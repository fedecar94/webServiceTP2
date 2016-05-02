"""
Este es el cliente de la base de datos centralizada que solicita informacion a los hospitales.
Requiere una DB llamada hospitales
"""
import sys
import dataset
import logging
import traceback as tb
import suds.metrics as metrics
from suds import WebFault
import threading
from suds.client import Client
from suds.wsse import *
from datetime import datetime
import time
import json


userid = "admin"
password = "admin"
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/hospitales')


class Historial(object):
    """
    Esta clase Historial se utiliza cuando se reciben varios historiales al mismo tiempo.
    """
    def __init__(self, **kwargs):
        for keyword in ["ci_paciente", "fecha_hist", "sintomas", "diagnostico", "enfermedad", "hospital",
                        "responsable"]:
            setattr(self, keyword, kwargs[keyword])
        self.code = 0
        self.coordinates = []
        self.population = 0

    def __str__(self):
        fields = ['  {}={!r}'.format(k, v)
                  for k, v in self.__dict__.items() if not k.startswith("_")]
        return "{}(\n{})".format(self.__class__.__name__, '\n'.join(fields))


def datos(id, client):
    t_paciente = db['tabla_paciente']
    try:
        result = db.query('select exists(select 1 from tabla_paciente where ci=' + str(id) + ')')
        for row in result:
            check = row['exists']
        if check == True:
            print "Ya existen los datos personales de ese paciente en la BD"
            return False
        else:
            r1 = client.service.personaID(id, userid, password)
            if r1 == 'No existe el paciente':
                print r1
                return True
            else:
                r = json.loads(r1)
                print r['nombre']
                t_paciente.insert(dict(ci=id, nombre=r['nombre'], sexo=r['sexo'], edad=r['edad'],
                                       lugar_fecha_nac=r['lugar_fecha_nac'], ocupacion=r['ocupacion'],
                                       religion=r['religion'], raza=r['raza'], domicilio=r['domicilio'],
                                       telefono=r['telefono']))
                return False
    except WebFault, f:
        print f
        print f.fault
    except Exception, e:
        print e
        tb.print_exc()


def conectar():
    userid = raw_input("Userid: ")
    password = raw_input("Contrasena: ")
    ip = str(raw_input("ip del hospital a conectar: "))
    url = 'http://' + ip + ':8080/ObtenerDatosPaciente/ObtenerDatosPaciente?wsdl'
    client = Client(url)
    client.set_options(timeout=300)
    return client


def paciente():
    """
    La seccion de abajo se usa para solicitar un historial segun la CI de la persona y la fecha deseada
    """
    client = conectar()

    print "CI del paciente a consultar, ejemplo 1234567"
    print "ingrese 0 para volver al menu"
    id = input("CI: ")
    if id == 0:
        main()
    while(datos(id, client)):
        print "CI del paciente a consultar, ejemplo 1234567"
        print "ingrese 0 para volver al menu"
        id = input("CI: ")
        if id == 0:
            main()

    i = str(raw_input('ingrese fecha de historial (dd/mm/aaaa):'))
    dt = datetime.strptime(i, '%d/%m/%Y')
    fecha = time.mktime(dt.timetuple())

    r2 = client.service.historialID(id, fecha, userid, password)
    if r2 == 'No existe el historial':
        print r2
    else:
        t_historiales = db['historiales']
        r = json.loads(r2)
        print "carga exitosa"
        t_historiales.insert(dict(ci_paciente=id, hospital=r['hospital'],
                                  responsable=r['responsable'], sintomas=r['sintomas'],
                                  diagnostico=r['diagnostico'], enfermedad=r['enfermedad'],
                                  fecha_hist=r['fecha_hist']))

    main()


def tiempo():
    """
    La seccion de abajo se usa para solicitar los historiales en un rango de fechas.
    """
    client = conectar()

    i = str(raw_input('ingrese fecha inicial (dd/mm/aaaa):'))
    dt = datetime.strptime(i, '%d/%m/%Y')
    fecha1 = time.mktime(dt.timetuple())

    i = str(raw_input('ingrese fecha final (dd/mm/aaaa):'))
    dt = datetime.strptime(i, '%d/%m/%Y')
    fecha2 = time.mktime(dt.timetuple())


    r2 = client.service.historialFecha(fecha1, fecha2, userid, password)
    if r2 == "Nombre de usuario o contrasena incorrecto/a":
        print("Nombre de usuario o contrasena incorrecto/a")
    elif r2 == "No existen los historiales":
        print r2
    else:
        t_historiales = db['historiales']
        r = json.loads('{"historiales":' + r2 + '}')

        historiales = [Historial(**historial_info) for historial_info in r["historiales"]]
        for historial in historiales:
            t_historiales.insert(dict(ci_paciente=historial.ci_paciente, hospital=historial.hospital,
                                      responsable=historial.responsable, sintomas=historial.sintomas,
                                      diagnostico=historial.diagnostico, enfermedad=historial.enfermedad,
                                      fecha_hist=historial.fecha_hist))

    main()


def automatico(client, espera):

    dt = datetime(year=2010, month=12, day=21)
    fecha1 = time.mktime(dt.timetuple())
    fecha2 = time.time()

    while True:
        r2 = client.service.historialFecha(fecha1, fecha2, userid, password)
        if r2 == "Nombre de usuario o contrasena incorrecto/a":
            print("Nombre de usuario o contrasena incorrecto/a")
        elif r2 == "No existen los historiales":
            print("no hay historiales nuevos")
        else:
            t_historiales = db['historiales']
            r = json.loads('{"historiales":' + r2 + '}')

            historiales = [Historial(**historial_info) for historial_info in r["historiales"]]
            for historial in historiales:
                t_historiales.insert(dict(ci_paciente=historial.ci_paciente, hospital=historial.hospital,
                                          responsable=historial.responsable, sintomas=historial.sintomas,
                                          diagnostico=historial.diagnostico, enfermedad=historial.enfermedad,
                                          fecha_hist=historial.fecha_hist))
        fecha1 = fecha2
        time.sleep(espera)
        fecha2 = time.time()


def auto():
    ip = str(raw_input("ip del hospital a conectar: "))
    url = 'http://' + ip + ':8080/ObtenerDatosPaciente/ObtenerDatosPaciente?wsdl'
    client = Client(url)
    client.set_options(timeout=300)

    espera = input("tiempo entre actualizaciones (segundos): ")

    t = threading.Thread(target=automatico(client, espera))
    t.setDaemon(True)
    t.start()
    main()


def main():

    print "Base de Datos Centralizada"
    print "Ingrese opcion:"
    print "1. Solicitar datos de un paciente"
    print "2. Solicitar datos en un rango de tiempo"
    print "3. Configurar Autosincronizacion"
    print "0. Terminar ejecucion"
    op = input("opcion: ")

    if op == 1:
        paciente()

    elif op == 2:
        tiempo()

    elif op == 3:
        auto()

    elif op ==0:
        print "Terminando..."
        return 0

    else:
        print "no es opcion valida..."
        main()


main()

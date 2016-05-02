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
from suds.client import Client
from suds.wsse import *
import json
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/hospitales')

"""
Esta clase Historial se utiliza cuando se reciben varios historiales al mismo tiempo.
"""
class Historial(object):
    def __init__(self, **kwargs):
        for keyword in ["ci_paciente", "fecha_hist", "sintomas", "diagnostico", "enfermedad","hospital","responsable"]:
            setattr(self, keyword, kwargs[keyword])
        self.code = 0
        self.coordinates = []
        self.population = 0

    def __str__(self):
        fields = ['  {}={!r}'.format(k,v)
                    for k, v in self.__dict__.items() if not k.startswith("_")]
        return "{}(\n{})".format(self.__class__.__name__, '\n'.join(fields))




try:
    url = 'http://localhost:8080/ObtenerDatosPaciente/ObtenerDatosPaciente?wsdl'
    client = Client(url)
    client.set_options(timeout= 300)
    userid = 'Ruben'
    password = 'admin'

    """
    id y las fechas son datos de prueba, se debe solicitar al usuario ingresar el numero de ci a usar como id.
    Ademas se debe hacer un menu para poder elegir las opciones de consulta y tambien elegir el hospital al que
    quiere consultar
    """
    id=1234567
    fecha = 1461169733.0
    fecha1 = fecha
    fecha2 = 1461969754.0


    t_paciente= db['tabla_paciente']
    t_historiales=db['historiales']

    """
    La seccion de abajo se usa para solicitar los datos de una persona cuando la BD central no los tiene.
    Solo se ejecuta una vez por persona.
    """

    result = db.query('select exists(select 1 from tabla_paciente where ci='+str(id)+')')
    for row in result:
        check= row['exists']
    if check==True:
        print "Ya existen los datos personales de ese paciente en la BD"
    else:
        r1 = client.service.personaID(id, userid, password)
        if r1 == 'No existe el paciente':
            print r1
        else:
            r=json.loads(r1)
            t_paciente.insert(dict(ci= id, nombre = r['nombre'],sexo= r['sexo'], edad=r['edad'],
                                      lugar_fecha_nac= r['lugar_fecha_nac'], ocupacion= r['ocupacion'],
                                      religion=r['religion'], raza= r['raza'], domicilio= r['domicilio'],
                                       telefono= r['telefono']))

    """
    La seccion de abajo se usa para solicitar un historial segun la CI de la persona y la fecha deseada
    """
    r2=client.service.historialID(id, fecha, userid, password)
    if r2=='No existe el historial':
        print r2
    else:
        r = json.loads(r2)

        t_historiales.insert(dict(ci_paciente= id, hospital=r['hospital'],
                                  responsable= r['responsable'], sintomas=r['sintomas'],
                                  diagnostico= r['diagnostico'], enfermedad= r['enfermedad'],
                                  fecha_hist=r['fecha_hist']))


    """
    La seccion de abajo se usa para solicitar los historiales en un rango de fechas.
    """
    r2=client.service.historialFecha(fecha1, fecha2, userid, password)
    if r2=="No existen los historiales":
        print r2
    else:
        r = json.loads('{"historiales":' + r2 + '}')

        historiales = [Historial(**historial_info) for historial_info in r["historiales"]]
        for historial in historiales:

            t_historiales.insert(dict(ci_paciente=historial.ci_paciente, hospital=historial.hospital,
                                      responsable=historial.responsable, sintomas=historial.sintomas,
                                      diagnostico=historial.diagnostico, enfermedad=historial.enfermedad,
                                      fecha_hist = historial.fecha_hist))

    """
    La seccion de abajo se usa para solicitar el ultimo historial de la persona segun su CI.
    """
    r1 = client.service.actualizacion(id, userid, password)

    if r1 == 'No existe el historial':
        print r1
    else:
        r3=json.loads(r1)

        t_historiales.insert(dict(ci_paciente=id, hospital=r3['hospital'],
                                  responsable=r3['responsable'], sintomas=r3['sintomas'],
                                  diagnostico=r3['diagnostico'], enfermedad=r3['enfermedad'],
                                  fecha_hist=r3['fecha_hist']))


except WebFault, f:
    print f
    print f.fault
except Exception, e:
    print e
    tb.print_exc()



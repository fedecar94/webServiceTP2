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
import json
db = dataset.connect('postgresql://postgres:postgres@localhost:5432/hospitales')

"""
Esta clase Historial se utiliza cuando se reciben varios historiales al mismo tiempo.
"""
class Historial(object):
    def __init__(self, **kwargs):
        for keyword in ["id", "fecha_hist", "sintomas", "diagnostico", "enfermedad","hospital","responsable"]:
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
    """
    id y las fechas son datos de prueba, se debe solicitar al usuario ingresar el numero de ci a usar como id.
    Ademas se debe hacer un menu para poder elegir las opciones de consulta y tambien elegir el hospital al que
    quiere consultar
    """
    id=333
    fecha = 123.0
    fecha1 = fecha
    fecha2 = 123.7

    t_paciente= db['tabla_paciente']
    t_historiales=db['historiales']

    """
    La seccion de abajo se usa para solicitar los datos de una persona cuando la BD central no los tiene.
    Solo se ejecuta una vez por persona.
    """

    result = db.query('select exists(select 1 from tabla_paciente where id='+str(id)+')')
    for row in result:
        check= row['exists']
    if check==False:
        "Ya existen los datos personales de ese paciente en la BD"
    else:
        r1=json.loads(client.service.personaID(id))

        t_paciente.insert(dict(ci= id, nombre = r1['nombre'],sexo= r1['sexo'], edad=r1['edad'],
                                  lugar_fecha_nac= r1['lugar_fecha_nac'], ocupacion= r1['ocupacion'],
                                  religion=r1['religion'], raza= r1['raza'], domicilio= ['domicilio'],
                                   telefono= ['telefono']))

    """
    La seccion de abajo se usa para solicitar un historial segun la CI de la persona y la fecha deseada
    """
    r1=json.loads(client.service.historialID(id, fecha))

    t_historiales.insert(dict(ci_paciente= id, hospital=r1['hospital'],
                              responsable= r1['responsable'], sintomas=r1['sintomas'],
                              diagnostico= r1['diagnostico'], enfermedad= r1['enfermedad'], fecha_hist=r1['fecha_hist']))


    """
    La seccion de abajo se usa para solicitar los historiales en un rango de fechas.
    """
    r2=json.loads('{"historiales":'+client.service.historialFecha(fecha1, fecha2)+'}')

    historiales = [Historial(**historial_info) for historial_info in r2["historiales"]]
    for historial in historiales:

        t_historiales.insert(dict(ci_paciente=historial.id, hospital=historial.hospital,
                                  responsable=historial.responsable, sintomas=historial.sintomas,
                                  diagnostico=historial.diagnostico, enfermedad=historial.enfermedad, fecha_hist = historial.fecha_hist))

    """
    La seccion de abajo se usa para solicitar el ultimo historial de la persona segun su CI.
    """
    r3=client.service.actualizacion(id)

    t_historiales.insert(dict(ci_paciente=id, hospital=r3['hospital'],
                              responsable=r3['responsable'], sintomas=r3['sintomas'],
                              diagnostico=r3['diagnostico'], enfermedad=r3['enfermedad'], fecha_hist=r3['fecha_hist']))


except WebFault, f:
    print f
    print f.fault
except Exception, e:
    print e
    tb.print_exc()



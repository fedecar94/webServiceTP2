# Ejemplo de un cliente webservice en Python que
# consume un ws de operaciones matematicas.
# Se utiliza la libreria suds https://fedorahosted.org/suds/
#
#Sistemas Distribuidos - 2012


import sys

import logging
import traceback as tb
import suds.metrics as metrics
from suds import WebFault
from suds.client import Client


try:
    url = 'http://localhost:8080/ObtenerDatosPaciente/ObtenerDatosPaciente?wsdl'
    client = Client(url)


    r1=client.service.nombre("Ruben")
    print r1

except WebFault, f:
    print f
    print f.fault
except Exception, e:
    print e
    tb.print_exc()


import dataset 
import sys

db1 = dataset.connect('postgresql://postgres:postgres@localhost:5432/sd_ips')
db2 = dataset.connect('postgresql://postgres:postgres@localhost:5432/sd_bautista')
db3 = dataset.connect('postgresql://postgres:postgres@localhost:5432/sd_migone')


#Crear tabla pacientes e insertar datos en la tabla del hospital_1

db1.create_table('paciente', primary_id='ci', primary_type='Integer')
table1 = db1['paciente']
data = dict(ci=1234567, edad=45, lugar_fecha_nac='Caaguazu', ocupacion='Docente', religion='Catolico', raza='Latino', domicilio='San Lorenzo', telefono=987456321 )
table1.insert(data)
data = dict(ci=6541234, edad=16, lugar_fecha_nac='Asuncion', ocupacion='Estudiante', religion='Judio', raza='Asiatico', domicilio='San Antonio', telefono=985123963 )
table1.insert(data)

res = db1.query('CREATE TABLE historial (ci_paciente integer REFERENCES paciente (ci))')
table1 = db1['historial']
data = dict(ci_paciente=1234567, fecha_hist=1461969753, sintomas='Dolor de huesos', diagnostico='Dengue', enfermedad='Dengue', hospital='ips', responsable='Francisco Garay')
table1.insert(data)
data = dict(ci_paciente=1234567, fecha_hist=1261949753, sintomas='Estornudos', diagnostico='Gripe', enfermedad='Resfriado', hospital='ips', responsable='Ruben Acosta')
table1.insert(data)
data = dict(ci_paciente=1234567, fecha_hist=1461169733, sintomas='Fatiga', diagnostico='Gripe', enfermedad='Resfriado', hospital='ips', responsable='Federico Aseretto')
table1.insert(data)
data = dict(ci_paciente=6541234, fecha_hist=1154103753, sintomas='Fiebre', diagnostico='Zika', enfermedad='Dengue', hospital='ips', responsable='Sebastian Garay')
table1.insert(data)
data = dict(ci_paciente=6541234, fecha_hist=1354103753, sintomas='Dolores musculares', diagnostico='Gripe', enfermedad='Dengue', hospital='ips', responsable='Jose Gonzalez')
table1.insert(data)
data = dict(ci_paciente=6541234, fecha_hist=1356103753, sintomas='Lagrimeo en los ojos', diagnostico='Alergia', enfermedad='Resfriado', hospital='ips', responsable='Sebastian Garay')
table1.insert(data)

#Crear tabla pacientes e insertar datos en la tabla del hospital_2
db2.create_table('paciente', primary_id='ci', primary_type='Integer')
table2 = db2['paciente']
data = dict(ci=7412589, edad=11, lugar_fecha_nac='Encarnacion', ocupacion='Estudiante', religion='Protestante', raza='Latino', domicilio='Encarnacion', telefono=983124697 )
table2.insert(data)
data = dict(ci=3357159, edad=29, lugar_fecha_nac='Asuncion', ocupacion='Electricista', religion='Catolico', raza='Latino', domicilio='San Lorenzo', telefono=981357159 )
table2.insert(data)

res = db2.query('CREATE TABLE historial (ci_paciente integer REFERENCES paciente (ci))')
table2 = db2['historial']
data = dict(ci_paciente=7412589, fecha_hist=1458142153, sintomas='Fiebre', diagnostico='Zika', enfermedad='Dengue', hospital='bautista', responsable='Jose Garay')
table2.insert(data)
data = dict(ci_paciente=7412589, fecha_hist=1448142153, sintomas='Nauseas', diagnostico='Dengue', enfermedad='H1N1', hospital='bautista', responsable='Federico Garay')
table2.insert(data)
data = dict(ci_paciente=7412589, fecha_hist=1438142153, sintomas='Fatiga', diagnostico='Zika', enfermedad='H1N1', hospital='bautista', responsable='Federico Gonzalez')
table2.insert(data)
data = dict(ci_paciente=3357159, fecha_hist=1453402553, sintomas='Zarpullido', diagnostico='Chikunguya', enfermedad='Zika', hospital='bautista', responsable='Ruben Garay')
table2.insert(data)
data = dict(ci_paciente=3357159, fecha_hist=1453412553, sintomas='Dolor de cabeza', diagnostico='H1N1', enfermedad='Gripe', hospital='bautista', responsable='Francisco Figueredo')
table2.insert(data)
data = dict(ci_paciente=3357159, fecha_hist=1453432553, sintomas='Escalofrios y sudoracion', diagnostico='H1N1', enfermedad='H1N1', hospital='bautista', responsable='Sebastian Figueredo')
table2.insert(data)

#Crear tabla pacientes e insertar datos en la tabla del hospital_3
db3.create_table('paciente', primary_id='ci', primary_type='Integer')
table3 = db3['paciente']
data = dict(ci=3124598, edad=26, lugar_fecha_nac='Asuncion', ocupacion='Futbolista', religion='Catolico', raza='Asiatico', domicilio='Fernando de la Mora', telefono=985534912 )
table3.insert(data)
data = dict(ci=999536, edad=80, lugar_fecha_nac='Coronel Oviedo', ocupacion='Artesano', religion='Catolico', raza='Latino', domicilio='Coronel Oviedo', telefono=961479312 )
table3.insert(data)

res = db3.query('CREATE TABLE historial (ci_paciente integer REFERENCES paciente (ci))')
table3 = db3['historial']
data = dict(ci_paciente=3124598, fecha_hist=1290375753, sintomas='Presion en el pecho', diagnostico='Anemia', enfermedad='Anemia', hospital='migone', responsable='Federico Aseretto')
table3.insert(data)
data = dict(ci_paciente=3124598, fecha_hist=1390375753, sintomas='Mareos', diagnostico='Anemia', enfermedad='H1N1', hospital='migone', responsable='Federico Figueredo')
table3.insert(data)
data = dict(ci_paciente=3124598, fecha_hist=1490375753, sintomas='Ampollas', diagnostico='Chikunguya', enfermedad='Chikunguya', hospital='migone', responsable='Federico Garay')
table3.insert(data)
data = dict(ci_paciente=999536, fecha_hist=1074842953, sintomas='Palidez', diagnostico='Falta de alimentacion', enfermedad='Anemia', hospital='migone', responsable='Ruben Villamayor')
table3.insert(data)
data = dict(ci_paciente=999536, fecha_hist=1174842953, sintomas='Dolor de articulaciones', diagnostico='Dengue', enfermedad='Chikunguya', hospital='migone', responsable='Sebastian Villamayor')
table3.insert(data)	
data = dict(ci_paciente=999536, fecha_hist=974842953, sintomas='Conjuntivitis', diagnostico='Conjuntivitis', enfermedad='Chikunguya', hospital='migone', responsable='Ruben Villamayor')
table3.insert(data)

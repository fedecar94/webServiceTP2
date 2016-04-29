/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import com.google.gson.Gson;
/**
 *
 * @author tekor
 */
@WebService(serviceName = "ObtenerDatosPaciente")
@Stateless()
public class ObtenerDatosPaciente {
    public Connection bdconnect(String hospital){
        Connection c=null;
        try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/"+hospital,
            "postgres", "postgres");
         
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      } return c;
    }
    /**
     * Esta es la clase que implementan todas las funciones del web service.
     * En esta clase hay que ver como conectar con una base de datos postgres y 
     * sacar de ahi los datos que pide cada funcion, y retornar como un string.
     * al retornar, el string tiene que tener el formato de gson, las ultimas 
     * dos lineas de cada funcion estan de ejemplo para que vean como enviar 
     * los datos. En la base de datos se guardan separadas las tablas persona
     * e historial, y van unidas usando el ci de la persona como foreign key.
     * @param id
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "historialID")
    /*Esta funcion recibe el numero de cedula de la persona y la fecha del histo
    solicitado. Busca estos dos en la base de datos y los devuelve en una clase 
    Historial formateada a string json
    */
    public String historialID(@WebParam(name = "id") int id, @WebParam(name = "fecha") double fecha) {
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial histo = new Historial();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM historial where ci_paciente="
                                        +id+" and fecha_hist="+fecha+";" );
        if (rs.first()==true){
            histo.setCIPaciente(id);
            histo.setResponsable(rs.getString("responsable"));
            histo.setDiagnosstico(rs.getString("diagnostico"));
            histo.setEnfermedad(rs.getString("enfermedad"));
            histo.setFecha_hist(rs.getDouble("fecha_hist"));
            histo.setHospital(hospital);
            histo.setSintomas(rs.getString("sintomas"));
            rs.close();
            stmt.close();
            c.close();
            Gson gson = new Gson();
            return gson.toJson(histo);
        }else
            rs.close();
            stmt.close();
            c.close();
            return "No existe el historial";
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
        }
        return "Error desconocido";
    }
    @WebMethod(operationName = "actualizacion")
    /*Esta funcion recibe el numero de cedula de la persona y devuelve en una 
    clase Historial formateada a string json el ultimo historial que esta en la
    base de datos de esa persona
    */
    
    public String actualizacion(@WebParam(name = "id") int id) {
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial histo = new Historial();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "select * from historiales where "
                            + "ci_paciente="+id+" order by fecha_hist desc limit 1" );
        if (rs.first()==true){
            histo.setCIPaciente(id);
            histo.setResponsable(rs.getString("responsable"));
            histo.setDiagnosstico(rs.getString("diagnostico"));
            histo.setEnfermedad(rs.getString("enfermedad"));
            histo.setFecha_hist(rs.getDouble("fecha_hist"));
            histo.setHospital(hospital);
            histo.setSintomas(rs.getString("sintomas"));
            rs.close();
            stmt.close();
            c.close();
            Gson gson = new Gson();
            return gson.toJson(histo);
        }else
            rs.close();
            stmt.close();
            c.close();
            return "No existe el historial";
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
        }
        return "Error desconocido";
    }

    @WebMethod(operationName = "historialFecha")
    /**
     * Esta funcion recibe dos fechas y devuelve una lista de todos los historia
     * les entre esas dos fechas
     */
    
    public String historialFecha(@WebParam(name = "fecha1") Double fecha1, @WebParam(name = "fecha2") Double fecha2) {
        
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial [] histo;
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM historial where fecha_hist "
                                        + "between "+fecha1+" and "+fecha2+";" );
        if (rs.first()==false){
            rs.close();
            stmt.close();
            c.close();
            return "No existen los historiales";
        }else{
            int i=0;
            while (rs.next()){
                i++;
            }
            histo = new Historial[i];
            rs.first();
            while (rs.next())
            {
                histo[i].setCIPaciente(rs.getInt("ci_paciente"));
                histo[i].setResponsable(rs.getString("responsable"));
                histo[i].setDiagnosstico(rs.getString("diagnostico"));
                histo[i].setEnfermedad(rs.getString("enfermedad"));
                histo[i].setFecha_hist(rs.getDouble("fecha_hist"));
                histo[i].setHospital(hospital);
                histo[i].setSintomas(rs.getString("sintomas"));
            }
            rs.close();
            stmt.close();
            c.close();
            Gson gson = new Gson();
            return gson.toJson(histo);
        }
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
        }
        
        return "Error desconocido";
    }

    /**
     * Esta funcion recibe la cedula de identidad de la persona y devuelve los 
     * datos personales de la persona en un string json
     */
    @WebMethod(operationName = "personaID")
    public String personaID(@WebParam(name = "id") int id) {
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Paciente pers = new Paciente();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM paciente where ci="
                                        +id+";" );
        if (rs.first()==true){
            pers.setCi(rs.getInt("ci"));
            pers.setNombre(rs.getString("nombre"));
            pers.setDomicilio(rs.getString("domicilio"));
            pers.setEdad(rs.getInt("edad"));
            pers.setLugar_fecha_nac(rs.getString("lugar_fecha_nac"));
            pers.setOcupacion(rs.getString("ocupacion"));
            pers.setRaza(rs.getString("raza"));
            pers.setReligion(rs.getString("religion"));
            pers.setSexo(rs.getString("sexo"));
            pers.setTelefono(rs.getInt("telefono"));
            rs.close();
            stmt.close();
            c.close();
            Gson gson = new Gson();
            return gson.toJson(pers);
        }else
            rs.close();
            stmt.close();
            c.close();
            return "No existe la persona";
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
        }
        return "Error desconocido";
    }
}

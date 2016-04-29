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
import com.google.gson.Gson;
/**
 *
 * @author tekor
 */
@WebService(serviceName = "ObtenerDatosPaciente")
@Stateless()
public class ObtenerDatosPaciente {

    /**
     * Esta es la clase que implementan todas las funciones del web service.
     * En esta clase hay que ver como conectar con una base de datos postgres y 
     * sacar de ahi los datos que pide cada funcion, y retornar como un string.
     * al retornar, el string tiene que tener el formato de gson, las ultimas 
     * dos lineas de cada funcion estan de ejemplo para que vean como enviar 
     * los datos. En la base de datos se guardan separadas las tablas persona
     * e historial, y van unidas usando el ci de la persona como foreign key.
     */
    @WebMethod(operationName = "historialID")
    /*Esta funcion recibe el numero de cedula de la persona y la fecha del histo
    solicitado. Busca estos dos en la base de datos y los devuelve en una clase 
    Historial formateada a string json
    */
    public String historialID(@WebParam(name = "id") int id, @WebParam(name = "fecha") double fecha) {
        Historial histo = new Historial();
        histo.setId(123);
        histo.setResponsable("asd");
        histo.setDiagnosstico("asdasd");
        histo.setEnfermedad("asdsad2");
        histo.setFecha_hist(0);
        histo.setHospital("macumbero");
        histo.setSintomas("gripe");
        Gson gson = new Gson();
        return gson.toJson(histo);
    }
    @WebMethod(operationName = "actualizacion")
    /*Esta funcion recibe el numero de cedula de la persona y devuelve en una 
    clase Historial formateada a string json el ultimo historial que esta en la
    base de datos de esa persona
    */
    
    public String actualizacion(@WebParam(name = "id") int id) {
        
        return null;
    }

    @WebMethod(operationName = "historialFecha")
    /**
     * Esta funcion recibe dos fechas y devuelve una lista de todos los historia
     * les entre esas dos fechas
     */
    
    public String historialFecha(@WebParam(name = "fecha1") Double fecha1, @WebParam(name = "fecha2") Double fecha2) {
        Historial [] histo = new Historial[2];
        histo[1] = new Historial();
        histo[1].setId(123);
        histo[1].setResponsable("asd");
        histo[1].setDiagnosstico("asdasd");
        histo[1].setEnfermedad("asdsad2");
        histo[1].setFecha_hist(0);
        histo[1].setHospital("macumbero");
        histo[1].setSintomas("gripe");
        histo[0] = new Historial();

        histo[0].setId(123);
        histo[0].setResponsable("asd");
        histo[0].setDiagnosstico("asdasd");
        histo[0].setEnfermedad("asdsad2");
        histo[0].setFecha_hist(0);
        histo[0].setHospital("macumbero");
        histo[0].setSintomas("gripe");
        Gson gson = new Gson();
        
        return gson.toJson(histo);
    }

    /**
     * Esta funcion recibe la cedula de identidad de la persona y devuelve los 
     * datos personales de la persona en un string json
     */
    @WebMethod(operationName = "personaID")
    public String personaID(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }
}

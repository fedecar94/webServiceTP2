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
     * @param id
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "historialID")
    /*Esta funcion recibe el numero de cedula de la persona y la fecha del histo
    solicitado. Busca estos dos en la base de datos y los devuelve en una clase 
    Historial formateada a string json
    */
    public String historialID(@WebParam(name = "id") int id, @WebParam(name = "fecha") double fecha, 
            @WebParam(name= "userid") String userid,@WebParam(name = "password") String password ) {
        if (userid.equals(GerenciadorBD.val_username) && password.equals(GerenciadorBD.val_pass)){
        return GerenciadorBD.get_hist_id(id,fecha);}
        else 
            return "Nombre de usuario o contraseña incorrecto/a";
    }
    @WebMethod(operationName = "actualizacion")
    /*Esta funcion recibe el numero de cedula de la persona y devuelve en una 
    clase Historial formateada a string json el ultimo historial que esta en la
    base de datos de esa persona
    */
    
    public String actualizacion(@WebParam(name = "id") int id, 
            @WebParam(name= "userid") String userid,@WebParam(name = "password") String password) {
        if (userid.equals(GerenciadorBD.val_username) && password.equals(GerenciadorBD.val_pass)){

        return GerenciadorBD.get_hist_act(id);}
        else 
            return "Nombre de usuario o contraseña incorrecto/a";
    }

    @WebMethod(operationName = "historialFecha")
    /**
     * Esta funcion recibe dos fechas y devuelve una lista de todos los historia
     * les entre esas dos fechas
     */
    
    public String historialFecha(@WebParam(name = "fecha1") Double fecha1, @WebParam(name = "fecha2") Double fecha2, 
            @WebParam(name= "userid") String userid,@WebParam(name = "password") String password) {
        if (userid.equals(GerenciadorBD.val_username) && password.equals(GerenciadorBD.val_pass)){
        return GerenciadorBD.get_hist_fecha(fecha1, fecha2);}
        else 
            return "Nombre de usuario o contraseña incorrecto/a";
    }

    /**
     * Esta funcion recibe la cedula de identidad de la persona y devuelve los 
     * datos personales de la persona en un string json
     */
    @WebMethod(operationName = "personaID")
    public String personaID(@WebParam(name = "id") int id, 
            @WebParam(name= "userid") String userid,@WebParam(name = "password") String password) {
        return GerenciadorBD.get_persona_id(id);
    }
}

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
import hospital.Historial;

/**
 *
 * @author tekor
 */
@WebService(serviceName = "ObtenerDatosPaciente")
@Stateless()
public class ObtenerDatosPaciente {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "historialID")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizacion")
    public String getHistorialID(@WebParam(name = "ID") int ID) {
        
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "historialFecha")
    public String historialFecha(@WebParam(name = "fecha1") Double fecha1, @WebParam(name = "fecha2") Double fecha2) {
        Historial histo[] = new Historial[3];
        return ;
    }
}

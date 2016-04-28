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
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    @WebMethod(operationName = "nombre")
    public String nombre(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

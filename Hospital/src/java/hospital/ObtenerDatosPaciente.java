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
     * This is a sample web service operation
     */
    @WebMethod(operationName = "historialID")
    public String hello(@WebParam(name = "name") String txt) {
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
}

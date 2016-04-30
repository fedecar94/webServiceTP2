/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class GerenciadorBD {
    public static Connection bdconnect(String hospital){
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
    public static String get_hist_id(int id, double fecha){    
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial histo = new Historial();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM historial where ci_paciente="
                                        +id+" and fecha_hist="+fecha+";" );
        if (rs.next()==true){
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
    public static String get_hist_act(int id)
    {
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial histo = new Historial();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "select * from historial where "
                            + "ci_paciente="+id+" order by fecha_hist desc limit 1" );
        if (rs.next()==true){
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
    public static String get_hist_fecha(double fecha1, double fecha2)
    {
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Historial [] histo;
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM historial where fecha_hist "
                                        + "between "+fecha1+" and "+fecha2+";" );
        if (rs.next()==false){
            rs.close();
            stmt.close();
            c.close();
            return "No existen los historiales";
        }else{
            int i=0;
            rs = stmt.executeQuery("SELECT * FROM historial where fecha_hist "
                                        + "between "+fecha1+" and "+fecha2+";" );
            while (rs.next()){
                i++;
            }
            histo = new Historial[i];
            rs = stmt.executeQuery("SELECT * FROM historial where fecha_hist "
                                        + "between "+fecha1+" and "+fecha2+";" );
            i=0;
            while (rs.next())
            {
                histo[i].setCIPaciente(rs.getInt("ci_paciente"));
                histo[i].setResponsable(rs.getString("responsable"));
                histo[i].setDiagnosstico(rs.getString("diagnostico"));
                histo[i].setEnfermedad(rs.getString("enfermedad"));
                histo[i].setFecha_hist(rs.getDouble("fecha_hist"));
                histo[i].setHospital(hospital);
                histo[i].setSintomas(rs.getString("sintomas"));
                i++;
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
    
    public static String get_persona_id(int id){
        String hospital ="ips";
        Connection c=bdconnect(hospital);
        Paciente pers = new Paciente();
        try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM paciente where ci="
                                        +id+";" );
        if (rs.next()==true){
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

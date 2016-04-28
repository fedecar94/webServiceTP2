/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

/**
 *
 * @author User
 */
public class Historial {
    private double fecha_hist;
    private String sintomas;
    private String diagnosstico;
    private String hospital;

    /**
     * @return the fecha_hist
     */
    public double getFecha_hist() {
        return fecha_hist;
    }

    /**
     * @param fecha_hist the fecha_hist to set
     */
    public void setFecha_hist(double fecha_hist) {
        this.fecha_hist = fecha_hist;
    }

    /**
     * @return the sintomas
     */
    public String getSintomas() {
        return sintomas;
    }

    /**
     * @param sintomas the sintomas to set
     */
    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * @return the diagnosstico
     */
    public String getDiagnosstico() {
        return diagnosstico;
    }

    /**
     * @param diagnosstico the diagnosstico to set
     */
    public void setDiagnosstico(String diagnosstico) {
        this.diagnosstico = diagnosstico;
    }

    /**
     * @return the hospital
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * @param hospital the hospital to set
     */
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

}

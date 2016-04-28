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
    private Integer id;
    private double fecha_hist;
    private String sintomas;
    private String diagnosstico;
    private String enfermedad;
    private String hospital;
    private String responsable;

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
    
    @Override
    public String toString() {
        return "Data [id=" + this.getId() 
                + ", fecha_hist=" + this.getFecha_hist() 
                + ", sintomas=" + this.getSintomas() 
                + ", diagnostico=" + this.getDiagnosstico() 
                + ", enfermedad=" + this.getEnfermedad()
                + ", hospital=" + this.getHospital() 
                + ", responsable=" +this.getResponsable()
                + "]";
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the enfermedad
     */
    public String getEnfermedad() {
        return enfermedad;
    }

    /**
     * @param enfermedad the enfermedad to set
     */
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
}

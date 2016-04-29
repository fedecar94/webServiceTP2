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
    private Integer ci_paciente;
    private double fecha_hist;
    private String sintomas;
    private String diagnostico;
    private String enfermedad;
    private String hospital;
    private String responsable;
    
    public Historial ()
    {
        ci_paciente =0;
        fecha_hist=0;
        sintomas=null;
        diagnostico=null;
        enfermedad=null;
        hospital=null;
        responsable=null;
    }
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
        return diagnostico;
    }
    /**
     * @param diagnosstico the diagnosstico to set
     */
    public void setDiagnosstico(String diagnosstico) {
        this.diagnostico = diagnosstico;
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
        return "Data [ci_paciente=" + this.getCIPaciente() 
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
    public Integer getCIPaciente() {
        return ci_paciente;
    }

    /**
     * @param id the id to set
     */
    public void setCIPaciente(Integer id) {
        this.ci_paciente = id;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Trabaja;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author usuario
 */
@Entity
@Table(name="trabaja")
public class Trabaja {
	@EmbeddedId
        private ClaveTrabaja clave;
       /* 
        @Column(name="codtra")
        private Long codtra;
        @Column(name="codemp")
        private Long codemp;
*/
  
	
	@Column(name="convenio")
	private String convenio;
	
	@Column(name="grupocot")
	private Short grupoCot;
	
	@Column(name="catprof")
	private String catProf;
	
	@Column(name="tipocontrato")
	private String tipoContrato;
	
	@Column(name="fecintra")
	private String fecInTra;
	
	@Column(name="fecsaltra")
	private String fecSalTra;
        


    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Short getGrupoCot() {
        return grupoCot;
    }

    public void setGrupoCot(Short grupoCot) {
        this.grupoCot = grupoCot;
    }

    public String getCatProf() {
        return catProf;
    }

    public void setCatProf(String catProf) {
        this.catProf = catProf;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getFecInTra() {
        return fecInTra;
    }

    public void setFecInTra(String fecInTra) {
        this.fecInTra = fecInTra;
    }

    public String getFecSalTra() {
        return fecSalTra;
    }

    public void setFecSalTra(String fecSalTra) {
        this.fecSalTra = fecSalTra;
    }
    
      public ClaveTrabaja getClave() {
        return clave;
    }

    public void setClave(ClaveTrabaja clave) {
        this.clave = clave;
    }
/*
    public Long getCodtra() {
        return codtra;
    }

    public void setCodtra(Long codtra) {
        this.codtra = codtra;
    }

    public Long getCodemp() {
        return codemp;
    }

    public void setCodemp(Long codemp) {
        this.codemp = codemp;
    }*/
}

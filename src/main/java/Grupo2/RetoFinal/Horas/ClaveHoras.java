/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Horas;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author ferna
 */
@Embeddable
public class ClaveHoras implements Serializable {

    Long idtra;
    Long codemp;
    String mesanyo;

    public ClaveHoras() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idtra);
        hash = 71 * hash + Objects.hashCode(this.codemp);
        hash = 71 * hash + Objects.hashCode(this.mesanyo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClaveHoras other = (ClaveHoras) obj;
        if (!Objects.equals(this.idtra, other.idtra)) {
            return false;
        }
        if (!Objects.equals(this.codemp, other.codemp)) {
            return false;
        }
        if (!Objects.equals(this.mesanyo, other.mesanyo)) {
            return false;
        }
        return true;
    }

    public Long getCodtra() {
        return idtra;
    }

    public void setCodtra(Long codtra) {
        this.idtra = codtra;
    }

    public Long getCodemp() {
        return codemp;
    }

    public void setCodemp(Long codemp) {
        this.codemp = codemp;
    }

    public String getMesanyo() {
        return mesanyo;
    }

    public void setMesanyo(String mesanyo) {
        this.mesanyo = mesanyo;
    }

}

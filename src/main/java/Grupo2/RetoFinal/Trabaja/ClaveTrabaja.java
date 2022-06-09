/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Trabaja;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;



/**
 *
 * @author ferna
 */
@Embeddable
public class ClaveTrabaja implements Serializable {
    
    public Long codtra;
    public Long codemp;
    
    public ClaveTrabaja(){}
    public ClaveTrabaja(Long codtra,Long codemp){
    this.codtra=codtra;
    this.codemp=codemp;
    }
    
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
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.codtra);
        hash = 17 * hash + Objects.hashCode(this.codemp);
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
        final ClaveTrabaja other = (ClaveTrabaja) obj;
        if (!Objects.equals(this.codtra, other.codtra)) {
            return false;
        }
        if (!Objects.equals(this.codemp, other.codemp)) {
            return false;
        }
        return true;
    }
    
}

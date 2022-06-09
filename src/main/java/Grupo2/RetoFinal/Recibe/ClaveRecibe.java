/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Recibe;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author ferna
 */
@Embeddable
public class ClaveRecibe implements Serializable {

    Long id;
    Long codtra;
    String mesanyo;

    public ClaveRecibe() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.codtra);
        hash = 37 * hash + Objects.hashCode(this.mesanyo);
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
        final ClaveRecibe other = (ClaveRecibe) obj;
        if (!Objects.equals(this.mesanyo, other.mesanyo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codtra, other.codtra)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodtra() {
        return codtra;
    }

    public void setCodtra(Long codtra) {
        this.codtra = codtra;
    }

    public String getMesanyo() {
        return mesanyo;
    }

    public void setMesanyo(String mesanyo) {
        this.mesanyo = mesanyo;
    }

}

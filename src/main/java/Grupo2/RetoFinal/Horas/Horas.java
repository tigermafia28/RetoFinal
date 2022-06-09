/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Horas;

import Grupo2.RetoFinal.Trabaja.ClaveTrabaja;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ferna
 */
@Entity
@Table(name = "horas")
public class Horas {

    @EmbeddedId
    private ClaveHoras clave;

    @Column(name = "n_horasex")
    private int n_horas_ex;
    @Column(name="n_horastot")
    private int n_horas_tot;


    public ClaveHoras getClave() {
        return clave;
    }

    public void setClave(ClaveHoras clave) {
        this.clave = clave;
    }

    public int getN_horas_ex() {
        return n_horas_ex;
    }

    public void setN_horas_ex(int n_horas_ex) {
        this.n_horas_ex = n_horas_ex;
    }

    public int getN_horas_tot() {
        return n_horas_tot;
    }

    public void setN_horas_tot(int n_horas_tot) {
        this.n_horas_tot = n_horas_tot;
    }


}

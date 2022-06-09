/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Horas;

import Grupo2.RetoFinal.Empresa.Empresa;
import Grupo2.RetoFinal.Trabaja.ClaveTrabaja;
import Grupo2.RetoFinal.Trabaja.Trabaja;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ferna
 */
@Service
public class ServicioHoras {
    @Autowired
    RepositorioHoras horas;
    public List getAllHoras(){
        List<Horas> horatot = horas.findAll();
        
        return horatot;
        
    }
    
      public Horas getTrabajaById(ClaveHoras ho) {
        List<Horas> ole = horas.findAll();
        Horas hor = new Horas();
        for(int i=0;i<ole.size();i++){
            if(ole.get(i).getClave().codemp.equals(ho.codemp) && ole.get(i).getClave().idtra.equals(ho.idtra) && ole.get(i).getClave().mesanyo.equals(ho.mesanyo)){
              hor.setN_horas_ex(ole.get(i).getN_horas_ex());
              hor.setN_horas_tot(ole.get(i).getN_horas_tot());
            }
        }
        return hor;
    }
}

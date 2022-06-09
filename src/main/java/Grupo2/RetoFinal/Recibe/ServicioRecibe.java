/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Recibe;

import Grupo2.RetoFinal.Horas.ClaveHoras;
import Grupo2.RetoFinal.Horas.Horas;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ferna
 */
@Service
public class ServicioRecibe {
    @Autowired
    RepositorioRecibe repositorio;
    public List getAllRecibe(){
        List<Recibe> recibeto = repositorio.findAll();
        return recibeto;
    }
    
    public Recibe getTrabajaById(ClaveRecibe re) {
        List<Recibe> ole = repositorio.findAll();
        Recibe recib = new Recibe();
        for(int i=0;i<ole.size();i++){
            if(ole.get(i).getRecibe().codtra.equals(re.codtra) && ole.get(i).getRecibe().id.equals(re.id) && ole.get(i).getRecibe().mesanyo.equals(re.mesanyo)){
              recib.setValor(ole.get(i).getValor());
            }
        }
        return recib;
    }
}

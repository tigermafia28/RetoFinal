/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Trabaja;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ferna
 */
@Service
public class ServicioTrabaja {

    @Autowired
    RepositorioTrabaja repositorio;

    public List getAllTrabaja() {

        List<Trabaja> listaTrabaja = repositorio.findAll();

        return listaTrabaja;
    }

    public Trabaja getTrabajaById(ClaveTrabaja tr) {
        List<Trabaja> ole = repositorio.findAll();
        Trabaja trab = new Trabaja();
        for(int i=0;i<ole.size();i++){
            if(ole.get(i).getClave().codemp.equals(tr.codemp) && ole.get(i).getClave().codtra.equals(tr.codtra)){
                trab.setCatProf(ole.get(i).getCatProf());
                trab.setConvenio(ole.get(i).getConvenio());
                trab.setFecInTra(ole.get(i).getFecInTra());
                trab.setFecSalTra(ole.get(i).getFecSalTra());
                trab.setGrupoCot(ole.get(i).getGrupoCot());
                trab.setTipoContrato(ole.get(i).getTipoContrato());
            }
        }
        return trab;
    }
    
    public Optional<List<Trabaja>> to(){
        Optional<List<Trabaja>> listato = repositorio.listato();
        
        return listato;
    }
}

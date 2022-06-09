/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.pluses;

import Grupo2.RetoFinal.Empresa.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ferna
 */
@Service
public class ServicioPluses {
    @Autowired
    RepositorioPluses pluses;
    
    public List getAllPluses(){
        List<Pluses> tolospluses = pluses.findAll();
        return tolospluses;
    }
    
     public Pluses getEmpresaById(Long id) {

        Optional<Pluses> plus = pluses.findById(id);

        if (plus.isPresent())
            return plus.get();
		
        return null;
        
    }
}

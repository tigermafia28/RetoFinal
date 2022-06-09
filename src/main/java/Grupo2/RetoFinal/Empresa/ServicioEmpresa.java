/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Empresa;

import Grupo2.RetoFinal.Trabajador;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ferna
 */
@Service
public class ServicioEmpresa {
    @Autowired
    RepositorioEmpresa empresa;
    public List getAllEmpresas(){
        List<Empresa> empresas = empresa.findAll();
        
        return empresas;
    }
    
    public Empresa getEmpresaById(Long id) {

        Optional<Empresa> empres = empresa.findById(id);

        if (empres.isPresent())
            return empres.get();
		
        return null;
        
    }
}

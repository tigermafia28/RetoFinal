/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class ServicioTrabajador {
	@Autowired
    RepositorioTrabajador repositorio;
	
	public List getAllTrabajadores() {

        List<Trabajador> listaTrabajadores = repositorio.findAll();

        return listaTrabajadores;
    }

    public Trabajador getTrabajadorById(Long id) {

        Optional<Trabajador> trabajador = repositorio.findById(id);

        if (trabajador.isPresent())
            return trabajador.get();
		
        return null;
        
    }
    
    
}

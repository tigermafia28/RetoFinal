/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal;

import Grupo2.RetoFinal.ServicioTrabajador;
import Grupo2.RetoFinal.Trabaja.ServicioTrabaja;
import Grupo2.RetoFinal.Trabaja.Trabaja;
import Grupo2.RetoFinal.Trabajador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("/trabajador")
public class ControladorTrabajador {
	@Autowired
	ServicioTrabajador servicio;
	
	@GetMapping("/list")
    public ResponseEntity<String> listarTrabajadores() {

        List<Trabajador> listaTrabajadores = servicio.getAllTrabajadores();
        String resultado = "";

        for (Trabajador t : listaTrabajadores) {
            resultado += t.getNombre() + "\n";
        }

        return ResponseEntity.ok(resultado);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<String> getTrabajadorById(@PathVariable("id") Long id) {
        Trabajador trabajador = servicio.getTrabajadorById(id);

        if (trabajador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El trabajador con id=" + id + " no existe");
        } else {
            return ResponseEntity.ok(trabajador.getNombre());
        }
    }
    
   

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Trabaja;

import Grupo2.RetoFinal.ServicioTrabajador;
import Grupo2.RetoFinal.Trabaja.ServicioTrabaja;
import Grupo2.RetoFinal.Trabaja.Trabaja;
import Grupo2.RetoFinal.Trabajador;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ferna
 */
@RestController
@RequestMapping("/trabaja")
public class ControladorTrabaja {

    @Autowired
    ServicioTrabaja servicio;

    @GetMapping("/list")
    public ResponseEntity<String> listarTrabajadores() {

        List<Trabaja> listaTrabajadores = servicio.getAllTrabaja();
        String resultado = "";
        /*
        for (Trabaja t : listaTrabajadores) {
            resultado += t.getFecSalTra() + "\n";
        }*/
        resultado+=listaTrabajadores.get(0).getConvenio();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTrabajadorById(@PathVariable("id") Long id) {
        ClaveTrabaja ide = new ClaveTrabaja();
        Long code = 1L;
        Long codt = 1L;
        
        ide.setCodemp(code);
        ide.setCodtra(codt);
        Trabaja trabaja = servicio.getTrabajaById(ide);

        if (trabaja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El trabajador con id=" + id + " no existe");
        } else {
            return ResponseEntity.ok(trabaja.getCatProf());
        }
    }
    
    @GetMapping("/to")
    public ResponseEntity<String> listato(){
        Optional<List<Trabaja>> listato = servicio.to();
        String algo = listato.get().get(0).getTipoContrato();
        return ResponseEntity.ok(algo);
    }
}

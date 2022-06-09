/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Recibe;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ferna
 */
@RestController
@RequestMapping("/recibe")
public class ControladorRecibe {
    @Autowired
    ServicioRecibe servicio;
    @GetMapping("/list")
    public ResponseEntity<Float> getloqsea(){
        List<Recibe> klk = servicio.getAllRecibe();
        float asa = klk.get(0).getValor();
        
        return ResponseEntity.ok(asa);
    }
}

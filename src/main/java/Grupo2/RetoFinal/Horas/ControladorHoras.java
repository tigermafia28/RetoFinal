/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Horas;

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
@RequestMapping("/horas")
public class ControladorHoras {
    @Autowired
    ServicioHoras horas;
    
    @GetMapping("/list")
    public ResponseEntity<Integer> listarHoras(){
    List<Horas> horastot = horas.getAllHoras();
    int result = horastot.get(0).getN_horas_tot();
    return ResponseEntity.ok(result);
    }
    
}

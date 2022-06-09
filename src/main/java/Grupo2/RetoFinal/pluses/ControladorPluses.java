/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.pluses;

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
@RequestMapping("/pluses")
public class ControladorPluses {
    @Autowired
    ServicioPluses spluses;
    @GetMapping("/list")
    public ResponseEntity<String> MuestraPluses(){
        List<Pluses> pluses = spluses.getAllPluses();
        String flepas = pluses.get(0).getNombre();
        return ResponseEntity.ok(flepas);
    }
}

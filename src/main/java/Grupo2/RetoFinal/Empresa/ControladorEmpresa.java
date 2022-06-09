/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.RetoFinal.Empresa;

import Grupo2.RetoFinal.Trabaja.Trabaja;
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
@RequestMapping("/empresa")
public class ControladorEmpresa {
    
    @Autowired
    ServicioEmpresa empresas;
       @GetMapping("/list")
    public ResponseEntity<String> listarEmpresas() {
        

        List<Empresa> listaTrabajadores = empresas.getAllEmpresas();
        String resultado = "";
        /*
        for (Trabaja t : listaTrabajadores) {
            resultado += t.getFecSalTra() + "\n";
        }*/
        resultado+=listaTrabajadores.get(0).getCcc();

        return ResponseEntity.ok(resultado);
    }
}

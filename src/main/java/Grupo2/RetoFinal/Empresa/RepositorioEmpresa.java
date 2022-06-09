/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal.Empresa;

import Grupo2.RetoFinal.Trabaja.Trabaja;
import Grupo2.RetoFinal.Trabajador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ferna
 */
@Repository
public interface RepositorioEmpresa extends JpaRepository<Empresa, Long>{
     @Override
    List<Empresa> findAll();
    
     @Override
    Optional<Empresa> findById(Long id);
    
}

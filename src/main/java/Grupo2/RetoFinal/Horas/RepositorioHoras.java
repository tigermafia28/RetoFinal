/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal.Horas;


import Grupo2.RetoFinal.Empresa.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ferna
 */
@Repository
public interface RepositorioHoras extends JpaRepository<Horas, Long>{
    @Override
    List<Horas> findAll();
    
    @Override
    Optional<Horas> findById(Long id);
}

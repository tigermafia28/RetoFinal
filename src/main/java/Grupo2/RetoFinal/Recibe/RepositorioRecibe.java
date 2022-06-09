/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal.Recibe;


import Grupo2.RetoFinal.Horas.Horas;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ferna
 */
@Repository
public interface RepositorioRecibe extends JpaRepository<Recibe, Long>{
    @Override
    List<Recibe> findAll();
    
    @Override
    Optional<Recibe> findById(Long id);
}

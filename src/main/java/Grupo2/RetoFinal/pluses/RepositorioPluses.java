/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal.pluses;

import Grupo2.RetoFinal.Empresa.Empresa;
import Grupo2.RetoFinal.Trabaja.Trabaja;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ferna
 */
@Repository
public interface RepositorioPluses extends JpaRepository<Pluses, Long> {

    @Override
    List<Pluses> findAll();
    @Override
    Optional<Pluses> findById(Long id);
}

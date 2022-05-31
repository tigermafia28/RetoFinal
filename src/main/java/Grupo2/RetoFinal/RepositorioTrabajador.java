/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal;

import Grupo2.putas.Trabajador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public interface RepositorioTrabajador extends JpaRepository<Trabajador, Long> {
	@Override
    List<Trabajador> findAll();

    @Override
    Optional<Trabajador> findById(Long id);
}

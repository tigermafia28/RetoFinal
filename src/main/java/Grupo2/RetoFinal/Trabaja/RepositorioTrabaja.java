/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Grupo2.RetoFinal.Trabaja;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ferna
 */
@Repository
public interface RepositorioTrabaja extends JpaRepository<Trabaja, Long>{
    @Override
    List<Trabaja> findAll();
    //@Override
    //Optional<Trabaja> findById( id);
    
    @Query(value="SELECT * FROM trabaja",nativeQuery=true)
    Optional<List<Trabaja>> listato();
    
    

   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2.putas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author usuario
 */
@Entity
@Table(name="trabaja")
public class Trabaja {
	@Id
	@Column(name="CodTra")
	private Long codTra;
	
	@Id
	@Column(name="CodEmp")
	private Long codEmp;
	
	@Column(name="Convenio")
	private String convenio;
	
	@Column(name="GrupoCot")
	private Short grupoCot;
	
	@Column(name="CatProf")
	private String catProf;
	
	@Column(name="TipoContrato")
	private String tipoContrato;
	
	@Column(name="FecInTra")
	private String fecInTra;
	
	@Column(name="FecSalTra")
	private String fecSalTra;
}

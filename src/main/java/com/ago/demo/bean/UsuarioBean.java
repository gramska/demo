package com.ago.demo.bean;

public class UsuarioBean {

	private Integer idUsuario;
	private String nombre;
	private Integer edad;
	
	
	
	public UsuarioBean() {
		super();
	}

	public UsuarioBean(Integer idUsuario, String nombre, Integer edad) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "UsuarioBean [idUsuario=" + idUsuario + ", nombre=" + nombre + ", edad=" + edad + "]";
	} 
}

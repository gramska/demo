package com.ago.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ago.demo.bean.UsuarioBean;
import com.ago.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;


/**
* Frase corta descriptiva
* Descripción de la clase
* @author Gabino Ramos
* @version 01/11/2023
*/
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	@Qualifier("UsuarioService")
	private UsuarioService usuarioService;

	/**
	 * Obtiene el listado de usuarios registrados
	 * @author Gabino Ramos
	 * @return List<UsuarioBean>
	*/
	@GetMapping
	@ResponseBody
	@Operation(summary = "Obtiene el listado de usuarios registrados")
	public List<UsuarioBean> obtenerUsuario() {		
		return usuarioService.obtenerUsuarios();
	}
	
	/**
	 * Obtiene un usuario por [idUsuario]
	 * @author Gabino Ramos
	 * @param Integer
	 * @return List<UsuarioBean>
	*/
	@GetMapping("/{idUsuario}")
	@ResponseBody
	@Operation(summary = "Obtiene un usuario por [idUsuario]")
	public UsuarioBean obtenerUsuario(@PathVariable Integer idUsuario) {
		return usuarioService.obtenerUsuario(idUsuario);
	}
	
	/**
	 * Registra un nuevo usuario
	 * @param UsuarioBean
	 * @return void
	*/
	@PostMapping 
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Registra un nuevo usuario")
	public void registrarUsuario(@RequestBody UsuarioBean usuario) {
		usuarioService.registrarUsuario(usuario);
	}
	
	/**
	 * Elimina un usuario por [idUsuario]
	 * @param Integer
	 * @return void
	*/
	@DeleteMapping("/{idUsuario}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Elimina un usuario por [idUsuario]")
	public void eliminarUsuario(@PathVariable Integer idUsuario) {
		usuarioService.eliminarUsuario(idUsuario);
	}
	
	/**
	 * Actualiza la informacion de un usuario
	 * @param UsuarioBean
	 * @return void
	*/
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Actualiza la informacion de un usuario")
	public void actualiarUsuario(@RequestBody UsuarioBean usuario) {
		usuarioService.actualizarUsuario(usuario);
	}
	
	/**
	 * Actualiza la edad de un usuarioo
	 * @param Integer 
	 * @param UsuarioBean
	 * @return void
	*/
	@PatchMapping("/{idUsuario}")
	@Operation(summary = "Actualiza la edad de un usuario")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void actualiarEdadUsuario(@PathVariable Integer idUsuario, @RequestBody UsuarioBean usuario) {
		usuarioService.actualiarEdadUsuario(idUsuario,usuario.getEdad());
	}
}

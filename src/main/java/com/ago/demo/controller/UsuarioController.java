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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	@Qualifier("UsuarioService")
	private UsuarioService usuarioService;

	@GetMapping
	@ResponseBody
	public List<UsuarioBean> obtenerUsuario() {		
		return usuarioService.obtenerUsuarios();
	}
	
	
	@GetMapping("/{idUsuario}")
	@ResponseBody
	public UsuarioBean obtenerUsuario(@PathVariable Integer idUsuario) {
		return usuarioService.obtenerUsuario(idUsuario);
	}
	
	@PostMapping 
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registrarUsuario(@RequestBody UsuarioBean usuario) {
		usuarioService.registrarUsuario(usuario);
	}
	
	@DeleteMapping("/{idUsuario}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarUsuario(@PathVariable Integer idUsuario) {
		usuarioService.eliminarUsuario(idUsuario);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void actualiarUsuario(@RequestBody UsuarioBean usuario) {
		usuarioService.actualizarUsuario(usuario);
	}
	
	@PatchMapping("/{idUsuario}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void actualiarEdadUsuario(@PathVariable Integer idUsuario, @RequestBody UsuarioBean usuario) {
		usuarioService.actualiarEdadUsuario(idUsuario,usuario.getEdad());
	}
}

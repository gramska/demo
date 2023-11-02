package com.ago.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ago.demo.bean.UsuarioBean;
import com.ago.demo.exception.ConflictException;
import com.ago.demo.exception.NotFoundException;
import com.ago.demo.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	private List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();

	public List<UsuarioBean> obtenerUsuarios(){
		return usuarios;
	}

	@Override
	public UsuarioBean obtenerUsuario(Integer idUsuario) {
		UsuarioBean usuario = null;

		for(UsuarioBean busqueda: usuarios) {
			if(busqueda.getIdUsuario() == idUsuario) {
				usuario = busqueda;
				break;
			}
		}
		
		if(usuario == null) {
			throw new NotFoundException("Se encontro ningun usuario con id: " + idUsuario);
		}

		return usuario;
	}

	@Override
	public void registrarUsuario(UsuarioBean usuario) {
		usuarios.forEach(busqueda -> {
			if(busqueda.getIdUsuario() == usuario.getIdUsuario())
				throw new ConflictException("el idUsuario ya se encuentra registrado");
		});
		usuarios.add(usuario);
	}

	@Override
	public void eliminarUsuario(Integer idUsuario) {
		Integer indiceEncontrado = null;
				
		for(Integer indice = 0; usuarios.size() > indice; indice++) {
			if(usuarios.get(indice).getIdUsuario() == idUsuario) {
				indiceEncontrado = indice;
			}
		}
		
		if(indiceEncontrado == null) {
			throw new NotFoundException("Se encontro ningun usuario con id: " + idUsuario);
		}else {
			usuarios.remove((int)indiceEncontrado);
		}
	}

	@Override
	public void actualizarUsuario(UsuarioBean usuario) {
		Integer indiceEncontrado = null;
		
		for(Integer indice = 0; usuarios.size() > indice; indice++) {
			if(usuarios.get(indice).getIdUsuario() == usuario.getIdUsuario()) {
				indiceEncontrado = indice;
			}
		}
		
		if(indiceEncontrado == null) {
			throw new NotFoundException("Se encontro ningun usuario con id: " + usuario.getIdUsuario());
		}else {
			usuarios.set((int)indiceEncontrado,usuario);
		}
	}

	@Override
	public void actualiarEdadUsuario(Integer idUsuario, Integer edad) {
		Integer indiceEncontrado = null;
		
		for(Integer indice = 0; usuarios.size() > indice; indice++) {
			if(usuarios.get(indice).getIdUsuario() == idUsuario) {
				indiceEncontrado = indice;
			}
		}
		
		if(indiceEncontrado == null) {
			throw new NotFoundException("Se encontro ningun usuario con id: " + idUsuario);
		}else {
			usuarios.get((int)indiceEncontrado).setEdad(edad);
		}
		
	}
}

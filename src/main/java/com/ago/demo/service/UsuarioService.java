package com.ago.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ago.demo.bean.UsuarioBean;

@Component
public interface UsuarioService {

	public List<UsuarioBean> obtenerUsuarios();
	public UsuarioBean obtenerUsuario(Integer idUsuario);
	public void registrarUsuario(UsuarioBean usuario);
	public void eliminarUsuario(Integer idUsuario);
	public void actualizarUsuario(UsuarioBean usuario);
	public void actualiarEdadUsuario(Integer idUsuario, Integer edad);
}

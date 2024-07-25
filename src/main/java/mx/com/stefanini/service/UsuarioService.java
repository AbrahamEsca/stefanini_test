package mx.com.stefanini.service;

import mx.com.stefanini.model.RequestActualizarUsuarioBean;
import mx.com.stefanini.model.RequestRegistrarUsuarioBean;
import mx.com.stefanini.model.ResponseConsultarUsuariosBean;
import mx.com.stefanini.model.ResponseGenericBean;

public interface UsuarioService {
	
	public ResponseGenericBean registrarUsuario(RequestRegistrarUsuarioBean request);
	
	public ResponseGenericBean eliminarUsuario(Integer idUsuario);
	
	public ResponseConsultarUsuariosBean consultarUsuarios(String usuario, Integer indexPage, Integer defaultRows);
	
	public ResponseGenericBean consultarUsuario(Integer idUsuario);
	
	public ResponseGenericBean actualizarUsuario(RequestActualizarUsuarioBean request);
	
	public ResponseGenericBean actualizarNombre(Integer idUsuario, String nombre);

}

package mx.com.stefanini.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.stefanini.entity.UsuarioEntity;
import mx.com.stefanini.model.RequestActualizarUsuarioBean;
import mx.com.stefanini.model.RequestRegistrarUsuarioBean;
import mx.com.stefanini.model.ResponseConsultarUsuariosBean;
import mx.com.stefanini.model.ResponseGenericBean;
import mx.com.stefanini.model.ResponseUsuarioBean;
import mx.com.stefanini.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public ResponseGenericBean registrarUsuario(RequestRegistrarUsuarioBean request) {
		LOGGER.info("Inicio del metodo registrarUsuario del service");
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();

		try {
			UsuarioEntity usuarioEntity = new UsuarioEntity();
			usuarioEntity.setNombre(request.getNombre());
			usuarioEntity.setCorreoElectronico(request.getCorreoElectronico());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String passwordCifrada = encoder.encode(request.getPassword());
			usuarioEntity.setPassword(passwordCifrada);

			usuarioRepository.save(usuarioEntity);
			
			responseGenericBean.setCodigo(0);
			responseGenericBean.setMessage("Usuario guardado exitosamente");
		} catch(Exception e) {
			LOGGER.error("Ocurrio un error en el metodo registrarUsuario: ", e);
			responseGenericBean.setCodigo(1);
			responseGenericBean.setMessage("Ocurrió un error al registrar usuario");		
		}
		
		LOGGER.info("Fin del metodo registrarUsuario del service");
		return responseGenericBean;
	}
	
	@Override
	public ResponseGenericBean eliminarUsuario(Integer idUsuario) {
		LOGGER.info("Inicio del metodo eliminarUsuario del service");
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();

		try {
			usuarioRepository.deleteById(idUsuario);
			
			responseGenericBean.setCodigo(0);
			responseGenericBean.setMessage("Usuario eliminado exitosamente");
		} catch(Exception e) {
			LOGGER.error("Ocurrio un error en el metodo eliminarUsuario: ", e);
			responseGenericBean.setCodigo(1);
			responseGenericBean.setMessage("Ocurrió un error al eliminar usuario");		
		}
		
		LOGGER.info("Fin del metodo eliminarUsuario del service");
		return responseGenericBean;
	}
	
	public ResponseConsultarUsuariosBean consultarUsuarios(String usuario, Integer indexPage, Integer defaultRows){
		LOGGER.info("Inicio de metodo consultarUsuarios del service");
		
		ResponseConsultarUsuariosBean responseConsultarUsuariosBean = new ResponseConsultarUsuariosBean();
		
		Pageable pageable = PageRequest.of(indexPage, defaultRows);
		
		List<ResponseUsuarioBean> responseUsuariosBeanList = new ArrayList<>();
		Page<UsuarioEntity>       usuarioEntityPage        = usuarioRepository.findByNombreContainingIgnoringCaseOrderByIdUsuarioAsc(usuario, pageable);
		
		if(!usuarioEntityPage.getContent().isEmpty()) {
			List<UsuarioEntity> usuarioEntityList = usuarioEntityPage.getContent();

			usuarioEntityList.forEach(usuarioEntity -> {
				ResponseUsuarioBean responseUsuarioBean = new ResponseUsuarioBean(usuarioEntity);
				
				responseUsuariosBeanList.add(responseUsuarioBean);
			});
			
			responseConsultarUsuariosBean.setTotalPage(usuarioEntityPage.getTotalPages());
			responseConsultarUsuariosBean.setTotalRows((int) usuarioEntityPage.getTotalElements());
		}
		
		responseConsultarUsuariosBean.setUsuariosList(responseUsuariosBeanList);
		
		LOGGER.info("Fin de metodo consultarUsuarios del service");
		return responseConsultarUsuariosBean;
	}
	
	@Override
	public ResponseGenericBean consultarUsuario(Integer idUsuario) {
		LOGGER.info("Inicio del metodo consultarUsuario del service");
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();

		UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).orElse(null);
		if(usuarioEntity == null) {
			responseGenericBean.setCodigo(1);
			responseGenericBean.setMessage("No se encontro usuario con el dato proporcionado");
			
			return responseGenericBean;
		}
		
		responseGenericBean.setData(new ResponseUsuarioBean(usuarioEntity));
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Usuario consultado exitosamente");

		LOGGER.info("Fin del metodo consultarUsuario del service");
		return responseGenericBean;
	}
	
	@Override
	public ResponseGenericBean actualizarUsuario(RequestActualizarUsuarioBean request) {
		LOGGER.info("Inicio del metodo actualizarUsuario del service");
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();

		try {
			UsuarioEntity usuarioEntity = usuarioRepository.findById(request.getIdUsuario()).orElse(null);
			
			if(usuarioEntity != null) {
				usuarioEntity.setNombre(request.getNombre());
				usuarioEntity.setCorreoElectronico(request.getCorreoElectronico());
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String passwordCifrada = encoder.encode(request.getPassword());
				usuarioEntity.setPassword(passwordCifrada);

				usuarioRepository.save(usuarioEntity);
			}
			
			responseGenericBean.setCodigo(0);
			responseGenericBean.setMessage("Usuario actualizado exitosamente");
		} catch(Exception e) {
			LOGGER.error("Ocurrio un error en el metodo actualizarUsuario: ", e);
			responseGenericBean.setCodigo(1);
			responseGenericBean.setMessage("Ocurrió un error al actualizar usuario");		
		}
		
		LOGGER.info("Fin del metodo actualizarUsuario del service");
		return responseGenericBean;
	}
	
	@Override
	public ResponseGenericBean actualizarNombre(Integer idUsuario, String nombre) {
		LOGGER.info("Inicio del metodo actualizarNombre del service");
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();

		try {
			UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).orElse(null);
			
			if(usuarioEntity != null) {
				usuarioEntity.setNombre(nombre);

				usuarioRepository.save(usuarioEntity);
			}
			
			responseGenericBean.setCodigo(0);
			responseGenericBean.setMessage("Nombre del usuario actualizado exitosamente");
		} catch(Exception e) {
			LOGGER.error("Ocurrio un error en el metodo actualizarNombre: ", e);
			responseGenericBean.setCodigo(1);
			responseGenericBean.setMessage("Ocurrió un error al actualizar el nombre del usuario");		
		}
		
		LOGGER.info("Fin del metodo actualizarNombre del service");
		return responseGenericBean;
	}

}

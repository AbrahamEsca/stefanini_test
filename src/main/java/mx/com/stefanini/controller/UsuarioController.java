package mx.com.stefanini.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mx.com.stefanini.model.RequestActualizarUsuarioBean;
import mx.com.stefanini.model.RequestRegistrarUsuarioBean;
import mx.com.stefanini.model.ResponseConsultarUsuariosBean;
import mx.com.stefanini.model.ResponseGenericBean;
import mx.com.stefanini.service.UsuarioService;

@RestController
@RequestMapping("/stefanini")
@Validated
@Tag(name = "Operaciones con usuarios", description = "API para operar usuarios")
public class UsuarioController {
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Descripcion: Servicio para registrar un usuario
	 * 
	 * @param RequestRegistrarUsuarioBean Body con los datos del nuevo usuario
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@PostMapping("/usuarios")
	public ResponseEntity<ResponseGenericBean> registrarUsuario(@RequestBody RequestRegistrarUsuarioBean request) {
		LOGGER.info("Inicio del metodo registrarUsuario del controlador");
		
		ResponseGenericBean responseGenericBean = usuarioService.registrarUsuario(request);
		
		if(responseGenericBean.getCodigo() != 0) {
			return ResponseEntity.ok(responseGenericBean);
		}
		
		LOGGER.info("Fin del metodo registrarUsuario del controlador");
		return new ResponseEntity<>(responseGenericBean, HttpStatus.CREATED);
	}
	
	/**
	 * Descripcion: Servicio para eliminar un usuario
	 * 
	 * @param idUsuario Dato del usuario a eliminar
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@DeleteMapping("/usuarios")
	public ResponseEntity<ResponseGenericBean> eliminarUsuario(@RequestParam @NotNull @Positive Integer idUsuario) {
		LOGGER.info("Inicio del metodo eliminarUsuario del controlador");
		
		ResponseGenericBean responseGenericBean = usuarioService.eliminarUsuario(idUsuario);
		
		LOGGER.info("Fin del metodo eliminarUsuario del controlador");
		return ResponseEntity.ok(responseGenericBean);
	}
	
	/**
	 * Descripcion: Servicio para consultar una lista de usuarios
	 * 
	 * @param nombre Nombre de usuarios a buscar
	 * 
	 * @param indexPage Pagina que se requiere
	 * 
	 * @param defaultRows Numero de usuarios por pagina
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@GetMapping("/lista_usuarios")
	public ResponseEntity<ResponseConsultarUsuariosBean> consultarUsuarios(@RequestParam @NotNull String nombre, 
			                                                          @RequestParam @NotNull @Positive Integer indexPage, 
			                                                          @RequestParam @NotNull @Positive Integer defaultRows) {
		LOGGER.info("Inicio del metodo consultarUsuarios del controlador");
		
		ResponseConsultarUsuariosBean responseConsultarUsuariosBean = usuarioService.consultarUsuarios(nombre, indexPage, defaultRows);
		
		if(responseConsultarUsuariosBean.getUsuariosList().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		LOGGER.info("Fin del metodo consultarUsuarios del controlador");
		return ResponseEntity.ok(responseConsultarUsuariosBean);
	}
	
	/**
	 * Descripcion: Servicio para consultar un usuario
	 * 
	 * @param idUsuario Dato del usuario a consultar
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@GetMapping("/usuarios")
	public ResponseEntity<ResponseGenericBean> consultarUsuario(@RequestParam @NotNull @Positive Integer idUsuario) {
		LOGGER.info("Inicio del metodo consultarUsuario del controlador");
		
		ResponseGenericBean responseGenericBean = usuarioService.consultarUsuario(idUsuario);
		
		LOGGER.info("Fin del metodo consultarUsuario del controlador");
		return ResponseEntity.ok(responseGenericBean);
	}
	
	/**
	 * Descripcion: Servicio para actualizar un usuario
	 * 
	 * @param RequestActualizarUsuarioBean Body con los datos nuevos del usuario
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@PutMapping("/usuarios")
	public ResponseEntity<ResponseGenericBean> actualizarUsuario(@RequestBody RequestActualizarUsuarioBean request) {
		LOGGER.info("Inicio del metodo actualizarUsuario del controlador");
		
		ResponseGenericBean responseGenericBean = usuarioService.actualizarUsuario(request);
		
		LOGGER.info("Fin del metodo actualizarUsuario del controlador");
		return ResponseEntity.ok(responseGenericBean);
	}
	
	/**
	 * Descripcion: Servicio para actualizar el nombre de un usuario
	 * 
	 * @param idUsuario Dato del usuario que se requiere actualizar
	 * 
	 * @param nombre Dato con el nuevo nombre del usuario
	 * 
	 * @return ResponseEntity Respuesta del servicio
	 * 
	 **/
	@PatchMapping("/usuarios")
	public ResponseEntity<ResponseGenericBean> actualizarNombre(@RequestParam @NotNull @Positive Integer idUsuario, @RequestParam @NotNull String nombre) {
		LOGGER.info("Inicio del metodo actualizarNombre del controlador");
		
		ResponseGenericBean responseGenericBean = usuarioService.actualizarNombre(idUsuario, nombre);
		
		LOGGER.info("Fin del metodo actualizarNombre del controlador");
		return ResponseEntity.ok(responseGenericBean);
	}

}

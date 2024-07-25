package mx.com.stefanini.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mx.com.stefanini.model.RequestActualizarUsuarioBean;
import mx.com.stefanini.model.RequestRegistrarUsuarioBean;
import mx.com.stefanini.model.ResponseConsultarUsuariosBean;
import mx.com.stefanini.model.ResponseGenericBean;
import mx.com.stefanini.model.ResponseUsuarioBean;
import mx.com.stefanini.service.UsuarioService;

@SpringBootTest
public class UsuarioControllerTest {

	@Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;
    
	@Test	
	public void testRegistrarUsuario() {
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Usuario guardado");
		
		when(usuarioService.registrarUsuario(any(RequestRegistrarUsuarioBean.class))).thenReturn(responseGenericBean);
		
		ResponseEntity<ResponseGenericBean> resp = usuarioController.registrarUsuario(new RequestRegistrarUsuarioBean());
		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}
	
	@Test	
	public void testEliminarUsuario() {
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Usuario eliminado");
		
		when(usuarioService.eliminarUsuario(anyInt())).thenReturn(responseGenericBean);
		
		ResponseEntity<ResponseGenericBean> resp = usuarioController.eliminarUsuario(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testConsultarUsuarios() {
		ResponseConsultarUsuariosBean responseConsultarUsuariosBean = new ResponseConsultarUsuariosBean();
		responseConsultarUsuariosBean.setTotalPage(1);
		responseConsultarUsuariosBean.setTotalRows(1);
		List<ResponseUsuarioBean> usuariosList = new ArrayList<>();
		usuariosList.add(new ResponseUsuarioBean(1, "Abraham", "abra@gmail.com", "1234"));
		responseConsultarUsuariosBean.setUsuariosList(usuariosList);
		
		when(usuarioService.consultarUsuarios(anyString(), anyInt(), anyInt())).thenReturn(responseConsultarUsuariosBean);
		
		ResponseEntity<ResponseConsultarUsuariosBean> resp = usuarioController.consultarUsuarios("", 0, 10);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testConsultarUsuario() {
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Usuario eliminado");
		responseGenericBean.setData(new ResponseUsuarioBean(1, "Abraham", "abra@gmail.com", "1234"));
		
		when(usuarioService.consultarUsuario(anyInt())).thenReturn(responseGenericBean);
		
		ResponseEntity<ResponseGenericBean> resp = usuarioController.consultarUsuario(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testActualizarUsuario() {
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Usuario actualizado");
		
		when(usuarioService.actualizarUsuario(any(RequestActualizarUsuarioBean.class))).thenReturn(responseGenericBean);
		
		ResponseEntity<ResponseGenericBean> resp = usuarioController.actualizarUsuario(new RequestActualizarUsuarioBean());
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testActualizarNombre() {
		ResponseGenericBean responseGenericBean = new ResponseGenericBean();
		responseGenericBean.setCodigo(0);
		responseGenericBean.setMessage("Nombre de usuario actualizado");
		
		when(usuarioService.actualizarNombre(anyInt(), anyString())).thenReturn(responseGenericBean);
		
		ResponseEntity<ResponseGenericBean> resp = usuarioController.actualizarNombre(1, "Abraham");
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

}

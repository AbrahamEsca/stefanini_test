package mx.com.stefanini.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import mx.com.stefanini.entity.UsuarioEntity;
import mx.com.stefanini.model.RequestActualizarUsuarioBean;
import mx.com.stefanini.model.RequestRegistrarUsuarioBean;
import mx.com.stefanini.model.ResponseConsultarUsuariosBean;
import mx.com.stefanini.model.ResponseGenericBean;
import mx.com.stefanini.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

	@Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;
    
	@Test	
	public void testRegistrarUsuario() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setIdUsuario(1);
		usuarioEntity.setNombre("Abraham Escalante");
		usuarioEntity.setCorreoElectronico("abram3131@hotmail.com");
		usuarioEntity.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		
		when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
		
		RequestRegistrarUsuarioBean request = new RequestRegistrarUsuarioBean();
		request.setNombre("Abraham Escalante");
		request.setCorreoElectronico("abram3131@hotmail.com");
		request.setPassword("Hol4Mund0*");
		ResponseGenericBean responseGenericBean = usuarioServiceImpl.registrarUsuario(request);
		
		assertEquals(0, responseGenericBean.getCodigo());
	}
	
	@Test	
	public void testEliminarUsuario() {
		doNothing().when(usuarioRepository).deleteById(1);
		
		ResponseGenericBean responseGenericBean = usuarioServiceImpl.eliminarUsuario(1);
		
		assertEquals(0, responseGenericBean.getCodigo());
	}
	
	@Test	
	public void testConsultarUsuarios() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setIdUsuario(1);
		usuarioEntity.setNombre("Abraham Escalante");
		usuarioEntity.setCorreoElectronico("abram3131@hotmail.com");
		usuarioEntity.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		List<UsuarioEntity> usuarios = new ArrayList<>();
		usuarios.add(usuarioEntity);
		
		Pageable pageable = PageRequest.of(0, 10);
        Page<UsuarioEntity> usuarioEntityPage = new PageImpl<>(usuarios, pageable, usuarios.size());
        
		when(usuarioRepository.findByNombreContainingIgnoringCaseOrderByIdUsuarioAsc(anyString(), any(Pageable.class))).thenReturn(usuarioEntityPage);
		
		ResponseConsultarUsuariosBean responseConsultarUsuariosBean = usuarioServiceImpl.consultarUsuarios("", 0, 10);
		
		assertNotNull(responseConsultarUsuariosBean.getUsuariosList());
	}
	
	@Test	
	public void testConsultarUsuario() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setIdUsuario(1);
		usuarioEntity.setNombre("Abraham Escalante");
		usuarioEntity.setCorreoElectronico("abram3131@hotmail.com");
		usuarioEntity.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		Optional<UsuarioEntity> usOpt = Optional.of(usuarioEntity);
		
		when(usuarioRepository.findById(1)).thenReturn(usOpt);
		
		ResponseGenericBean responseGenericBean = usuarioServiceImpl.consultarUsuario(1);
		
		assertEquals(0, responseGenericBean.getCodigo());
	}
	
	@Test	
	public void testActualizarUsuario() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setIdUsuario(1);
		usuarioEntity.setNombre("Abraham Escalante");
		usuarioEntity.setCorreoElectronico("abram3131@hotmail.com");
		usuarioEntity.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		Optional<UsuarioEntity> usOpt = Optional.of(usuarioEntity);
		when(usuarioRepository.findById(1)).thenReturn(usOpt);
		
		UsuarioEntity usuarioEntityAct = new UsuarioEntity();
		usuarioEntityAct.setIdUsuario(1);
		usuarioEntityAct.setNombre("Abraham Flores");
		usuarioEntityAct.setCorreoElectronico("abram453@hotmail.com");
		usuarioEntityAct.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		when(usuarioRepository.save(usuarioEntityAct)).thenReturn(usuarioEntityAct);
		
		RequestActualizarUsuarioBean request = new RequestActualizarUsuarioBean();
		request.setIdUsuario(1);
		request.setNombre("Abraham Flores");
		request.setCorreoElectronico("abram453@hotmail.com");
		request.setPassword("Hol4Mund0*");
		ResponseGenericBean responseGenericBean = usuarioServiceImpl.actualizarUsuario(request);
		
		assertEquals(0, responseGenericBean.getCodigo());
	}
	
	@Test	
	public void testActualizarNombre() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setIdUsuario(1);
		usuarioEntity.setNombre("Abraham Escalante");
		usuarioEntity.setCorreoElectronico("abram3131@hotmail.com");
		usuarioEntity.setPassword("$2a$10$N6xgv89BdXSEt1yfZclhz.QVoWTO5v0TGgvJz73CIwmajvsdf.l4O");
		Optional<UsuarioEntity> usOpt = Optional.of(usuarioEntity);
		when(usuarioRepository.findById(1)).thenReturn(usOpt);
		
		UsuarioEntity usuarioEntityAct = new UsuarioEntity();
		usuarioEntityAct.setIdUsuario(1);
		usuarioEntityAct.setNombre("Jose Luis");
		when(usuarioRepository.save(usuarioEntityAct)).thenReturn(usuarioEntityAct);
		
		ResponseGenericBean responseGenericBean = usuarioServiceImpl.actualizarNombre(1, "Jose Luis");
		
		assertEquals(0, responseGenericBean.getCodigo());
	}

}

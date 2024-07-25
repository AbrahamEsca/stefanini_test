package mx.com.stefanini.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.stefanini.entity.UsuarioEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUsuarioBean {

	private Integer idUsuario;

	private String nombre;

	private String  correoElectronico;

	private String  password;

	public ResponseUsuarioBean(UsuarioEntity usuarioEntity) {
		this.idUsuario = usuarioEntity.getIdUsuario();
		this.nombre = usuarioEntity.getNombre();
		this.correoElectronico = usuarioEntity.getCorreoElectronico();
		this.password = usuarioEntity.getPassword();
	}

}

package mx.com.stefanini.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_pk")
	private Integer idUsuario;
	
	@Column(name = "nombre")
	private String  nombre;
	
	@Column(name = "correo_electronico")
	private String  correoElectronico;
	
	@Column(name = "password")
	private String  password;

}

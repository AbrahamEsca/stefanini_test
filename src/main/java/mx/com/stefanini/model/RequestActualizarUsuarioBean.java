package mx.com.stefanini.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestActualizarUsuarioBean {
	
	@NotEmpty
	@NotNull
	@Positive
	private Integer idUsuario;
	
	@NotEmpty
	@NotNull
	private String  nombre;
	
	@NotEmpty
	@NotNull
	private String  correoElectronico;
	
	@NotEmpty
	@NotNull
	private String  password;

}

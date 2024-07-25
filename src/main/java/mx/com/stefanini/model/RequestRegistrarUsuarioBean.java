package mx.com.stefanini.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestRegistrarUsuarioBean {
	
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

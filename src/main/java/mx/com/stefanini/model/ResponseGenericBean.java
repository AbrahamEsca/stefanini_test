package mx.com.stefanini.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ResponseGenericBean {
	
	private String message;
	
	private Object data;
	
	private Integer codigo;

}

package mx.com.stefanini.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseConsultarUsuariosBean {
	    
	private Integer totalPage;
	
	private Integer totalRows;
	
	private List<ResponseUsuarioBean> usuariosList;

}

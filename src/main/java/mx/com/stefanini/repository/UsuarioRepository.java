package mx.com.stefanini.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.stefanini.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Integer> {
	
	public Page<UsuarioEntity> findByNombreContainingIgnoringCaseOrderByIdUsuarioAsc(String nombre, Pageable pageable);
	
}

package progresa.relacionalclase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.relacionalclase.entity.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findBycategoria(String categoria);
    boolean existsByCategoria(String categoria);
}

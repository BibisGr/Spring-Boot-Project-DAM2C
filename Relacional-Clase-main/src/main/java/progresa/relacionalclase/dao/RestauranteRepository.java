package progresa.relacionalclase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.relacionalclase.entity.Restaurante;

import java.util.Optional;

public interface RestauranteRepository
        extends JpaRepository<Restaurante, Long> {
    Optional<Restaurante> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

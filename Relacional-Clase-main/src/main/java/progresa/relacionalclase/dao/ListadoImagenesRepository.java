package progresa.relacionalclase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progresa.relacionalclase.entity.ListadoImgs;

import java.util.Optional;

public interface ListadoImagenesRepository extends JpaRepository<ListadoImgs,Long> {
    Optional<ListadoImgs> findByUrl(String url);
    boolean existsByUrl(String url);
}

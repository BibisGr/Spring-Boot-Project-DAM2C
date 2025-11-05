package progresa.relacionalclase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.relacionalclase.entity.ListadoImgs;

import java.util.Optional;
@RepositoryRestResource //faltaba por poner
public interface ListadoImagenesRepository extends JpaRepository<ListadoImgs,Long> {
    Optional<ListadoImgs> findByUrl(String url);
    boolean existsByUrl(String url);
}

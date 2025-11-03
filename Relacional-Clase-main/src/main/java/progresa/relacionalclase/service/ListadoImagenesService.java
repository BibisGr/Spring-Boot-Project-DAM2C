package progresa.relacionalclase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.relacionalclase.dao.ListadoImagenesRepository;
import progresa.relacionalclase.entity.ListadoImgs;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ListadoImagenesService {
    @Autowired
    private ListadoImagenesRepository listadoImagenesRepository;
    //lista todos los restaurantes
    public List<ListadoImgs> list(){
        return  listadoImagenesRepository.findAll();
    }
    //obtiene uno de los listados por id
    public Optional<ListadoImgs> findById(long id){
        return  listadoImagenesRepository.findById(id);
    }
    //obtiene uno de los listados por url
    public Optional<ListadoImgs> findByUrl(String url){
        return  listadoImagenesRepository.findByUrl(url);
    }
    //guarda el listado de imagenes
    public  void save(ListadoImgs listadoImgs){
        listadoImagenesRepository.save(listadoImgs);
    }
    //borra el restaurante suegun el id
    public  void delete(long id){
        listadoImagenesRepository.deleteById(id);
    }
    //pregunta si existe el listado por id
    public boolean existsById(long id){
        return listadoImagenesRepository.existsById(id);
    }
    //pregunta si existe el listado por url
    public boolean existsByUrl(String url){
        return listadoImagenesRepository.existsByUrl(url);
    }

}

package progresa.relacionalclase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.relacionalclase.dao.CategoriaRepository;
import progresa.relacionalclase.dao.ListadoImagenesRepository;
import progresa.relacionalclase.entity.Categoria;
import progresa.relacionalclase.entity.ListadoImgs;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class  CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    //lista todas las categorias
    public List<Categoria> list(){
        return  categoriaRepository.findAll();
    }
    //obtiene una de las categorias por id
    public Optional<Categoria> getById(long id){
        return  categoriaRepository.findById(id);
    }
    //obtiene una de las categorias por su tipo
    public Optional<Categoria> getByCategoria(String categoria){
        return  categoriaRepository.findBycategoria(categoria);
    }
    //guarda la categoria
    public  void save(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    //borra la categoria  segun el id
    public  void delete(long id){
        categoriaRepository.deleteById(id);
    }
    //pregunta si existe la categoria por ID
    public boolean existsById(long id){
        return categoriaRepository.existsById(id);
    }
    //pregunta si existe la categoria por el tipo de categoria ej, Vegetariana
    public boolean existsByCategoria(String categoria){
        return categoriaRepository.existsByCategoria(categoria);
    }

}

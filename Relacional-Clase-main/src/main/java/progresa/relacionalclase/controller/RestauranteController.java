package progresa.relacionalclase.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import progresa.relacionalclase.dao.ListadoImagenesRepository;
import progresa.relacionalclase.dto.ListadoImagenesDTO;
import progresa.relacionalclase.dto.Mensaje;
import progresa.relacionalclase.dto.RestauranteDTO;
import progresa.relacionalclase.entity.Categoria;
import progresa.relacionalclase.entity.Direccion;
import progresa.relacionalclase.entity.ListadoImgs;
import progresa.relacionalclase.entity.Restaurante;
import progresa.relacionalclase.service.CategoriaService;
import progresa.relacionalclase.service.ListadoImagenesService;
import progresa.relacionalclase.service.RestauranteService;

import java.beans.XMLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {
    @Autowired
    RestauranteService restauranteService;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    ListadoImagenesService listadoImagenesService;

    @GetMapping("/lista")
    public ResponseEntity<List<Restaurante>> List(){
        List<Restaurante> list = restauranteService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Restaurante> getById(@PathVariable("id") long id){
        if (!restauranteService.existsById(id))
            return new ResponseEntity(new Mensaje ("no esiste el restaurante"), HttpStatus.NOT_FOUND);
        if (restauranteService.getOne(id).isPresent()){
            Restaurante restaurante = restauranteService.getOne(id).get();
            return   new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("no esiste el restaurante"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Restaurante> getByName(@PathVariable("nombre") String nombre){
//        if(!restauranteService.existsByNombre(nombre))
//            return new ResponseEntity(new Mensaje ("no existe el restaurante"), HttpStatus.NOT_FOUND);
        if (restauranteService.getByNombre(nombre).isPresent()){
            Restaurante restaurante = restauranteService.getByNombre(nombre).get();
            return   new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("no existe el restaurante"), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RestauranteDTO restauranteDto) {
        if (StringUtils.isBlank(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (restauranteService.existsByNombre(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre YA EXISTE"), HttpStatus.BAD_REQUEST);

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDto.getNombre());

        //Control de Direccion
        Direccion direccion = new Direccion();
        //controlar que la calle no sea vac'ia
        if (StringUtils.isBlank(restauranteDto.getDireccion().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("la calle no puede estar vacia."),
                    HttpStatus.BAD_REQUEST);
        direccion.setCalle(restauranteDto.getDireccion().getCalle());
        if (StringUtils.isBlank(restauranteDto.getDireccion().getNumero()))
            return new ResponseEntity<>(
                    new Mensaje("el numero no puede estar vacio."),
                    HttpStatus.BAD_REQUEST);

        direccion.setNumero(restauranteDto.getDireccion().getNumero());
        direccion.setRestaurante(restaurante);
        restaurante.setDireccion(direccion);

        //control listado
        Set<ListadoImgs> imagenes = new HashSet<>();
        for (ListadoImagenesDTO listadoImg: restauranteDto.getImagenes()){
            if (StringUtils.isBlank(listadoImg.getUrl()))
                return new ResponseEntity<>(new Mensaje("la URL es obligatoria"),
                        HttpStatus.BAD_REQUEST);
            ListadoImgs newImagen = new ListadoImgs(listadoImg.getUrl());
            newImagen.setRestaurante(restaurante);
            imagenes.add(newImagen);
        }
        restaurante.setImagenes(imagenes);

        // control de categoria
//        Categoria categoria = null;
//        if(categoriaService.getByCategoria(restauranteDto.getCategoria()).isPresent()){
//            categoria = categoriaService.getByCategoria(restauranteDto.getCategoria()).get();
//        }
//        else {
//            if (StringUtils.isBlank(restauranteDto.getCategoria())) {
//                return new ResponseEntity<>(new Mensaje("el nombre de la categoria es obligatorio"), HttpStatus.BAD_REQUEST);
//            }
//            categoria = new Categoria(restauranteDto.getCategoria());
//        }
//        categoria.getListaRestaurante().add(restaurante);
//        restaurante.setCategoria(categoria);

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("restaurante creado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!restauranteService.existsById(id))
            return  new ResponseEntity(new Mensaje("el restaurante no existe"), HttpStatus.NOT_FOUND);
        restauranteService.delete(id);
        return new ResponseEntity<>(new Mensaje("el restaurante eliminado"), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody RestauranteDTO restauranteDto) { //aqui se puso el update por create
        if (StringUtils.isBlank(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (restauranteService.existsByNombre(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre YA EXISTE"), HttpStatus.BAD_REQUEST);

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDto.getNombre());

        //Control de Direccion
        Direccion direccion = new Direccion();
        //controlar que la calle no sea vac'ia
        if (StringUtils.isBlank(restauranteDto.getDireccion().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("la calle no puede estar vacia."),
                    HttpStatus.BAD_REQUEST);
        direccion.setCalle(restauranteDto.getDireccion().getCalle());
        if (StringUtils.isBlank(restauranteDto.getDireccion().getNumero()))
            return new ResponseEntity<>(
                    new Mensaje("el numero no puede estar vacio."),
                    HttpStatus.BAD_REQUEST);

        direccion.setNumero(restauranteDto.getDireccion().getNumero());
        direccion.setRestaurante(restaurante);
        restaurante.setDireccion(direccion);

        //control listado
        Set<ListadoImgs> imagenes = new HashSet<>();
        for (ListadoImagenesDTO listadoImg: restauranteDto.getImagenes()){
            if (StringUtils.isBlank(listadoImg.getUrl()))
                return new ResponseEntity<>(new Mensaje("la URL es obligatoria"),
                        HttpStatus.BAD_REQUEST);
            ListadoImgs  newImagen = new ListadoImgs(listadoImg.getUrl());
            newImagen.setRestaurante(restaurante);
            imagenes.add(newImagen);
        }


        // control de categoria
//        Categoria categoria = null;
//        if(categoriaService.getByCategoria(restauranteDto.getCategoria()).isPresent()){
//            categoria = categoriaService.getByCategoria(restauranteDto.getCategoria()).get();
//        }
//        else {
//            if (StringUtils.isBlank(restauranteDto.getCategoria())) {
//                return new ResponseEntity<>(new Mensaje("el nombre de la categoria es obligatorio"), HttpStatus.BAD_REQUEST);
//            }
//            categoria = new Categoria(restauranteDto.getCategoria());
//        }
//        categoria.getListaRestaurante().add(restaurante);
//        restaurante.setCategoria(categoria);

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("restaurante creado"), HttpStatus.OK);
    }


}

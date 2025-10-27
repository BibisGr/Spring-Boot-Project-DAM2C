package progresa.relacionalclase.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.relacionalclase.dto.Mensaje;
import progresa.relacionalclase.dto.RestauranteDTO;
import progresa.relacionalclase.entity.Direccion;
import progresa.relacionalclase.entity.Restaurante;
import progresa.relacionalclase.service.RestauranteService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {
    @Autowired
    RestauranteService restauranteService;

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
    public ResponseEntity<Restaurante> getByName(
            @PathVariable("nombre") String nombre){
        if(!restauranteService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje ("no existe el restaurante"), HttpStatus.NOT_FOUND);
        if (restauranteService.getByNombre(nombre).isPresent()){
            Restaurante restaurante = restauranteService.getByNombre(nombre).get();
            return  new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("no existe el restaurante"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RestauranteDTO restauranteDto) {
        if (StringUtils.isBlank(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (restauranteService.existsByNombre(restauranteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre YA EXISTE"), HttpStatus.BAD_REQUEST);

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDto.getNombre());

        Direccion direccion = new Direccion();
        //controlar que la calle no sea vac'ia
        if (StringUtils.isBlank(restauranteDto.getDireccionDto().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("la calle no puede estar vacia."),
                    HttpStatus.BAD_REQUEST);
        direccion.setCalle(restauranteDto.getDireccionDto().getCalle());
        if (StringUtils.isBlank(restauranteDto.getDireccionDto().getNumero()))
            return new ResponseEntity<>(
                    new Mensaje("el numero no puede estar vacio."),
                    HttpStatus.BAD_REQUEST);

        direccion.setNumero(restauranteDto.getDireccionDto().getNumero());
        direccion.setCalle(restauranteDto.getDireccionDto().getCalle());
        direccion.setRestaurante(restaurante);
        restaurante.setDireccion(direccion);

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
    public ResponseEntity<?> update(
            @PathVariable("id") long id,
            @RequestBody RestauranteDTO restauranteDTO){
        if (StringUtils.isBlank(restauranteDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("el " +
                    "nombre es obligatorio"),
                    HttpStatus.BAD_REQUEST);
        if (restauranteService.existsByNombre(
                restauranteDTO.getNombre()) &&
                restauranteService.getByNombre(restauranteDTO.
                                getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("" +
                    "el nombre YA EXISTE"), HttpStatus.BAD_REQUEST);
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDTO.getNombre());

        Direccion direccion = new Direccion();
        //controlar que la calle no sea vac'ia
        if (StringUtils.isBlank(restauranteDTO.getDireccionDto().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("la calle no puede estar vacia."),
                    HttpStatus.BAD_REQUEST);
        direccion.setCalle(restauranteDTO.getDireccionDto().getCalle());
        if (StringUtils.isBlank(restauranteDTO.getDireccionDto().getNumero()))
            return new ResponseEntity<>(
                    new Mensaje("el numero no puede estar vacio."),
                    HttpStatus.BAD_REQUEST);

        direccion.setNumero(restauranteDTO.getDireccionDto().getNumero());
        direccion.setCalle(restauranteDTO.getDireccionDto().getCalle());
        direccion.setRestaurante(restaurante);
        restaurante.setDireccion(direccion);

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("restaurante creado"), HttpStatus.OK);

    }


}

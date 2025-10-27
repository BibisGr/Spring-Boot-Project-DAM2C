package progresa.relacionalclase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.relacionalclase.dao.DireccionRepository;
import progresa.relacionalclase.entity.Direccion;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;
    public Optional<Direccion> getByNumero(String numero){
        return direccionRepository.findByNumero(numero);
    }
    public boolean existsByNumero(String numero){
        return direccionRepository.existsByNumero(numero);
    }
    public Optional<Direccion> getByCalle(String calle){
        return direccionRepository.findByCalle(calle);
    }
    public boolean existsByCalle(String calle){
        return direccionRepository.existsByCalle(calle);
    }
    public List<Direccion> list(){
        return  direccionRepository.findAll();
    }
    public Optional<Direccion> getById(Long id){
        return direccionRepository.findById(id);
    }
    public void  save(Direccion direccion){
        direccionRepository.save(direccion);
    }
    public  void  delete(long id){
        direccionRepository.deleteById(id);
    }
}

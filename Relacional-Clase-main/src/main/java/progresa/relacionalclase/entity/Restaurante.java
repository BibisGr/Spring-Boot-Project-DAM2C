package progresa.relacionalclase.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity //conversion de la clase restaurante a un tipo entidad
@Table(name="restaurante") // creacion de la tabla

@Getter
@Setter

@NoArgsConstructor // lombook crea el constructor por defecto vacio
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Long id;

    @Column(name="nombre")
    private  String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_direccion") //clave ajena
//    @PrimaryKeyJoinColumn
    private Direccion direccion;


//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<ListadoImgs> imagenes;
//
//    @ManyToOne
//    @JoinColumn(name="categoria_id", nullable = false)
//    private categoria categoria;
}

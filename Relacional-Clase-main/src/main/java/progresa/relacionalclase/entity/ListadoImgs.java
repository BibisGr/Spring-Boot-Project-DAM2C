package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter

public class ListadoImgs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre")
    private String nombre;
    @Column(name="url")
    private  String url;

//    @ManyToOne
//    @JoinColumn(name="restaurante_id", nullable = false)
//    @JsonIgnore
//    private Restaurante restaurante;
}

package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resennas")
@Getter
@Setter

public class Resennas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Resenna")
    private String resenna;
    @Column(name = "Usuario")
    private String usuario;
    @Column(name = "cantEstrellas")
    private Integer cantEstrellas;

//    @ManyToOne
//    @JoinColumn(name = "id_restaurante")
//    @JsonIgnore
//    private Restaurante restaurante;
}

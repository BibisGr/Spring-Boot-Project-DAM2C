package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // falta este

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="categoria")
    private String categoria;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
//    @JsonIgnore
//    private Set<Restaurante> listaRestaurante;

    public Categoria(String categoria) {
        this.categoria = categoria;
//        listaRestaurante = new HashSet<>();
    }

}

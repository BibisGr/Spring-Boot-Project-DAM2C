package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="categoria")
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @JsonIgnore
    private Set<Restaurante> listaRestaurante;

}

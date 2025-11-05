package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor // esto lo agregue
@AllArgsConstructor // esto lo agregue

public class ListadoImgs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

//    @Column(name="nombre")
//    private String nombre;
    @Column(name="url")
    private String url;

    @ManyToOne
    @JoinColumn(name="restaurante_id", nullable = false)
    private Restaurante restaurante;

    //esto no lo habia agregado
    public ListadoImgs(String url) {
        this.url = url;
    }
}

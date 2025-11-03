package progresa.relacionalclase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String calle;
    @Column
    private String numero;
    @Column
    private String codigoPostal;

    @JsonIgnore
    @OneToOne(mappedBy = "direccion")
    private Restaurante restaurante;

    public Direccion(String calle, String numero, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }
}

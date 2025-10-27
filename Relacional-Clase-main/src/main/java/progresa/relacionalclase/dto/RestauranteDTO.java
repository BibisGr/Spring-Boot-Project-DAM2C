package progresa.relacionalclase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    @NotBlank
    private String nombre;
    private DireccionDto direccionDto;
}

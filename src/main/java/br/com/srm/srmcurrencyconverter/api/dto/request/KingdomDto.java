package br.com.srm.srmcurrencyconverter.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KingdomDto {

    @JsonProperty("name")
    @NotNull(message = "O nome do reino é obrigatório")
    @NotEmpty(message = "O nome do reino é obrigatório")
    private String name;

    @JsonProperty("race")
    @NotNull(message = "A raça é obrigatória")
    @NotEmpty(message = "A raça é obrigatória")
    private String race;

    @JsonProperty("specialty")
    @NotNull(message = "A especialidade do reino é obrigatória")
    @NotEmpty(message = "A especialidade do reino é obrigatória")
    private String specialty;
}

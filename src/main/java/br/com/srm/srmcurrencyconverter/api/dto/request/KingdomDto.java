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
    @NotNull(message = "Kingdom name is required")
    @NotEmpty(message = "Kingdom name cannot be empty")
    private String name;

    @JsonProperty("race")
    @NotNull(message = "Race is required")
    @NotEmpty(message = "Race cannot be empty")
    private String race;

    @JsonProperty("specialty")
    @NotNull(message = "Kingdom specialty is required")
    @NotEmpty(message = "Kingdom specialty cannot be empty")
    private String specialty;
}

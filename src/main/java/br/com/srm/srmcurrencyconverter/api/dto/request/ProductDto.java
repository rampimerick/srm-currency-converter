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
public class ProductDto {

    @JsonProperty("name")
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @JsonProperty("description")
    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;
}

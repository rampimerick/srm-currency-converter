package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Kingdom {

    @Id
    private Integer kingdomId;
    private String name;
    private String race;
    private String specialty;
}

package br.com.srm.srmcurrencyconverter.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kingdoms")
public class Kingdom {

    @Id
    @Column(name = "kingdom_id")
    private Integer kingdomId;
    @Column()
    private String name;
    @Column()
    private String race;
    @Column()
    private String specialty;
}

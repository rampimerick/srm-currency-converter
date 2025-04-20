package br.com.srm.srmcurrencyconverter.api.model;

import br.com.srm.srmcurrencyconverter.api.dto.request.KingdomDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kingdoms")
public class Kingdom {

    @Id
    @Column(name = "kingdom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kingdomId;
    @Column()
    private String name;
    @Column()
    private String race;
    @Column()
    private String specialty;

    public Kingdom(final KingdomDto kingdomDto) {
        this.name = kingdomDto.getName();
        this.race = kingdomDto.getRace();
        this.specialty = kingdomDto.getSpecialty();
    }
}

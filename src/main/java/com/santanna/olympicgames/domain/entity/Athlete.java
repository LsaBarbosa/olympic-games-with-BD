package com.santanna.olympicgames.domain.entity;

import com.santanna.olympicgames.domain.dto.*;
import com.santanna.olympicgames.domain.enums.Continent;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_athlete")
@Entity(name = "Athlete")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 65)
    private String name;

    @Min(40)
    private int weight;

    @Min(140)
    private double height;

    @Min(14)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Continent continent;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    //usar cache (desafio)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    //usar cache (desafio)
    private Sport sport;

    public Athlete(AthleteRequestDTO dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.age = dto.age();
        this.continent = dto.continent();
        this.country = dto.country();
        this.gender = dto.gender();
        this.sport = dto.sport();
        this.weight = dto.weight();
        this.height = dto.height();

    }

    public Athlete(AthleteResponseDTO dto) {
        this.name = dto.name();
        this.age = dto.age();
        this.continent = dto.continent();
        this.country = dto.country();
        this.gender = dto.gender();
        this.sport = dto.sport();
        this.weight = dto.weight();
        this.height = dto.height();

    }

    public Athlete(SportsDTO dto) {
        this.name = dto.name();
        this.country = dto.country();
        this.gender = dto.gender();
        this.sport = dto.sport();
    }

    public void updateAthleteData(UpdateAthleteDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.age() != null) {
            this.age = data.age();
        }
        if (!Double.isNaN(data.height())) {
            this.height = data.height();
        }
        if (data.weight() != null) {
            this.weight = data.weight();
        }
        if (data.continent() != null) {
            this.continent = data.continent();
        }
        if (data.country() != null) {
            this.country = data.country();
        }
        if (data.name() != null) {
            this.sport = data.sport();
        }
    }
}

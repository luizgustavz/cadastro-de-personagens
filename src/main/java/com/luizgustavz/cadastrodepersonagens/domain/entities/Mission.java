package com.luizgustavz.cadastrodepersonagens.domain.entities;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.FieldsNotNullOrBlankException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidNameFormatException;
import com.luizgustavz.cadastrodepersonagens.domain.validate.Patterns;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_mission")
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    private Difficulty difficulty;

    @OneToMany(mappedBy = "mission")
    private List<Person> persons = new ArrayList<>();

    public Mission() {}

    public Mission(
            String name,
            Difficulty difficulty
    ){
            assignName(name);
            assignDifficulty(difficulty);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Difficulty getDificulty() {
        return difficulty;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void assignName(final String name){
        notNull(name);
        validateFormat(name);
        this.name = name.trim().toLowerCase();
    }

    private void validateFormat(final String name){
        if (!Patterns.isValid(name)){
            throw new InvalidNameFormatException();
        }

        final int MIN = 3;
        final int MAX = 30;

        if (name.length() < MIN || name.length() > MAX){
            throw new InvalidNameFormatException();
        }
    }

    public void assignDifficulty(Difficulty difficulty){
        if (difficulty == null){
           difficulty =  Difficulty.INDEFINIDO;
        }
        this.difficulty = difficulty;
    }

    public void addPerson(Person person){
        persons.add(person);
        person.addMission(this);
    }

    private void notNull(String value){
        if(value == null || value.isEmpty()){
            throw new FieldsNotNullOrBlankException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if (!(o instanceof Mission mission)) return false;
        return Objects.equals(uuid, mission.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Mission: {\nid: " + uuid + "\nname: " + name + "\ndificulty: " + difficulty + "}";
    }

    @Serial
    private static final long serialVersionUID = 1L;


}

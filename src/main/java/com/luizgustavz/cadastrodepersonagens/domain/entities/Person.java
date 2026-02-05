package com.luizgustavz.cadastrodepersonagens.domain.entities;

import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.FieldsNotNullOrBlankException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidAgeArgument;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidNameFormatException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidUriException;
import com.luizgustavz.cadastrodepersonagens.domain.validate.Patterns;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "tb_person")
public class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    private int age;

    public Person(){};

    public Person(String name, String url, Rank rank, int age){
        assignName(name);
        assignUrl(url);
        assignRank(rank);
        assignAge(age);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Rank getRank() {
        return rank;
    }

    public int getAge() {
        return age;
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
        final int MAX = 60;

        if (name.length() < MIN || name.length() > MAX){
            throw new InvalidNameFormatException();
        }
    }

    public void assignUrl(final String url){
        notNull(url);
        validateUrl(url.trim());
        this.imageUrl = url.trim();
    }

    private void validateUrl(final String url){
        try {
            URI uri = new URI(url);
            if (uri.getScheme() == null){
                throw new InvalidUriException();
            }
        } catch (URISyntaxException e) {
            throw new InvalidUriException();
        }
    }

    public void assignRank(Rank rank){
        if (rank == null){
            rank = Rank.UNKNOWN;
        }
        this.rank = rank;
    }

    public void assignAge(final int age){
        validateAge(age);
        this.age = age;
    }

    private void validateAge(final int age){
        if (age <= 0 || age > 100){
            throw new InvalidAgeArgument();
        }
    }

    private void notNull(final String value){
        if (Objects.isNull(value)){
            throw new FieldsNotNullOrBlankException();
        }

        if (value.trim().isBlank()){
            throw new FieldsNotNullOrBlankException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person: {\nid: " + id + "\nname: " + name + "\nurl: " + imageUrl + "\nrank: " + rank + "\nage: " + age + "\n}";
    }

    @Serial
    private static final long serialVersionUID = 1L;
}

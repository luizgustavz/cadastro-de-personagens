package com.luizgustavz.cadastrodepersonagens.application.mapper;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public final Person toEntity(PersonRequest request){
        Person entity = new Person();
        entity.assignName(request.name());
        entity.assignUrl(request.imageUrl());
        entity.assignRank(request.rank());
        entity.assignAge(request.age());
        return entity;
    }

    public final PersonResponse toDto(Person person){
        PersonResponse response = new PersonResponse(
                person.getId(),
                person.getName(),
                person.getImageUrl(),
                person.getRank(),
                person.getAge()
        );
        return response;
    }



}

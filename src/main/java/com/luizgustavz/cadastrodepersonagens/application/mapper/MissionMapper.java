package com.luizgustavz.cadastrodepersonagens.application.mapper;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.MissionRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.MissionResponse;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public final Mission toEntity(MissionRequest request){
        Mission mission = new Mission();
        mission.assignName(request.name());
        mission.assignDifficulty(request.difficulty());
        return mission;
    }

    public final MissionResponse toDTO(Mission mission){
        return new MissionResponse(
                mission.getUuid(),
                mission.getName(),
                mission.getDificulty()
        );
    }




}

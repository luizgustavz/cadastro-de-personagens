package com.luizgustavz.cadastrodepersonagens.integration.mission;

import com.luizgustavz.cadastrodepersonagens.application.dto.request.MissionRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.MissionResponse;
import com.luizgustavz.cadastrodepersonagens.application.mapper.MissionMapper;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.MissionRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.MissionServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissionServicesIntegrationTest {

    @Autowired
    private MissionServices services;

    @Autowired
    private MissionRepository repository;

    @Autowired
    private MissionMapper mapper;

    @Test
    void deve_criar_missao_com_sucesso(){

        MissionRequest mission = new MissionRequest(
                "Roubar Amuleto",
                Difficulty.MEDIO
        );

        MissionResponse saved = services.createEntity(mission);
        var id = saved.uuid();

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(id, saved.uuid());
        Assertions.assertEquals("roubar amuleto", saved.name());
        Assertions.assertEquals(Difficulty.MEDIO, saved.difficulty());

        System.out.println(saved);
    }

    @Test
    void deve_salvar_apenas_1_registro_quando_persistido_com_sucesso(){

        MissionRequest mission = new MissionRequest(
                "Roubar Amuleto",
                Difficulty.MEDIO
        );

        MissionResponse saved = services.createEntity(mission);
        var id = saved.uuid();

        List<MissionResponse> missions = new ArrayList<>();
        missions.add(saved);

        Assertions.assertEquals(1, missions.size());
        Assertions.assertEquals(saved.uuid(), missions.getFirst().uuid());
    }

    @Test
    void deve_retornar_uma_missao_base_de_dados_com_sucesso(){

        MissionRequest mission = new MissionRequest(
                "Roubar Amuleto",
                Difficulty.MEDIO
        );

        MissionResponse saved = services.createEntity(mission);
        MissionResponse findMissionByID = services.findByID(saved.uuid());

        Assertions.assertNotNull(findMissionByID.uuid());
        Assertions.assertEquals(saved.uuid(), findMissionByID.uuid());
        Assertions.assertEquals("roubar amuleto", findMissionByID.name());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_uuid_nao_encontrado(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findByID(uuid));
    }

    @Test
    void deve_buscar_todas_entidades_com_sucesso(){

        MissionRequest mission1 = new MissionRequest(
                "Roubar Amuleto",
                Difficulty.MEDIO
        );

        MissionRequest mission2 = new MissionRequest(
                "Roubar Peteca",
                Difficulty.FACIL
        );

        MissionResponse saved1 = services.createEntity(mission1);
        MissionResponse saved2 = services.createEntity(mission2);

        List<MissionResponse> missions = services.findAll();

        Assertions.assertEquals(2, missions.size());
        Assertions.assertEquals(saved1.uuid(), missions.get(0).uuid());
        Assertions.assertEquals(saved2.uuid(), missions.get(1).uuid());
    }

    @Test
    void deve_retornar_lista_vazia_quando_nao_existe_registro_salvo(){

        List<MissionResponse> missions = services.findAll();

        Assertions.assertEquals(0,  missions.size());
        assertThat(services.findAll()).isEmpty();
    }

    @Test
    void deve_remover_registro_com_sucesso(){

        MissionRequest mission1 = new MissionRequest(
                "Roubar Amuleto",
                Difficulty.MEDIO
        );

        MissionResponse saved = services.createEntity(mission1);
        services.dropEntity(saved.uuid());

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findByID(saved.uuid()));
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_nao_encontrado(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.dropEntity(uuid));
    }
}

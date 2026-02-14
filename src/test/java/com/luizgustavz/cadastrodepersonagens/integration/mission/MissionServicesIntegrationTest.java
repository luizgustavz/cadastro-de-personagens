package com.luizgustavz.cadastrodepersonagens.integration.mission;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.MissionRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.MissionServicesImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissionServicesIntegrationTest {

    @Autowired
    private MissionServicesImpl services;

    @Autowired
    private MissionRepository repository;

    @Test
    void deve_criar_missao_com_sucesso(){

        Mission mission = new Mission();
        mission.assignName("Roubar Amuleto");
        mission.assignDifficulty(Difficulty.MEDIO);

        Mission saved = services.createEntity(mission);

        Assertions.assertNotNull(saved.getUuid());
        Assertions.assertNotNull(saved.getName());
        Assertions.assertNotNull(saved.getDificulty());

        System.out.println(saved);
    }

    @Test
    void deve_salvar_apenas_1_registro_quando_persistido_com_sucesso(){

        Mission mission = new Mission();
        mission.assignName("Roubar Amuleto");
        mission.assignDifficulty(Difficulty.MEDIO);

        List<Mission> missionList = new ArrayList<>();
        missionList.add(services.createEntity(mission));

        Assertions.assertEquals(1, missionList.size());
    }

    @Test
    void deve_retornar_uma_missao_base_de_dados_com_sucesso(){

        Mission mission = new Mission();
        mission.assignName("Roubar Amuleto");
        mission.assignDifficulty(Difficulty.MEDIO);
        Mission saved = services.createEntity(mission);

        Mission findByMission = services.findById(saved.getUuid());

        Assertions.assertNotNull(findByMission.getUuid());
        Assertions.assertEquals(saved.getUuid(), findByMission.getUuid());
        Assertions.assertEquals(saved.getName(), findByMission.getName());
        Assertions.assertEquals(saved.getDificulty(), findByMission.getDificulty());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_uuid_nao_encontrado(){

        UUID uuid = UUID.randomUUID();

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(uuid));
    }

    @Test
    void deve_buscar_todas_entidades_com_sucesso(){

        Mission one = new Mission();
        one.assignName("Roubar Amuleto");
        one.assignDifficulty(Difficulty.MEDIO);

        Mission two = new Mission();
        two.assignName("Roubar Relogio");
        two.assignDifficulty(Difficulty.FACIL);

        Mission savedOne = services.createEntity(one);
        Mission savedTwo = services.createEntity(two);

        List<Mission> recordMissoes = services.findAllEntities();

        Assertions.assertEquals(2, recordMissoes.size());
        Assertions.assertEquals(savedOne.getUuid(), recordMissoes.get(0).getUuid());
        Assertions.assertEquals(savedTwo.getUuid(), recordMissoes.get(1).getUuid());
    }

    @Test
    void deve_retornar_lista_vazia_quando_nao_existe_registro_salvo(){

        List<Mission> recordMissoes = services.findAllEntities();

        Assertions.assertEquals(0,  recordMissoes.size());
        Assertions.assertTrue(recordMissoes.isEmpty());
    }

    @Test
    void deve_buscar_missao_pelo_nome_com_sucesso(){

        Mission primeiro = new Mission();
        primeiro.assignName("Roubar Amuleto");
        primeiro.assignDifficulty(Difficulty.MEDIO);

        Mission segundo = new Mission();
        segundo.assignName("Roubar Relogio");
        segundo.assignDifficulty(Difficulty.FACIL);

        Mission primeiroSalvo = services.createEntity(primeiro);
        Mission segundoSalvo = services.createEntity(segundo);

        Mission recuperaEntityPeloNome = services.findByName("Roubar Amuleto");

        Assertions.assertNotNull(recuperaEntityPeloNome.getUuid());
        Assertions.assertEquals(primeiro.getUuid(), recuperaEntityPeloNome.getUuid());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_registro_nao_encontrado_pelo_nome(){

        Mission primeiro = new Mission();
        primeiro.assignName("Roubar Amuleto");
        primeiro.assignDifficulty(Difficulty.MEDIO);

        Mission primeiroSalvo = services.createEntity(primeiro);
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findByName("Roubar Relogio"));
    }

    @Test
    void deve_remover_registro_com_sucesso(){

        Mission primeiro = new Mission();
        primeiro.assignName("Roubar Amuleto");
        primeiro.assignDifficulty(Difficulty.MEDIO);

        Mission primeiroSalvo = services.createEntity(primeiro);

        services.dropEntity(primeiroSalvo.getUuid());

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(primeiroSalvo.getUuid()));
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_nao_encontrado(){

        UUID uuid = UUID.randomUUID();

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.dropEntity(uuid));
    }
}

package com.luizgustavz.cadastrodepersonagens.integration.person;

import com.luizgustavz.cadastrodepersonagens.CadastroDePersonagensApplication;
import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.application.mapper.PersonMapper;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.PersonRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.DataViolationNameException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServices;
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
@SpringBootTest(classes = CadastroDePersonagensApplication.class)
@ActiveProfiles("test")
public class PersonServicesIntegrationTest {

    @Autowired
    private PersonServices services;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    @Test
    void deve_salvar_personagem_com_sucesso(){

        PersonRequest request = new PersonRequest("Goku", "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc", Rank.A, 57);

        PersonResponse response = services.createEntity(request);

        Assertions.assertNotNull(response.id());
        Assertions.assertNotNull(response.name());
        Assertions.assertNotNull(response.imageUrl());
        Assertions.assertNotNull(response.rank());

        Assertions.assertEquals("goku",  response.name());
        Assertions.assertEquals("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",  response.imageUrl());
        Assertions.assertEquals(Rank.A,  response.rank());
        Assertions.assertEquals(57,  response.age());

        System.out.println(request.toString());
    }

    @Test
    void deve_lancar_DataViolationNameException_quando_nome_existe_na_base_de_dados(){

        PersonRequest request1 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonResponse response1 = services.createEntity(request1);

        PersonRequest request2 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);;

        Assertions.assertThrows(DataViolationNameException.class, () -> services.createEntity(request2));
    }

    @Test
    void deve_salvar_apenas_uma_entidade_quando_persistida(){

        PersonRequest request = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);;

        PersonResponse response = services.createEntity(request);


        List<PersonResponse> tb_persons = new ArrayList<>();
        tb_persons.add(response);

        Assertions.assertEquals(1, tb_persons.size());
        Assertions.assertNotNull(tb_persons.getFirst().id());
    }

    @Test
    void deve_buscar_apenas_uma_entidade_quando_findById_acionado_verificando_id_corresponde_a_chamada(){

        PersonRequest request = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);;

        PersonRequest request2 = new PersonRequest(
                "Goku Black",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);;

        PersonResponse saved1 = services.createEntity(request);
        PersonResponse saved2 = services.createEntity(request2);

        PersonResponse findByPerson1 = services.findById(saved1.id());

        Assertions.assertEquals(saved1.id(), findByPerson1.id());
        Assertions.assertEquals("goku", findByPerson1.name());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_informado_nao_existe_base_de_dados_ou_informado_erroneamente(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(uuid));
    }

    @Test
    void deve_retornar_todos_objetos_do_repositorio_quando_chamado_metodo_findAll(){

        PersonRequest request1 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonRequest request2 = new PersonRequest(
                "Goku Black",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonRequest request3 = new PersonRequest(
                "Goku Super Sayajin",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonResponse response1 = services.createEntity(request1);
        PersonResponse response2 = services.createEntity(request2);
        PersonResponse response3 = services.createEntity(request3);

        List<PersonResponse> listPerson = services.findAllEntities();

        Assertions.assertEquals(3, listPerson.size());
    }

    @Test
    void deve_retornar_uma_lista_vazia_quando_nao_tem_objetos_salvos(){

        List<PersonResponse> persons = services.findAllEntities();
        assertThat(persons).isEmpty();
    }

    @Test
    void deve_retornar_um_unico_objeto_quando_findByName_e_chamado(){

        PersonRequest request1 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonRequest request2 = new PersonRequest(
                "Goku Black",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.C,
                89);

        PersonResponse saved1 =  services.createEntity(request1);
        PersonResponse saved2 =  services.createEntity(request2);

        Assertions.assertDoesNotThrow(() -> services.findByName("goku"));

        PersonResponse findByName = services.findByName("goku");

        Assertions.assertEquals(saved1.id(), findByName.id());
        Assertions.assertEquals(saved1.name(), findByName.name());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_objeto_nao_existir_ou_nao_nome_errado() {

        PersonRequest request1 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonResponse saved = services.createEntity(request1);

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findByName("Vegeta"));
    }

    @Test
    void deve_deletar_person_com_sucesso(){

        PersonRequest request1 = new PersonRequest(
                "Goku",
                "https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc",
                Rank.A,
                57);

        PersonResponse saved = services.createEntity(request1);
        services.dropEntity(saved.id());

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(saved.id()));
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_nao_existe(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.dropEntity(uuid));
    }
}

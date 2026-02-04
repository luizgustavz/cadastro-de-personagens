package com.luizgustavz.cadastrodepersonagens.integration;

import com.luizgustavz.cadastrodepersonagens.CadastroDePersonagensApplication;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.domain.repositories.PersonRepository;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.DataViolationNameException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServicesImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@SpringBootTest(classes = CadastroDePersonagensApplication.class)
public class PersonServicesIntegrationTest {

    @Autowired
    private PersonServicesImpl services;

    @Autowired
    private PersonRepository repository;

    @Test
    void deve_salvar_personagem_com_sucesso(){

        Person person = new Person();

        person.assignName("Goku");
        person.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        person.assignRank(Rank.A);
        person.assignAge(47);

        Person saved = services.createEntity(person);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getName());
        Assertions.assertNotNull(saved.getImageUrl());
        Assertions.assertNotNull(saved.getRank());

        System.out.println(saved.toString());
    }

    @Test
    void deve_lancar_DataViolationNameException_quando_nome_existe_na_base_de_dados(){

        Person primeiroPerson = new Person();

        primeiroPerson.assignName("Goku");
        primeiroPerson.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        primeiroPerson.assignRank(Rank.A);
        primeiroPerson.assignAge(47);

        Person primeiroPersonSalvo = services.createEntity(primeiroPerson);

        Person segundoPerson = new Person();

        segundoPerson.assignName("Goku");
        segundoPerson.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        segundoPerson.assignRank(Rank.B);
        segundoPerson.assignAge(48);

        Assertions.assertThrows(DataViolationNameException.class, () -> services.createEntity(segundoPerson));

    }

    @Test
    void deve_salvar_apenas_uma_entidade_quando_persistida(){

        Person person = new Person();

        person.assignName("Goku");
        person.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        person.assignRank(Rank.B);
        person.assignAge(48);

        List<Person> tb_persons = new ArrayList<>();
        tb_persons.add(services.createEntity(person));

        Assertions.assertEquals(1, tb_persons.size());
    }

    @Test
    void deve_buscar_apenas_uma_entidade_quando_findById_acionado_verificando_id_corresponde_a_chamada(){

        Person primeiro = new Person();

        primeiro.assignName("Goku");
        primeiro.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        primeiro.assignRank(Rank.A);
        primeiro.assignAge(48);

        Person segundo = new Person();

        segundo.assignName("Goku Black");
        segundo.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        segundo.assignRank(Rank.B);
        segundo.assignAge(48);

        var savedPrimeiroPerson = services.createEntity(primeiro);
        var savedSegundoPerson = services.createEntity(segundo);

        Person findPerson = services.findById(savedPrimeiroPerson.getId());

        Assertions.assertEquals(savedPrimeiroPerson.getId(), findPerson.getId());
        Assertions.assertEquals(savedPrimeiroPerson.getName(), findPerson.getName());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_informado_nao_existe_base_de_dados_ou_informado_erroneamente(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(uuid));
    }

    @Test
    void deve_retornar_todos_objetos_do_repositorio_quando_chamado_metodo_findAll(){

        Person primeiro = new Person();

        primeiro.assignName("Goku");
        primeiro.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        primeiro.assignRank(Rank.A);
        primeiro.assignAge(48);

        Person segundo = new Person();

        segundo.assignName("Vegeta");
        segundo.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        segundo.assignRank(Rank.A);
        segundo.assignAge(48);

        Person terceiro = new Person();

        terceiro.assignName("Bulma");
        terceiro.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        terceiro.assignRank(Rank.A);
        terceiro.assignAge(48);

        var primeiroSalvo = services.createEntity(primeiro);
        var segundoSalvo = services.createEntity(segundo);
        var terceiroSalvo = services.createEntity(terceiro);

        List<Person> listPerson = services.findAllEntities();

        Assertions.assertEquals(3, listPerson.size());
    }

    @Test
    void deve_retornar_uma_lista_vazia_quando_nao_tem_objetos_salvos(){

        List<Person> persons = services.findAllEntities();
        assertThat(persons).isEmpty();
    }

    @Test
    void deve_retornar_um_unico_objeto_quando_findByName_e_chamado(){

        Person primeiroPerson = new Person();

        primeiroPerson.assignName("Goku");
        primeiroPerson.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        primeiroPerson.assignRank(Rank.A);
        primeiroPerson.assignAge(48);

        Person segundoPerson = new Person();

        segundoPerson.assignName("Goku Black");
        segundoPerson.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        segundoPerson.assignRank(Rank.B);
        segundoPerson.assignAge(48);

        var primeiroSaved = services.createEntity(primeiroPerson);
        var segundosSaved = services.createEntity(segundoPerson);

        Person person = services.findByName("Goku");

        Assertions.assertEquals(primeiroSaved.getName(), person.getName());
        Assertions.assertNotEquals(segundosSaved.getRank(), person.getRank());
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_objeto_nao_existir_ou_nao_nome_errado() {

        Person person = new Person();

        person.assignName("Goku Black");
        person.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        person.assignRank(Rank.B);
        person.assignAge(48);

        var personSaved = services.createEntity(person);

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findByName("Goku"));
        Assertions.assertEquals("goku black", personSaved.getName());
    }

    @Test
    void deve_deletar_person_com_sucesso(){

        Person person = new Person();

        person.assignName("Goku Black");
        person.assignUrl("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc");
        person.assignRank(Rank.B);
        person.assignAge(48);

        var saved = services.createEntity(person);
        services.dropEntity(saved.getId());

        Assertions.assertThrows(EntityNotFoundException.class, () -> services.findById(saved.getId()));
    }

    @Test
    void deve_lancar_EntityNotFoundException_quando_id_nao_existe(){

        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(EntityNotFoundException.class, () -> services.dropEntity(uuid));
    }






}

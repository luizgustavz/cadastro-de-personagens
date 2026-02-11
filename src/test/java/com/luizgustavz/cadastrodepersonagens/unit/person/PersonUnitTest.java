package com.luizgustavz.cadastrodepersonagens.unit.person;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.FieldsNotNullOrBlankException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidAgeArgument;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidNameFormatException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidUriException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonUnitTest {

    @Test
    void deve_criar_o_personagem_com_sucesso(){

        Person person = new Person(
                "kakarott",
                "https://imgs.search.brave.com/o7oPkZ9g_u432qZpsPCGjsNvNZ_4Qr2H3azJYPkZQ2U/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/",
                Rank.A,
                47);

        Assertions.assertNotNull(person);
        Assertions.assertEquals("kakarott", person.getName());
        Assertions.assertEquals("https://imgs.search.brave.com/o7oPkZ9g_u432qZpsPCGjsNvNZ_4Qr2H3azJYPkZQ2U/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/", person.getImageUrl());
        Assertions.assertEquals(Rank.A, person.getRank());
        Assertions.assertEquals(47, person.getAge());
        System.out.println(person.toString());
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_name_e_null(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignName(null));
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_name_e_enviado_vazio(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignName(""));
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_name_e_enviado_branco(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignName("                   "));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_name_contem_numeros(){

        Person person = new Person();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> person.assignName("K4karott"));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_name_contem_caracteres_especiais(){

        Person person = new Person();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> person.assignName("kak@rott"));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_name_contem_menos_de_3_caracteres(){

        Person person = new Person();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> person.assignName("zt"));
    }

    @Test
    void deve_criar_objeto_sem_espacos_inicio_fim(){

        Person person = new Person();
        person.assignName(" goku ");
        Assertions.assertEquals("goku", person.getName());
    }

    @Test
    void deve_criar_objeto_com_formato_caixa_baixa(){

        Person person = new Person();
        person.assignName("GOKU");
        Assertions.assertEquals("goku", person.getName());
    }

    // image

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_url_null(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignUrl(null));
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_url_enviada_vazia(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignUrl(""));
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_url_enviada_branco(){

        Person person = new Person();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> person.assignUrl("              "));
    }

    @Test
    void deve_remover_espacos_inicio_fim_url(){

        Person person = new Person();
        person.assignUrl(" https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc ");
        Assertions.assertEquals("https://imgs.search.brave.com/CsojGCwyLxS6Q5jqhkW9-Bmtc22Ph8596a_KeLgw964/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvc3Vw/ZXItc2FpeWFuLTQt/Z29rdS1kYnotNGst/bTRtamh2ZnluYzNo/M3U4Yi5qcGc", person.getImageUrl());
    }

    @Test
    void deve_lancar_InvalidUriException_quando_uri_informada_for_invalida(){

        Person person = new Person();
        Assertions.assertThrows(InvalidUriException.class, () -> person.assignUrl("google.com/imagem.png"));
    }

    @Test
    void deve_lancar_InvalidAgeArgumentException_quando_valor_informado_for_menor_ou_igual_zero(){

        Person person = new Person();
        Assertions.assertThrows(InvalidAgeArgument.class, () -> person.assignAge(-2));
    }

    @Test
    void deve_lancar_InvalidAgeArgumentException_quando_valor_informado_for_maior_que_100(){

        Person person = new Person();
        Assertions.assertThrows(InvalidAgeArgument.class, () -> person.assignAge(101));
    }

    @Test
    void deve_aceitar_age_valido(){

        Person person = new Person();

        Assertions.assertDoesNotThrow(() -> person.assignAge(1));
        Assertions.assertDoesNotThrow(() -> person.assignAge(50));
        Assertions.assertDoesNotThrow(() -> person.assignAge(100));
    }

}


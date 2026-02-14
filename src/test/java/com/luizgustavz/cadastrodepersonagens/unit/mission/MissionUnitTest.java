package com.luizgustavz.cadastrodepersonagens.unit.mission;

import com.luizgustavz.cadastrodepersonagens.domain.entities.Mission;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Difficulty;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.FieldsNotNullOrBlankException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidNameFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MissionUnitTest {

    @Test
    void deve_lancar_FieldsNotNUllOrBlankException_quando_campo_nome_enviado_valor_null() {

        Mission mission = new Mission();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> mission.assignName(null));
    }

    @Test
    void deve_lancar_FieldsNotNullOrBlankException_quando_nome_enviado_em_branco() {

        Mission mission = new Mission();
        Assertions.assertThrows(FieldsNotNullOrBlankException.class, () -> mission.assignName(""));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_nome_conter_menos_que_3_caracteres() {

        Mission mission = new Mission();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> mission.assignName("AB"));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_nome_conter_mais_de_60_caracteres() {

        Mission mission = new Mission();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> mission.assignName("MISSSSSSSSSSSSSSSSSSSSSSSSSSSSSSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoo"));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_nome_conter_numeros() {

        Mission mission = new Mission();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> mission.assignName("M1ssao"));
    }

    @Test
    void deve_lancar_InvalidNameFormatException_quando_nome_conter_caractetes_especiais(){

        Mission mission = new Mission();
        Assertions.assertThrows(InvalidNameFormatException.class, () -> mission.assignName("M1ss@o"));
    }

    @Test
    void deve_criar_nome_com_sucesso(){

        Mission mission = new Mission();
        Assertions.assertDoesNotThrow(() -> mission.assignName(" Missao "));
        Assertions.assertEquals("missao", mission.getName());
    }

    @Test
    void deve_criar_dificuldade_com_valor_indefinido_quando_valor_passado_e_null(){

        Mission mission = new Mission();
        mission.assignDifficulty(null);

        Assertions.assertNotNull(mission.getDificulty());
        Assertions.assertEquals(Difficulty.INDEFINIDO,  mission.getDificulty());
    }

    @Test
    void deve_criar_missao_com_sucesso(){
        Mission mission = new Mission();
        mission.assignName("Missao");
        mission.assignDifficulty(null);

        Assertions.assertNotNull(mission.getDificulty());
        Assertions.assertEquals(Difficulty.INDEFINIDO,  mission.getDificulty());
        Assertions.assertEquals("missao",  mission.getName());
    }
}

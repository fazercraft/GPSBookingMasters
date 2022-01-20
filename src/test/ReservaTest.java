package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tpgps.model.Reserva;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    Reserva res;

    @BeforeEach
    void setUp() {
        res = new Reserva("userTest","disciplina","hoje","1");
    }

    @Test
    void getNomeUser() {
        String nome = res.getNomeUser();
        assert nome.equalsIgnoreCase("userTest");
    }

    @Test
    void getDisciplinaUser() {
        String disc = res.getDisciplinaUser();
        assert disc.equalsIgnoreCase("disciplina");
    }

    @Test
    void setNomeUser() {
        res.setNomeUser("teste");
        assert res.getNomeUser().equalsIgnoreCase("teste");
    }

    @Test
    void setDisciplinaUser() {
        res.setDisciplinaUser("teste");
        assert res.getDisciplinaUser().equalsIgnoreCase("teste");
    }
}
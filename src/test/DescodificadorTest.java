package test;

import decode.Descodificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DescodificadorTest {

    Descodificador descodificador;

    @BeforeEach
    public void setup() {
        descodificador = new Descodificador();
        descodificador.back1Solucio(0);
    }

    @Test
    void descodificar_HARRY_Test() {
        int valor = descodificador.descodificar( new char[] {'H','A','R','R','Y'});
        assertEquals(65112, valor);
    }
    @Test
    void descodificar_POTTER_Test() {
        int valor = descodificador.descodificar( new char[] {'P','O','T','T','E','R'});
        assertEquals(748891, valor);
    }
    @Test
    void descodificar_TROLLS_Test() {
        int valor = descodificador.descodificar( new char[] {'T','R','O','L','L','S'});
        assertEquals(814003, valor);
    }
    @Test
    void quinValorTeLaLletraTest() {
        int valor = descodificador.quinValorTeLaLletra('T');
        assertEquals(8, valor);
    }

}
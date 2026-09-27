package pe.cibertec.appgrupo1consumidor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciServiceTest {

    private FibonacciService fibonacciService;

    @BeforeEach
    void setUp() {
        fibonacciService = new FibonacciService();
    }

    @Test
    void testCasosBase() {
        assertEquals(0, fibonacciService.fibonacci(0));
        assertEquals(1, fibonacciService.fibonacci(1));
    }

    @Test
    void testValoresConocidos() {
        assertEquals(1, fibonacciService.fibonacci(2));
        assertEquals(2, fibonacciService.fibonacci(3));
        assertEquals(21, fibonacciService.fibonacci(8));
        assertEquals(610, fibonacciService.fibonacci(15));
    }

    @Test
    void testCalculateSequence() {
        List<Integer> posiciones = Arrays.asList(1, 2, 15, 8);
        List<Long> esperados = Arrays.asList(1L, 1L, 610L, 21L);

        List<Long> resultado = fibonacciService.calculateSequence(posiciones);

        assertEquals(esperados, resultado);
    }
}

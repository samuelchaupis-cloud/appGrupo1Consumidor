package pe.cibertec.appgrupo1consumidor.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.cibertec.appgrupo1consumidor.config.RabbitMQConfig;
import pe.cibertec.appgrupo1consumidor.service.FibonacciService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@AllArgsConstructor
public class FibonacciConsumer {

    private FibonacciService fibonacciService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String cadenaNumeros) {

        log.info("Mensaje recibido de RabbitMQ: {}", cadenaNumeros);

        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        List<Integer> positions = Arrays.asList(integerArray);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Integer pos : positions) {
            log.info("fibonacci({}) = {}", pos, fibonacciService.fibonacci(pos));
        }

        List<Long> resultados = fibonacciService.calculateSequence(positions);
        log.info("Resultado: {}", resultados);
        log.info("Procesado, fecha y hora {}", LocalDateTime.now());
        log.info("----------------------------------------");
    }
}

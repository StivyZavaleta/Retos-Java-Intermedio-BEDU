package org.bedu.reactivo.Sesion5;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MeridianPrimeControl {

    static Random random = new Random();

    public static void main(String[] args) {

        // 🚗 Flujo de sensores de tráfico
        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0 - 100%
                .filter(nivel -> nivel > 70)
                .map(nivel -> "🚗 Alerta: Congestión del " + nivel + "% en Avenida Solar")
                .onBackpressureBuffer(5);

        // 🌫️ Flujo de contaminación del aire
        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(101)) // PM2.5 0 - 100 ug/m3
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");

        // 🚑 Flujo de accidentes viales
        List<String> prioridades = List.of("Baja", "Media", "Alta");
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades.get(random.nextInt(prioridades.size())))
                .filter(p -> p.equals("Alta"))
                .map(p -> "🚑 Emergencia vial: Accidente con prioridad " + p);

        // 🚝 Flujo de trenes maglev
        Flux<String> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // 0 - 10 min
                .delayElements(Duration.ofMillis(100)) 
                .filter(min -> min > 5)
                .map(min -> "🚝 Tren maglev con retraso crítico: " + min + " minutos");

        // 🚦 Flujo de semáforos inteligentes (estado persistente)
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(estados.length)];
                })
                .transform(MeridianPrimeControl::controlarSemaforos);

       // Combinamos todos los flujos
        Flux<String> sistemaCritico = Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .doOnNext(System.out::println);

        // Buffer de 1 segundo para detectar múltiples eventos críticos simultáneos
        sistemaCritico.buffer(Duration.ofSeconds(1))
                .filter(buffer -> buffer.size() >= 3)
                .subscribe(buffer -> System.out.println("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime"));

        // Mantener el sistema en ejecución
        sistemaCritico.blockLast();
    }
}

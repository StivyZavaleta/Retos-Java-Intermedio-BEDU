package org.bedu.reactivo.Sesion5;

import java.time.Duration;
import java.util.Random;
import java.util.stream.IntStream;

public class UCIMonitor {

    // Datos del paciente
    static class SignosVitales {
        private final int pacienteId;
        private final int fc; // Frecuencia cardíaca
        private final int sistolica;
        private final int diastolica;
        private final int spo2;

        SignosVitales(int id, int fc, int sistolica, int diastolica, int spo2) {
            this.pacienteId = id;
            this.fc = fc;
            this.sistolica = sistolica;
            this.diastolica = diastolica;
            this.spo2 = spo2;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        // Crear 3 flujos, uno por paciente
        Flux<SignosVitales> paciente1 = generarPaciente(1);
        Flux<SignosVitales> paciente2 = generarPaciente(2);
        Flux<SignosVitales> paciente3 = generarPaciente(3);

        // Fusionar los flujos
        Flux<SignosVitales> signosFusionados = Flux.merge(paciente1, paciente2, paciente3);

        // Procesamiento con backpressure y filtrado
        signosFusionados
                .delayElements(Duration.ofSeconds(1)) 
                .flatMap(signo -> {
                    // Priorizar FC > PA > SpO2
                    if (signo.fc < 50 || signo.fc > 120) {
                        return Flux.just("⚠️ Paciente " + signo.pacienteId + " - FC crítica: " + signo.fc + " bpm");
                    } else if (signo.sistolica > 140 || signo.diastolica > 90 ||
                               signo.sistolica < 90 || signo.diastolica < 60) {
                        return Flux.just("⚠️ Paciente " + signo.pacienteId + " - PA crítica: "
                                + signo.sistolica + "/" + signo.diastolica + " mmHg");
                    } else if (signo.spo2 < 90) {
                        return Flux.just("⚠️ Paciente " + signo.pacienteId + " - SpO2 baja: " + signo.spo2 + "%");
                    }
                    return Flux.empty();
                })
                .subscribe(System.out::println);

        // Mantener el sistema vivo
        try {
            Thread.sleep(60_000); // Simular 1 minuto de ejecución
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simula generación de datos para un paciente cada 300ms
    private static Flux<SignosVitales> generarPaciente(int pacienteId) {
        return Flux.interval(Duration.ofMillis(300))
                .map(t -> new SignosVitales(
                        pacienteId,
                        40 + random.nextInt(100),        // FC: 40-140 bpm
                        80 + random.nextInt(80),         // Sistólica: 80-170
                        50 + random.nextInt(50),         // Diastólica: 50-100
                        85 + random.nextInt(15)          // SpO2: 85-100%
                ));
    }
}
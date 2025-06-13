package Sesion4.Reto2;

import java.util.Random;
import java.util.concurrent.*;

public class AeropuertoControl {

    private static final Random random = new Random();

    // 🛣️ Verificar disponibilidad de la pista (con fallo aleatorio)
    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia(2);
            boolean disponible = random.nextInt(100) < 80; // 80% de probabilidad
            System.out.println("🛣️ Pista disponible: " + disponible);
            return disponible;
        });
    }

     // 🌦️ Verificar estado del clima (con fallo aleatorio)
    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia(3);
            boolean favorable = random.nextInt(100) < 85; // 85% de probabilidad
            System.out.println("🌦️ Clima favorable: " + favorable);
            return favorable;
        });
    }

    // 🚦 Verificar tráfico aéreo (con fallo aleatorio)
    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia(2);
            boolean despejado = random.nextInt(100) < 90; // 90% de probabilidad
            System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

     // 👷‍♂️ Verificar personal en tierra (con fallo aleatorio)
    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia(3);
            boolean disponible = random.nextInt(100) < 95; // 95% de probabilidad
            System.out.println("👷‍♂️ Personal disponible: " + disponible);
            return disponible;
        });
    }

    // Simula latencia 
    public static void simularLatencia(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pistaFuture = verificarPista();
        CompletableFuture<Boolean> climaFuture = verificarClima();
        CompletableFuture<Boolean> traficoFuture = verificarTraficoAereo();
        CompletableFuture<Boolean> personalFuture = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pistaFuture, climaFuture, traficoFuture, personalFuture);

        allChecks.thenApplyAsync(v -> {
            try {
                boolean condicionesOk = pistaFuture.get() && climaFuture.get() && traficoFuture.get() && personalFuture.get();
                return condicionesOk;
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener resultados", e);
            }
        })
        .thenAccept(condicionesOk -> {
            if (condicionesOk) {
                System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
            } else {
                System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
            }
        })
        .exceptionally(ex -> {
            System.out.println("\n❌ Error en la verificación: " + ex.getMessage());
            return null;
        });

        // Esperar a que todas las tareas finalicen (máx. 10 segundos)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

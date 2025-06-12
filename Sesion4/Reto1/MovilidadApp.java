import java.util.concurrent.*;

public class MovilidadApp {

    // 🗺️ Simula el cálculo de la ruta
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                TimeUnit.SECONDS.sleep(3);
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new IllegalStateException("🚨 Error al calcular la ruta", e);
            }
        });
    }

    // 💰 Simula la estimación de la tarifa
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                TimeUnit.SECONDS.sleep(2);
                return 75.50;
            } catch (InterruptedException e) {
                throw new IllegalStateException("🚨 Error al estimar tarifa", e);
            }
        });
    }

    public static void main(String[] args) {
        
        // 🏁 Ejecutar las operaciones en paralelo
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        // 🔗 Combina ambas operaciones cuando ambas terminan
        rutaFuture
            .thenCombine(tarifaFuture, (ruta, tarifa) -> {
                            return "🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa;
                        })
            .thenAccept(System.out::println)
            .exceptionally(ex -> { 
                System.out.println("🚨 Ocurrio un error: " + ex.getMessage());
                    return null;
             });

        // Esperar para que el programa no finalice antes de que terminen los hilos
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
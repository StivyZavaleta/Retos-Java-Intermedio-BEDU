import java.util.concurrent.*;

// 🛰️ Sistema de navegación
class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000); // Simula procesamiento
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}

// 🧪 Sistema de soporte vital
class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(600);
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

// 🔥 Sistema de control térmico
class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}

// 📡 Sistema de comunicaciones
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(500);
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {

        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Crear tareas
        Future<String> r1 = executor.submit(new SistemaNavegacion());
        Future<String> r2 = executor.submit(new SistemaSoporteVital());
        Future<String> r3 = executor.submit(new SistemaControlTermico());
        Future<String> r4 = executor.submit(new SistemaComunicaciones());

        try {
            System.out.println(r4.get());
            System.out.println(r2.get());
            System.out.println(r3.get());
            System.out.println(r1.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("❌ Error en la ejecución de sistemas: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }
}
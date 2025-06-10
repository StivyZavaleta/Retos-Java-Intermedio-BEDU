import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Crear tareas con Runnable
        Runnable dr1 = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable dr2 = () -> salaCirugia.usar("Dr. Gómez");
        Runnable dr3 = () -> salaCirugia.usar("Dra. Zavaleta");
        Runnable dr4 = () -> salaCirugia.usar("Dr. Torres");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(dr1);
        executor.execute(dr2);
        executor.execute(dr3);
        executor.execute(dr4);

        // Cierre del executor
        executor.shutdown();
    }
}
import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();


    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {

        lock.lock();  // Adquiere el bloqueo
        
        try {
            System.out.println("👨‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); 
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.err.println("❌ " + profesional + " fue interrumpido.");
        } finally {
            lock.unlock();  // Libera el bloqueo
        }
    }
}
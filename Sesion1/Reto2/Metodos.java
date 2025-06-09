import java.util.List;
import java.util.function.Predicate;

public class Metodos {

    // Método genérico para mostrar el detalle de todos los materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("\n📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Método genérico para sumar y mostrar la duración total de los videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos");
    }

    // Método genérico para actualizar el estado de los ejercicios a revisado = true y mostrar un mensaje por cada uno
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                ((Ejercicio) obj).marcarRevisado();
            }
        }
    }

    // Método genérico que filtre materiales por autor usando Predicate<MaterialCurso>
    public static <T extends MaterialCurso> void filtrarPorAutor(List<T> lista, Predicate<T> filtro) {
        System.out.println("\n🔍 Filtrando materiales por autor:");
        for (T material : lista) {
            if (filtro.test(material)) {
                material.mostrarDetalle();
            }
        }
    }
}
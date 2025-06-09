import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Video v1 = new Video("Introducción a Java", "Mario", 15);
        Video v2 = new Video("POO en Java", "Carlos", 20);
        Articulo a1 = new Articulo("Historia de Java", "Ana", 1200);
        Articulo a2 = new Articulo("Tipos de datos", "Luis", 800);
        Ejercicio e1 = new Ejercicio("Variables y tipos", "Luis");
        Ejercicio e2 = new Ejercicio("Condicionales", "Mario");

        // Crear lista general de materiales
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(v1);
        materiales.add(v2);
        materiales.add(a1);
        materiales.add(a2);
        materiales.add(e1);
        materiales.add(e2);

        Metodos.mostrarMateriales(materiales);

        // Contar duración de los videos
        List<Video> listaVideos = new ArrayList<>();
        listaVideos.add(v1);
        listaVideos.add(v2);
        Metodos.contarDuracionVideos(listaVideos);

        // Marcar ejercicios como revisados
        List<Ejercicio> listaEjercicios = new ArrayList<>();
        listaEjercicios.add(e1);
        listaEjercicios.add(e2);
        Metodos.marcarEjerciciosRevisados(listaEjercicios);

        // Filtrar por autor
        Metodos.filtrarPorAutor(materiales, m -> m.getAutor().equalsIgnoreCase("Mario"));
    }
}

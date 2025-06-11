import java.util.*;

public class Encuesta {
    private String paciente;
    private String comentario;
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public String getPaciente() { return paciente; }
    public int getCalificacion() { return calificacion; }

    public Optional<String> getComentario() { 
        return Optional.ofNullable(comentario); 
    }

}
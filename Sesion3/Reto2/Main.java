import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        
        // 📋 Lista de sucursales con sus encuestas
        List<Sucursal> sucursales = List.of(
            new Sucursal("Centro", List.of(
                new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                new Encuesta("Stivy", null, 5))),
            new Sucursal("Norte", List.of(
                new Encuesta("Martin", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Sofía", null, 4)
            ))
        );

        List<String> msg = sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuesta().stream()
                    .filter(e -> e.getCalificacion() <= 3) 
                    .map(e -> e.getComentario() 
                        .map(c -> "Sucursal " + sucursal.getNombre() +
                                  ": Seguimiento a paciente con comentario: \"" + c + "\""))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
            )
            .collect(Collectors.toList());

        msg.forEach(System.out::println);
    }

    // Clase auxiliar para transportar Encuesta + Sucursal juntos
    static class Seguimiento {
        private final Encuesta encuesta;
        private final String sucursal;

        public Seguimiento(Encuesta encuesta, String sucursal) {
            this.encuesta = encuesta;
            this.sucursal = sucursal;
        }

        public Encuesta getEncuesta() { return encuesta; }
        public String getSucursal() { return sucursal; }
    }
}
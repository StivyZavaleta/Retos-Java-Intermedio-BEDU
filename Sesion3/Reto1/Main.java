package Reto1;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        List<Pedido> pedidos = List.of(
            new Pedido("Ana", "Domicilio", "555-1234"),
            new Pedido("Mario", "Local", "555-2345"),
            new Pedido("Stivy", "Domicilio", "555-5678"),
            new Pedido("Martin", "Local", null)
        );

        // Stream Lista de Pedidos
        pedidos.stream()
            .filter(p -> "Domicilio".equalsIgnoreCase(p.getTipoEntrega()))
            .map(Pedido::getTelefono) // 🔄 map transforma Pedido → Optional<String> (telefono)
            .filter(Optional::isPresent) // 🔍 filter permite solo los Optionals que tienen valor (no vacíos)
            .map(Optional::get) // 📥 map extrae el valor del Optional
            .map(tel -> "📞 Confirmación enviada al número: " + tel) // Crea el mensaje para mostrar todos en consola.
            .forEach(System.out::println); // 📤 forEach imprime los valores finales
    }
}
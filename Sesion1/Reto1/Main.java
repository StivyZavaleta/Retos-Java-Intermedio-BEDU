import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar órdenes
        Metodos.mostrarOrdenes(ordenesMasa);
        Metodos.mostrarOrdenes(ordenesPersonalizadas);
        Metodos.mostrarOrdenes(ordenesPrototipo);

        // Procesar órdenes personalizadas
        Metodos.procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        // Unificar todas las órdenes para el resumen total
        List<OrdenProduccion> total = new ArrayList<>();
        total.addAll(ordenesMasa);
        total.addAll(ordenesPersonalizadas);
        total.addAll(ordenesPrototipo);

        Metodos.contarOrdenes(total);
    }
}
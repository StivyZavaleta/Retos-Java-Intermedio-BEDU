import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repo, MarcaRepository marcaRepo) {
        return (args) -> {

            // Crea marcas
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");

            marcaRepo.save(apple);
            marcaRepo.save(samsung);

            productoRepo.save(new Producto("iPhone 15", "Smartphone de gama alta", 25000, apple));
            productoRepo.save(new Producto("iPad Pro", "Tableta profesional", 21000, apple));
            productoRepo.save(new Producto("Galaxy S23", "Smartphone premium", 23000, samsung));
            productoRepo.save(new Producto("Smart TV", "Televisor inteligente 4K", 18000, samsung));
            
            // Muestra los productos agrupados por marca
            System.out.println("\n📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                .filter(p -> p.getMarca().getId().equals(marca.getId()))
                .forEach(p -> System.out.println("   - " + p.getNombre()));
           });
        };
    }
}
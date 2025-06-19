import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
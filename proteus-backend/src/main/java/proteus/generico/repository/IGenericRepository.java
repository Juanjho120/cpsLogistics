package proteus.generico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Generic Repository for Models
 * 
 * @author Juan Tzun
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface IGenericRepository<T, ID> extends JpaRepository<T, ID> {

}

package ferramong.auth.repositories;

import ferramong.auth.entities.Dweller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repository
 *      Responsável por gerenciar queries do banco de dados
 */

@Repository
public interface SchedulerRepository extends JpaRepository<Dweller, Integer> {

    @Query(
            "SELECT s FROM Scheduler s " +
            "WHERE s.name LIKE CONCAT(UPPER(:name), '%')"
    )
    public List<Dweller> listAllWithName(
            @Param("name") String name
    );

}

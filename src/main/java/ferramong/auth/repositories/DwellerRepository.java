package ferramong.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ferramong.auth.entities.Dweller;

/*
 * Repository
 *      Responsável por gerenciar queries do banco de dados
 */

@Repository
public interface DwellerRepository extends JpaRepository<Dweller, Long> {

    public Optional<Dweller> findById(Long id);
    public Optional<Dweller> findByCpfAndPassword(String cpf, String password);
    public Optional<Dweller> findByCpf(String cpf);
    public Optional<Dweller> findByName(String name);
}

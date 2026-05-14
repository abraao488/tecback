package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}

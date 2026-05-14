package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Funcionario criarFuncionario(String cpf, String email) {
        return Funcionario.builder()
                .nome("João Silva").cpf(cpf).email(email)
                .cargo("Desenvolvedor").salario(new BigDecimal("5000.00"))
                .dataAdmissao(LocalDate.now()).build();
    }

    @Test
    void deveSalvarEBuscarPorId() {
        Funcionario salvo = funcionarioRepository.save(criarFuncionario("111.111.111-11", "joao@email.com"));
        assertTrue(funcionarioRepository.findById(salvo.getId()).isPresent());
    }

    @Test
    void deveBuscarPorCpf() {
        funcionarioRepository.save(criarFuncionario("222.222.222-22", "maria@email.com"));
        Optional<Funcionario> encontrado = funcionarioRepository.findByCpf("222.222.222-22");
        assertTrue(encontrado.isPresent());
    }

    @Test
    void deveBuscarPorEmail() {
        funcionarioRepository.save(criarFuncionario("333.333.333-33", "pedro@email.com"));
        Optional<Funcionario> encontrado = funcionarioRepository.findByEmail("pedro@email.com");
        assertTrue(encontrado.isPresent());
    }

    @Test
    void deveVerificarExistenciaPorCpf() {
        funcionarioRepository.save(criarFuncionario("444.444.444-44", "ana@email.com"));
        assertTrue(funcionarioRepository.existsByCpf("444.444.444-44"));
        assertFalse(funcionarioRepository.existsByCpf("000.000.000-00"));
    }

    @Test
    void deveVerificarExistenciaPorEmail() {
        funcionarioRepository.save(criarFuncionario("555.555.555-55", "carlos@email.com"));
        assertTrue(funcionarioRepository.existsByEmail("carlos@email.com"));
        assertFalse(funcionarioRepository.existsByEmail("naoexiste@email.com"));
    }
}

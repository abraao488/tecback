package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deveSalvarEBuscarCategoria() {
        Categoria categoria = Categoria.builder().nome("Ação").descricao("Filmes de ação").build();
        Categoria salva = categoriaRepository.save(categoria);
        Optional<Categoria> encontrada = categoriaRepository.findById(salva.getId());
        assertTrue(encontrada.isPresent());
        assertEquals("Ação", encontrada.get().getNome());
    }

    @Test
    void deveBuscarPorNome() {
        categoriaRepository.save(Categoria.builder().nome("Drama").descricao("Filmes de drama").build());
        Optional<Categoria> encontrada = categoriaRepository.findByNome("Drama");
        assertTrue(encontrada.isPresent());
    }

    @Test
    void deveVerificarExistenciaPorNome() {
        categoriaRepository.save(Categoria.builder().nome("Comédia").descricao("desc").build());
        assertTrue(categoriaRepository.existsByNome("Comédia"));
        assertFalse(categoriaRepository.existsByNome("Terror"));
    }

    @Test
    void deveDeletarCategoria() {
        Categoria salva = categoriaRepository.save(Categoria.builder().nome("Suspense").build());
        categoriaRepository.deleteById(salva.getId());
        assertFalse(categoriaRepository.findById(salva.getId()).isPresent());
    }
}

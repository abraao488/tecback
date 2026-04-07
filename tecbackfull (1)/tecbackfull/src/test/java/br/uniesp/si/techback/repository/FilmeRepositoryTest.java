package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Filme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    private Filme criarFilme(String titulo, String genero) {
        return Filme.builder()
                .titulo(titulo).sinopse("Sinopse de " + titulo)
                .dataLancamento(LocalDate.of(2023, 1, 1))
                .genero(genero).duracaoMinutos(120)
                .classificacaoIndicativa("14 anos").build();
    }

    @Test
    void deveSalvarEBuscarFilmePorId() {
        Filme salvo = filmeRepository.save(criarFilme("Matrix", "Ação"));
        Optional<Filme> encontrado = filmeRepository.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Matrix", encontrado.get().getTitulo());
    }

    @Test
    void deveBuscarFilmesPorGenero() {
        filmeRepository.save(criarFilme("Matrix", "Ação"));
        filmeRepository.save(criarFilme("Matrix Reloaded", "Ação"));
        filmeRepository.save(criarFilme("Comédia Show", "Comédia"));

        List<Filme> acao = filmeRepository.findByGenero("Ação");
        assertEquals(2, acao.size());
    }

    @Test
    void deveBuscarFilmesPorTituloContendo() {
        filmeRepository.save(criarFilme("Matrix", "Ação"));
        filmeRepository.save(criarFilme("Matrix Reloaded", "Ação"));
        filmeRepository.save(criarFilme("Interestelar", "Ficção"));

        List<Filme> resultado = filmeRepository.findByTituloContainingIgnoreCase("matrix");
        assertEquals(2, resultado.size());
    }

    @Test
    void deveDeletarFilme() {
        Filme salvo = filmeRepository.save(criarFilme("Teste", "Drama"));
        filmeRepository.deleteById(salvo.getId());
        assertFalse(filmeRepository.findById(salvo.getId()).isPresent());
    }

    @Test
    void deveListarTodosOsFilmes() {
        filmeRepository.save(criarFilme("Filme 1", "Ação"));
        filmeRepository.save(criarFilme("Filme 2", "Drama"));
        assertEquals(2, filmeRepository.findAll().size());
    }
}

package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FilmeMapperTest {

    private FilmeMapper filmeMapper;

    @BeforeEach
    void setUp() {
        filmeMapper = new FilmeMapper();
    }

    @Test
    void toDTO_deveConverterCorretamente() {
        Filme filme = Filme.builder()
                .id(1L).titulo("Matrix").sinopse("Sinopse")
                .dataLancamento(LocalDate.of(1999, 3, 31))
                .genero("Ação").duracaoMinutos(136)
                .classificacaoIndicativa("14 anos").build();

        FilmeDTO dto = filmeMapper.toDTO(filme);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Matrix", dto.getTitulo());
        assertEquals("Ação", dto.getGenero());
        assertEquals(136, dto.getDuracaoMinutos());
    }

    @Test
    void toEntity_deveConverterCorretamente() {
        FilmeDTO dto = FilmeDTO.builder()
                .id(1L).titulo("Matrix").sinopse("Sinopse")
                .genero("Ação").duracaoMinutos(136)
                .classificacaoIndicativa("14 anos").build();

        Filme filme = filmeMapper.toEntity(dto);

        assertNotNull(filme);
        assertEquals(1L, filme.getId());
        assertEquals("Matrix", filme.getTitulo());
        assertEquals("Ação", filme.getGenero());
    }

    @Test
    void toDTO_deveRetornarNull_quandoFilmeNull() {
        assertNull(filmeMapper.toDTO(null));
    }

    @Test
    void toEntity_deveRetornarNull_quandoDtoNull() {
        assertNull(filmeMapper.toEntity(null));
    }
}

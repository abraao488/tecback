package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaMapperTest {

    private CategoriaMapper categoriaMapper;

    @BeforeEach
    void setUp() {
        categoriaMapper = new CategoriaMapper();
    }

    @Test
    void toDTO_deveConverterCorretamente() {
        Categoria categoria = Categoria.builder()
                .id(1L).nome("Ação").descricao("Filmes de ação").build();

        CategoriaDTO dto = categoriaMapper.toDTO(categoria);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Ação", dto.getNome());
        assertEquals("Filmes de ação", dto.getDescricao());
    }

    @Test
    void toEntity_deveConverterCorretamente() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .id(1L).nome("Ação").descricao("Filmes de ação").build();

        Categoria categoria = categoriaMapper.toEntity(dto);

        assertNotNull(categoria);
        assertEquals(1L, categoria.getId());
        assertEquals("Ação", categoria.getNome());
    }

    @Test
    void toDTO_deveRetornarNull_quandoNull() {
        assertNull(categoriaMapper.toDTO(null));
    }

    @Test
    void toEntity_deveRetornarNull_quandoNull() {
        assertNull(categoriaMapper.toEntity(null));
    }
}

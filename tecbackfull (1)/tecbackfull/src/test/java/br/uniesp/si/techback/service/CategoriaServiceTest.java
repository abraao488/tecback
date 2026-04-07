package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.mapper.CategoriaMapper;
import br.uniesp.si.techback.model.Categoria;
import br.uniesp.si.techback.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoria = Categoria.builder().id(1L).nome("Ação").descricao("Filmes de ação").build();
        categoriaDTO = CategoriaDTO.builder().id(1L).nome("Ação").descricao("Filmes de ação").build();
    }

    @Test
    void listarTodas_deveRetornarLista() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        List<CategoriaDTO> result = categoriaService.listarTodas();

        assertEquals(1, result.size());
        assertEquals("Ação", result.get(0).getNome());
    }

    @Test
    void buscarPorId_deveRetornarCategoria_quandoExiste() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO result = categoriaService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Ação", result.getNome());
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoNaoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> categoriaService.buscarPorId(99L));
    }

    @Test
    void criar_deveSalvarCategoria() {
        when(categoriaMapper.toEntity(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO result = categoriaService.criar(categoriaDTO);

        assertNotNull(result);
        verify(categoriaRepository).save(any());
    }

    @Test
    void atualizar_deveAtualizarCategoria_quandoExiste() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toEntity(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO result = categoriaService.atualizar(1L, categoriaDTO);

        assertNotNull(result);
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNaoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> categoriaService.atualizar(99L, categoriaDTO));
    }

    @Test
    void deletar_deveDeletar_quandoExiste() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        categoriaService.deletar(1L);
        verify(categoriaRepository).deleteById(1L);
    }

    @Test
    void deletar_deveLancarExcecao_quandoNaoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> categoriaService.deletar(99L));
    }
}

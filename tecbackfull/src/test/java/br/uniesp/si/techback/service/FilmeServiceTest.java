package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.mapper.FilmeMapper;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private FilmeMapper filmeMapper;

    @InjectMocks
    private FilmeService filmeService;

    private Filme filme;
    private FilmeDTO filmeDTO;

    @BeforeEach
    void setUp() {
        filme = Filme.builder().id(1L).titulo("Matrix").genero("Ação")
                .duracaoMinutos(136).dataLancamento(LocalDate.of(1999, 3, 31)).build();
        filmeDTO = FilmeDTO.builder().id(1L).titulo("Matrix").genero("Ação")
                .duracaoMinutos(136).dataLancamento(LocalDate.of(1999, 3, 31)).build();
    }

    @Test
    void listarTodos_deveRetornarListaDeFilmes() {
        when(filmeRepository.findAll()).thenReturn(List.of(filme));
        when(filmeMapper.toDTO(filme)).thenReturn(filmeDTO);

        List<FilmeDTO> result = filmeService.listarTodos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Matrix", result.get(0).getTitulo());
    }

    @Test
    void buscarPorId_deveRetornarFilme_quandoExiste() {
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        when(filmeMapper.toDTO(filme)).thenReturn(filmeDTO);

        FilmeDTO result = filmeService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Matrix", result.getTitulo());
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoNaoExiste() {
        when(filmeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> filmeService.buscarPorId(99L));
    }

    @Test
    void criar_deveSalvarESepararFilme() {
        when(filmeMapper.toEntity(filmeDTO)).thenReturn(filme);
        when(filmeRepository.save(filme)).thenReturn(filme);
        when(filmeMapper.toDTO(filme)).thenReturn(filmeDTO);

        FilmeDTO result = filmeService.criar(filmeDTO);

        assertNotNull(result);
        assertEquals("Matrix", result.getTitulo());
        verify(filmeRepository, times(1)).save(any());
    }

    @Test
    void atualizar_deveAtualizarFilme_quandoExiste() {
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        when(filmeMapper.toEntity(filmeDTO)).thenReturn(filme);
        when(filmeRepository.save(filme)).thenReturn(filme);
        when(filmeMapper.toDTO(filme)).thenReturn(filmeDTO);

        FilmeDTO result = filmeService.atualizar(1L, filmeDTO);

        assertNotNull(result);
        verify(filmeRepository).save(any());
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNaoExiste() {
        when(filmeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> filmeService.atualizar(99L, filmeDTO));
    }

    @Test
    void deletar_deveDeletarFilme_quandoExiste() {
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));
        filmeService.deletar(1L);
        verify(filmeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletar_deveLancarExcecao_quandoNaoExiste() {
        when(filmeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> filmeService.deletar(99L));
    }

    @Test
    void buscarPorGenero_deveRetornarFilmesFiltrados() {
        when(filmeRepository.findByGenero("Ação")).thenReturn(List.of(filme));
        when(filmeMapper.toDTO(filme)).thenReturn(filmeDTO);

        List<FilmeDTO> result = filmeService.buscarPorGenero("Ação");

        assertEquals(1, result.size());
        assertEquals("Ação", result.get(0).getGenero());
    }
}

package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.mapper.FuncionarioMapper;
import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioMapper funcionarioMapper;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;

    @BeforeEach
    void setUp() {
        funcionario = Funcionario.builder().id(1L).nome("João").cpf("111.111.111-11")
                .email("joao@email.com").cargo("Dev").salario(new BigDecimal("5000"))
                .dataAdmissao(LocalDate.now()).build();
        funcionarioDTO = FuncionarioDTO.builder().id(1L).nome("João").cpf("111.111.111-11")
                .email("joao@email.com").cargo("Dev").salario(new BigDecimal("5000"))
                .dataAdmissao(LocalDate.now()).build();
    }

    @Test
    void listarTodos_deveRetornarLista() {
        when(funcionarioRepository.findAll()).thenReturn(List.of(funcionario));
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        List<FuncionarioDTO> result = funcionarioService.listarTodos();

        assertEquals(1, result.size());
    }

    @Test
    void buscarPorId_deveRetornar_quandoExiste() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        FuncionarioDTO result = funcionarioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoNaoExiste() {
        when(funcionarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> funcionarioService.buscarPorId(99L));
    }

    @Test
    void criar_deveSalvar_quandoCpfEEmailNovos() {
        when(funcionarioRepository.existsByCpf(any())).thenReturn(false);
        when(funcionarioRepository.existsByEmail(any())).thenReturn(false);
        when(funcionarioMapper.toEntity(funcionarioDTO)).thenReturn(funcionario);
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        FuncionarioDTO result = funcionarioService.criar(funcionarioDTO);

        assertNotNull(result);
        verify(funcionarioRepository).save(any());
    }

    @Test
    void criar_deveLancarExcecao_quandoCpfJaExiste() {
        when(funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> funcionarioService.criar(funcionarioDTO));
    }

    @Test
    void criar_deveLancarExcecao_quandoEmailJaExiste() {
        when(funcionarioRepository.existsByCpf(any())).thenReturn(false);
        when(funcionarioRepository.existsByEmail(funcionarioDTO.getEmail())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> funcionarioService.criar(funcionarioDTO));
    }

    @Test
    void deletar_deveDeletar_quandoExiste() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        funcionarioService.deletar(1L);
        verify(funcionarioRepository).deleteById(1L);
    }

    @Test
    void deletar_deveLancarExcecao_quandoNaoExiste() {
        when(funcionarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> funcionarioService.deletar(99L));
    }
}

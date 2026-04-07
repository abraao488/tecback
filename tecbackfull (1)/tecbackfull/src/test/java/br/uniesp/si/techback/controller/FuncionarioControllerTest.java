package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    private ObjectMapper objectMapper;
    private FuncionarioDTO funcionarioDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        funcionarioDTO = FuncionarioDTO.builder().id(1L).nome("João Silva")
                .cpf("111.111.111-11").email("joao@email.com").cargo("Dev")
                .salario(new BigDecimal("5000")).dataAdmissao(LocalDate.now()).build();
    }

    @Test
    void listarTodos_deveRetornar200() throws Exception {
        when(funcionarioService.listarTodos()).thenReturn(List.of(funcionarioDTO));

        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João Silva"));
    }

    @Test
    void buscarPorId_deveRetornar200() throws Exception {
        when(funcionarioService.buscarPorId(1L)).thenReturn(funcionarioDTO);

        mockMvc.perform(get("/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void criar_deveRetornar201() throws Exception {
        when(funcionarioService.criar(any())).thenReturn(funcionarioDTO);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void criar_deveRetornar400_quandoCamposInvalidos() throws Exception {
        FuncionarioDTO invalido = FuncionarioDTO.builder().nome("").email("emailinvalido").build();

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizar_deveRetornar200() throws Exception {
        when(funcionarioService.atualizar(eq(1L), any())).thenReturn(funcionarioDTO);

        mockMvc.perform(put("/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deletar_deveRetornar204() throws Exception {
        doNothing().when(funcionarioService).deletar(1L);

        mockMvc.perform(delete("/funcionarios/1"))
                .andExpect(status().isNoContent());
    }
}

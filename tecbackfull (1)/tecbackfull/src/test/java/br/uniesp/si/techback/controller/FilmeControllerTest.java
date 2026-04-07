package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.service.FilmeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    private ObjectMapper objectMapper;
    private FilmeDTO filmeDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        filmeDTO = FilmeDTO.builder().id(1L).titulo("Matrix").genero("Ação")
                .duracaoMinutos(136).dataLancamento(LocalDate.of(1999, 3, 31))
                .classificacaoIndicativa("14 anos").build();
    }

    @Test
    void listarTodos_deveRetornar200() throws Exception {
        when(filmeService.listarTodos()).thenReturn(List.of(filmeDTO));

        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Matrix"));
    }

    @Test
    void buscarPorId_deveRetornar200_quandoExiste() throws Exception {
        when(filmeService.buscarPorId(1L)).thenReturn(filmeDTO);

        mockMvc.perform(get("/filmes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Matrix"));
    }

    @Test
    void criar_deveRetornar201_quandoValido() throws Exception {
        when(filmeService.criar(any(FilmeDTO.class))).thenReturn(filmeDTO);

        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Matrix"));
    }

    @Test
    void criar_deveRetornar400_quandoTituloVazio() throws Exception {
        FilmeDTO invalido = FilmeDTO.builder().titulo("").build();

        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizar_deveRetornar200_quandoValido() throws Exception {
        when(filmeService.atualizar(eq(1L), any(FilmeDTO.class))).thenReturn(filmeDTO);

        mockMvc.perform(put("/filmes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deletar_deveRetornar204() throws Exception {
        doNothing().when(filmeService).deletar(1L);

        mockMvc.perform(delete("/filmes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarPorGenero_deveRetornar200() throws Exception {
        when(filmeService.buscarPorGenero("Ação")).thenReturn(List.of(filmeDTO));

        mockMvc.perform(get("/filmes/genero/Ação"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genero").value("Ação"));
    }
}

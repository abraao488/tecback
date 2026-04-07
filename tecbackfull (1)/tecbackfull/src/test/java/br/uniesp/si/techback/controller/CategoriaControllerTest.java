package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoriaDTO = CategoriaDTO.builder().id(1L).nome("Ação").descricao("Filmes de ação").build();
    }

    @Test
    void listarTodas_deveRetornar200() throws Exception {
        when(categoriaService.listarTodas()).thenReturn(List.of(categoriaDTO));

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Ação"));
    }

    @Test
    void buscarPorId_deveRetornar200() throws Exception {
        when(categoriaService.buscarPorId(1L)).thenReturn(categoriaDTO);

        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ação"));
    }

    @Test
    void criar_deveRetornar201() throws Exception {
        when(categoriaService.criar(any())).thenReturn(categoriaDTO);

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void criar_deveRetornar400_quandoNomeVazio() throws Exception {
        CategoriaDTO invalido = CategoriaDTO.builder().nome("").build();

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizar_deveRetornar200() throws Exception {
        when(categoriaService.atualizar(eq(1L), any())).thenReturn(categoriaDTO);

        mockMvc.perform(put("/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriaDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deletar_deveRetornar204() throws Exception {
        doNothing().when(categoriaService).deletar(1L);

        mockMvc.perform(delete("/categorias/1"))
                .andExpect(status().isNoContent());
    }
}

package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "CRUD de filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping
    @Operation(summary = "Listar todos os filmes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = FilmeDTO.class)))
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme encontrado",
                content = @Content(schema = @Schema(implementation = FilmeDTO.class))),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo filme")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Filme criado com sucesso",
                content = @Content(schema = @Schema(implementation = FilmeDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<FilmeDTO> criar(@Valid @RequestBody FilmeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar filme existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme atualizado",
                content = @Content(schema = @Schema(implementation = FilmeDTO.class))),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    public ResponseEntity<FilmeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FilmeDTO dto) {
        return ResponseEntity.ok(filmeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genero/{genero}")
    @Operation(summary = "Buscar filmes por gênero")
    @ApiResponse(responseCode = "200", description = "Filmes encontrados",
            content = @Content(schema = @Schema(implementation = FilmeDTO.class)))
    public ResponseEntity<List<FilmeDTO>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(filmeService.buscarPorGenero(genero));
    }
}

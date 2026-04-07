package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.service.CategoriaService;
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
@RequestMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "CRUD de categorias de produtos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar nova categoria")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso",
                content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar categoria existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria atualizada",
                content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar categoria")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

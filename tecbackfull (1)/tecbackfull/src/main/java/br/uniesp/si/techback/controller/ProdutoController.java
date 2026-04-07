package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.ProdutoDTO;
import br.uniesp.si.techback.service.ProdutoService;
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
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "CRUD de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = ProdutoDTO.class)))
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado",
                content = @Content(schema = @Schema(implementation = ProdutoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo produto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                content = @Content(schema = @Schema(implementation = ProdutoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar produto existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto atualizado",
                content = @Content(schema = @Schema(implementation = ProdutoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Buscar produtos por categoria")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados",
            content = @Content(schema = @Schema(implementation = ProdutoDTO.class)))
    public ResponseEntity<List<ProdutoDTO>> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoriaId));
    }
}

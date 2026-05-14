package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.PagamentoDTO;
import br.uniesp.si.techback.service.PagamentoService;
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
@RequestMapping(value = "/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Pagamentos", description = "CRUD de pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    @Operation(summary = "Listar todos os pagamentos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = PagamentoDTO.class)))
    public ResponseEntity<List<PagamentoDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pagamento encontrado",
                content = @Content(schema = @Schema(implementation = PagamentoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pagamento não encontrado", content = @Content)
    })
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo pagamento")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso",
                content = @Content(schema = @Schema(implementation = PagamentoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<PagamentoDTO> criar(@Valid @RequestBody PagamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar pagamento existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pagamento atualizado",
                content = @Content(schema = @Schema(implementation = PagamentoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pagamento não encontrado", content = @Content)
    })
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok(pagamentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pagamento")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pagamento deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pagamento não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.PedidoDTO;
import br.uniesp.si.techback.service.PedidoService;
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
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "CRUD de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = PedidoDTO.class)))
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                content = @Content(schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo pedido")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                content = @Content(schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<PedidoDTO> criar(@Valid @RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar pedido existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido atualizado",
                content = @Content(schema = @Schema(implementation = PedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pedido")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar pedidos por usuário")
    @ApiResponse(responseCode = "200", description = "Pedidos encontrados",
            content = @Content(schema = @Schema(implementation = PedidoDTO.class)))
    public ResponseEntity<List<PedidoDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.buscarPorUsuario(usuarioId));
    }
}

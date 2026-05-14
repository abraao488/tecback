package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.ItemPedidoDTO;
import br.uniesp.si.techback.service.ItemPedidoService;
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
@RequestMapping(value = "/itens-pedido", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Itens de Pedido", description = "CRUD de itens de pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @GetMapping
    @Operation(summary = "Listar todos os itens de pedido")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class)))
    public ResponseEntity<List<ItemPedidoDTO>> listarTodos() {
        return ResponseEntity.ok(itemPedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item de pedido por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item encontrado",
                content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    public ResponseEntity<ItemPedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo item de pedido")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Item criado com sucesso",
                content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<ItemPedidoDTO> criar(@Valid @RequestBody ItemPedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar item de pedido existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Item atualizado",
                content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    public ResponseEntity<ItemPedidoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItemPedidoDTO dto) {
        return ResponseEntity.ok(itemPedidoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item de pedido")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Item deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedido/{pedidoId}")
    @Operation(summary = "Buscar itens por pedido")
    @ApiResponse(responseCode = "200", description = "Itens encontrados",
            content = @Content(schema = @Schema(implementation = ItemPedidoDTO.class)))
    public ResponseEntity<List<ItemPedidoDTO>> buscarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(itemPedidoService.buscarPorPedido(pedidoId));
    }
}

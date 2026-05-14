package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.service.EnderecoService;
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
@RequestMapping(value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "CRUD de endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    @Operation(summary = "Listar todos os endereços")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = EnderecoDTO.class)))
    public ResponseEntity<List<EnderecoDTO>> listarTodos() {
        return ResponseEntity.ok(enderecoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar endereço por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo endereço")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> criar(@Valid @RequestBody EnderecoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar endereço existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço atualizado",
                content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto) {
        return ResponseEntity.ok(enderecoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar endereço")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

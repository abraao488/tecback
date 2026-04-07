package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.service.FuncionarioService;
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
@RequestMapping(value = "/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Funcionários", description = "CRUD de funcionários")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    @Operation(summary = "Listar todos os funcionários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = FuncionarioDTO.class)))
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                content = @Content(schema = @Schema(implementation = FuncionarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo funcionário")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
                content = @Content(schema = @Schema(implementation = FuncionarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou CPF/email já cadastrado", content = @Content)
    })
    public ResponseEntity<FuncionarioDTO> criar(@Valid @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.criar(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar funcionário existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funcionário atualizado",
                content = @Content(schema = @Schema(implementation = FuncionarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

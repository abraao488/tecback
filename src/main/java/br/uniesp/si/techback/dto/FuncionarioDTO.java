package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um funcionário")
public class FuncionarioDTO {

    @Schema(description = "ID do funcionário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome completo", example = "João Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Schema(description = "CPF do funcionário", example = "111.111.111-11", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description = "Email do funcionário", example = "joao@empresa.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Cargo", example = "Desenvolvedor")
    private String cargo;

    @Schema(description = "Salário", example = "5000.00")
    private BigDecimal salario;

    @Schema(description = "Data de admissão", example = "2023-01-15")
    private LocalDate dataAdmissao;

    @Schema(description = "ID do endereço", example = "1")
    private Long enderecoId;
}

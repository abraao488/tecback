package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um usuário")
public class UsuarioDTO {

    @Schema(description = "ID do usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome completo", example = "Maria Souza", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description = "Email do usuário", example = "maria@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Schema(description = "Senha do usuário", example = "senha123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    @Schema(description = "CPF do usuário", example = "222.222.222-22")
    private String cpf;

    @Schema(description = "Data de nascimento", example = "1995-06-20")
    private LocalDate dataNascimento;

    @Schema(description = "Telefone", example = "(83) 99999-0000")
    private String telefone;

    @Schema(description = "ID do endereço", example = "1")
    private Long enderecoId;
}

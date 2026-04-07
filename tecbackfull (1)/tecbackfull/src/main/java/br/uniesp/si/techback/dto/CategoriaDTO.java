package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de uma categoria")
public class CategoriaDTO {

    @Schema(description = "ID da categoria", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome da categoria", example = "Eletrônicos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Descrição da categoria", example = "Produtos eletrônicos em geral")
    private String descricao;
}

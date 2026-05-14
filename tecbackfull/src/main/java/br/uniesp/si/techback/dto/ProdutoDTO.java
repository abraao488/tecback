package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um produto")
public class ProdutoDTO {

    @Schema(description = "ID do produto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome do produto", example = "Notebook Dell", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Descrição do produto", example = "Notebook Dell Inspiron 15")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Schema(description = "Preço do produto", example = "2999.90", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal preco;

    @Schema(description = "Quantidade em estoque", example = "50")
    private Integer estoque;

    @Schema(description = "ID da categoria", example = "1")
    private Long categoriaId;
}

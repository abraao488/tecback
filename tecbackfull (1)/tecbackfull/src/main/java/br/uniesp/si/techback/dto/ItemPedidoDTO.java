package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um item de pedido")
public class ItemPedidoDTO {

    @Schema(description = "ID do item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Pedido é obrigatório")
    @Schema(description = "ID do pedido", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long pedidoId;

    @NotNull(message = "Produto é obrigatório")
    @Schema(description = "ID do produto", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    @Schema(description = "Quantidade do produto", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantidade;

    @Schema(description = "Preço unitário", example = "149.90")
    private BigDecimal precoUnitario;

    @Schema(description = "Subtotal (quantidade × preço)", example = "299.80", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal subtotal;
}

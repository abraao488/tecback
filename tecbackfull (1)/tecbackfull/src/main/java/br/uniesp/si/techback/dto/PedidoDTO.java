package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um pedido")
public class PedidoDTO {

    @Schema(description = "ID do pedido", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Data e hora do pedido", example = "2024-01-15T10:30:00")
    private LocalDateTime dataPedido;

    @Schema(description = "Status do pedido", example = "PENDENTE", allowableValues = {"PENDENTE", "APROVADO", "ENVIADO", "ENTREGUE", "CANCELADO"})
    private String status;

    @Schema(description = "Valor total do pedido", example = "299.90")
    private BigDecimal valorTotal;

    @NotNull(message = "Usuário é obrigatório")
    @Schema(description = "ID do usuário", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usuarioId;
}

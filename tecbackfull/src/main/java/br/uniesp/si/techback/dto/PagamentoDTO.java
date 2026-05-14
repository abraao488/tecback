package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um pagamento")
public class PagamentoDTO {

    @Schema(description = "ID do pagamento", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Pedido é obrigatório")
    @Schema(description = "ID do pedido", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long pedidoId;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    @Schema(description = "Forma de pagamento", example = "PIX", allowableValues = {"PIX", "CARTAO_CREDITO", "CARTAO_DEBITO", "BOLETO"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String formaPagamento;

    @Schema(description = "Status do pagamento", example = "APROVADO", allowableValues = {"PENDENTE", "APROVADO", "RECUSADO", "ESTORNADO"})
    private String status;

    @Schema(description = "Valor pago", example = "299.90")
    private BigDecimal valor;

    @Schema(description = "Data e hora do pagamento", example = "2024-01-15T10:35:00")
    private LocalDateTime dataPagamento;

    @Schema(description = "Código da transação", example = "TXN-ABC123")
    private String codigoTransacao;
}

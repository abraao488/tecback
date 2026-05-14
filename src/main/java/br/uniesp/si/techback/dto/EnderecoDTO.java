package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um endereço")
public class EnderecoDTO {

    @Schema(description = "ID do endereço", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Logradouro é obrigatório")
    @Schema(description = "Logradouro", example = "Rua das Flores", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logradouro;

    @Schema(description = "Número", example = "123")
    private String numero;

    @Schema(description = "Complemento", example = "Apto 45")
    private String complemento;

    @Schema(description = "Bairro", example = "Centro")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Schema(description = "Cidade", example = "Campina Grande", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Schema(description = "Estado (UF)", example = "PB", requiredMode = Schema.RequiredMode.REQUIRED)
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Schema(description = "CEP", example = "58400-000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cep;
}

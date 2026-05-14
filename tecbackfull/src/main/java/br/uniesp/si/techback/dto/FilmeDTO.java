package br.uniesp.si.techback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um filme")
public class FilmeDTO {

    @Schema(description = "ID do filme", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Schema(description = "Título do filme", example = "Matrix", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @Schema(description = "Sinopse do filme", example = "Um hacker descobre a verdade sobre a realidade...")
    private String sinopse;

    @Schema(description = "Data de lançamento", example = "1999-03-31")
    private LocalDate dataLancamento;

    @Schema(description = "Gênero do filme", example = "Ação")
    private String genero;

    @Schema(description = "Duração em minutos", example = "136")
    private Integer duracaoMinutos;

    @Schema(description = "Classificação indicativa", example = "14 anos")
    private String classificacaoIndicativa;
}

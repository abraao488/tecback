package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String sinopse;

    private LocalDate dataLancamento;
    private String genero;
    private Integer duracaoMinutos;
    private String classificacaoIndicativa;
}

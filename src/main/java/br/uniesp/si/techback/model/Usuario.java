package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    @ToString.Exclude
    private Endereco endereco;
}

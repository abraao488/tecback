package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.model.Filme;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public FilmeDTO toDTO(Filme filme) {
        if (filme == null) return null;
        return FilmeDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .sinopse(filme.getSinopse())
                .dataLancamento(filme.getDataLancamento())
                .genero(filme.getGenero())
                .duracaoMinutos(filme.getDuracaoMinutos())
                .classificacaoIndicativa(filme.getClassificacaoIndicativa())
                .build();
    }

    public Filme toEntity(FilmeDTO dto) {
        if (dto == null) return null;
        return Filme.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .sinopse(dto.getSinopse())
                .dataLancamento(dto.getDataLancamento())
                .genero(dto.getGenero())
                .duracaoMinutos(dto.getDuracaoMinutos())
                .classificacaoIndicativa(dto.getClassificacaoIndicativa())
                .build();
    }
}

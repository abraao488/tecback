package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        return Categoria.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
    }
}

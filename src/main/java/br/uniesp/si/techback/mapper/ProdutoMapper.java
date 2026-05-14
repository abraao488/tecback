package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.ProdutoDTO;
import br.uniesp.si.techback.model.Categoria;
import br.uniesp.si.techback.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .estoque(produto.getEstoque())
                .categoriaId(produto.getCategoria() != null ? produto.getCategoria().getId() : null)
                .build();
    }

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;
        Categoria categoria = dto.getCategoriaId() != null
                ? Categoria.builder().id(dto.getCategoriaId()).build()
                : null;
        return Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .estoque(dto.getEstoque())
                .categoria(categoria)
                .build();
    }
}

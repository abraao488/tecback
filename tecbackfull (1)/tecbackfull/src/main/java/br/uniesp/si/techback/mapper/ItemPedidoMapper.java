package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.ItemPedidoDTO;
import br.uniesp.si.techback.model.ItemPedido;
import br.uniesp.si.techback.model.Pedido;
import br.uniesp.si.techback.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedidoDTO toDTO(ItemPedido item) {
        if (item == null) return null;
        return ItemPedidoDTO.builder()
                .id(item.getId())
                .pedidoId(item.getPedido() != null ? item.getPedido().getId() : null)
                .produtoId(item.getProduto() != null ? item.getProduto().getId() : null)
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .subtotal(item.getSubtotal())
                .build();
    }

    public ItemPedido toEntity(ItemPedidoDTO dto) {
        if (dto == null) return null;
        Pedido pedido = dto.getPedidoId() != null ? Pedido.builder().id(dto.getPedidoId()).build() : null;
        Produto produto = dto.getProdutoId() != null ? Produto.builder().id(dto.getProdutoId()).build() : null;
        return ItemPedido.builder()
                .id(dto.getId())
                .pedido(pedido)
                .produto(produto)
                .quantidade(dto.getQuantidade())
                .precoUnitario(dto.getPrecoUnitario())
                .subtotal(dto.getSubtotal())
                .build();
    }
}

package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.PedidoDTO;
import br.uniesp.si.techback.model.Pedido;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) return null;
        return PedidoDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido())
                .status(pedido.getStatus())
                .valorTotal(pedido.getValorTotal())
                .usuarioId(pedido.getUsuario() != null ? pedido.getUsuario().getId() : null)
                .build();
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;
        Usuario usuario = dto.getUsuarioId() != null
                ? Usuario.builder().id(dto.getUsuarioId()).build()
                : null;
        return Pedido.builder()
                .id(dto.getId())
                .dataPedido(dto.getDataPedido())
                .status(dto.getStatus())
                .valorTotal(dto.getValorTotal())
                .usuario(usuario)
                .build();
    }
}

package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.PagamentoDTO;
import br.uniesp.si.techback.model.Pagamento;
import br.uniesp.si.techback.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public PagamentoDTO toDTO(Pagamento pagamento) {
        if (pagamento == null) return null;
        return PagamentoDTO.builder()
                .id(pagamento.getId())
                .pedidoId(pagamento.getPedido() != null ? pagamento.getPedido().getId() : null)
                .formaPagamento(pagamento.getFormaPagamento())
                .status(pagamento.getStatus())
                .valor(pagamento.getValor())
                .dataPagamento(pagamento.getDataPagamento())
                .codigoTransacao(pagamento.getCodigoTransacao())
                .build();
    }

    public Pagamento toEntity(PagamentoDTO dto) {
        if (dto == null) return null;
        Pedido pedido = dto.getPedidoId() != null ? Pedido.builder().id(dto.getPedidoId()).build() : null;
        return Pagamento.builder()
                .id(dto.getId())
                .pedido(pedido)
                .formaPagamento(dto.getFormaPagamento())
                .status(dto.getStatus())
                .valor(dto.getValor())
                .dataPagamento(dto.getDataPagamento())
                .codigoTransacao(dto.getCodigoTransacao())
                .build();
    }
}

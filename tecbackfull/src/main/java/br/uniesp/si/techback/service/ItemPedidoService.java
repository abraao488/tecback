package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.ItemPedidoDTO;
import br.uniesp.si.techback.mapper.ItemPedidoMapper;
import br.uniesp.si.techback.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    public List<ItemPedidoDTO> listarTodos() {
        log.info("Listando todos os itens de pedido");
        return itemPedidoRepository.findAll().stream()
                .map(itemPedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemPedidoDTO buscarPorId(Long id) {
        log.info("Buscando item de pedido por id: {}", id);
        return itemPedidoRepository.findById(id)
                .map(itemPedidoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado: " + id));
    }

    public ItemPedidoDTO criar(ItemPedidoDTO dto) {
        log.info("Criando item de pedido");
        if (dto.getPrecoUnitario() != null && dto.getQuantidade() != null) {
            dto.setSubtotal(dto.getPrecoUnitario().multiply(BigDecimal.valueOf(dto.getQuantidade())));
        }
        return itemPedidoMapper.toDTO(itemPedidoRepository.save(itemPedidoMapper.toEntity(dto)));
    }

    public ItemPedidoDTO atualizar(Long id, ItemPedidoDTO dto) {
        log.info("Atualizando item de pedido: {}", id);
        itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado: " + id));
        dto.setId(id);
        if (dto.getPrecoUnitario() != null && dto.getQuantidade() != null) {
            dto.setSubtotal(dto.getPrecoUnitario().multiply(BigDecimal.valueOf(dto.getQuantidade())));
        }
        return itemPedidoMapper.toDTO(itemPedidoRepository.save(itemPedidoMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando item de pedido: {}", id);
        itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado: " + id));
        itemPedidoRepository.deleteById(id);
    }

    public List<ItemPedidoDTO> buscarPorPedido(Long pedidoId) {
        return itemPedidoRepository.findByPedidoId(pedidoId).stream()
                .map(itemPedidoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.PedidoDTO;
import br.uniesp.si.techback.mapper.PedidoMapper;
import br.uniesp.si.techback.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public List<PedidoDTO> listarTodos() {
        log.info("Listando todos os pedidos");
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(Long id) {
        log.info("Buscando pedido por id: {}", id);
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
    }

    public PedidoDTO criar(PedidoDTO dto) {
        log.info("Criando pedido para usuário: {}", dto.getUsuarioId());
        if (dto.getDataPedido() == null) dto.setDataPedido(LocalDateTime.now());
        if (dto.getStatus() == null) dto.setStatus("PENDENTE");
        return pedidoMapper.toDTO(pedidoRepository.save(pedidoMapper.toEntity(dto)));
    }

    public PedidoDTO atualizar(Long id, PedidoDTO dto) {
        log.info("Atualizando pedido: {}", id);
        pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        dto.setId(id);
        return pedidoMapper.toDTO(pedidoRepository.save(pedidoMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando pedido: {}", id);
        pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        pedidoRepository.deleteById(id);
    }

    public List<PedidoDTO> buscarPorUsuario(Long usuarioId) {
        log.info("Buscando pedidos do usuário: {}", usuarioId);
        return pedidoRepository.findByUsuarioId(usuarioId).stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

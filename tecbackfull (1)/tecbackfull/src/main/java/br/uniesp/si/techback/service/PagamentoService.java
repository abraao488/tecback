package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.PagamentoDTO;
import br.uniesp.si.techback.mapper.PagamentoMapper;
import br.uniesp.si.techback.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;

    public List<PagamentoDTO> listarTodos() {
        log.info("Listando todos os pagamentos");
        return pagamentoRepository.findAll().stream()
                .map(pagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagamentoDTO buscarPorId(Long id) {
        log.info("Buscando pagamento por id: {}", id);
        return pagamentoRepository.findById(id)
                .map(pagamentoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado: " + id));
    }

    public PagamentoDTO criar(PagamentoDTO dto) {
        log.info("Criando pagamento para pedido: {}", dto.getPedidoId());
        if (dto.getDataPagamento() == null) dto.setDataPagamento(LocalDateTime.now());
        if (dto.getStatus() == null) dto.setStatus("PENDENTE");
        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamentoMapper.toEntity(dto)));
    }

    public PagamentoDTO atualizar(Long id, PagamentoDTO dto) {
        log.info("Atualizando pagamento: {}", id);
        pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado: " + id));
        dto.setId(id);
        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamentoMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando pagamento: {}", id);
        pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado: " + id));
        pagamentoRepository.deleteById(id);
    }
}

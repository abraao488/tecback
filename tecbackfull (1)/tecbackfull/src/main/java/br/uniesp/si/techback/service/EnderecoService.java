package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.mapper.EnderecoMapper;
import br.uniesp.si.techback.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public List<EnderecoDTO> listarTodos() {
        log.info("Listando todos os endereços");
        return enderecoRepository.findAll().stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO buscarPorId(Long id) {
        log.info("Buscando endereço por id: {}", id);
        return enderecoRepository.findById(id)
                .map(enderecoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));
    }

    public EnderecoDTO criar(EnderecoDTO dto) {
        log.info("Criando endereço: {}", dto.getLogradouro());
        return enderecoMapper.toDTO(enderecoRepository.save(enderecoMapper.toEntity(dto)));
    }

    public EnderecoDTO atualizar(Long id, EnderecoDTO dto) {
        log.info("Atualizando endereço: {}", id);
        enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));
        dto.setId(id);
        return enderecoMapper.toDTO(enderecoRepository.save(enderecoMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando endereço: {}", id);
        enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));
        enderecoRepository.deleteById(id);
    }
}

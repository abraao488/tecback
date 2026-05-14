package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.mapper.FuncionarioMapper;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public List<FuncionarioDTO> listarTodos() {
        log.info("Listando todos os funcionários");
        return funcionarioRepository.findAll().stream()
                .map(funcionarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioDTO buscarPorId(Long id) {
        log.info("Buscando funcionário por id: {}", id);
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
    }

    public FuncionarioDTO criar(FuncionarioDTO dto) {
        log.info("Criando funcionário: {}", dto.getNome());
        if (funcionarioRepository.existsByCpf(dto.getCpf()))
            throw new RuntimeException("CPF já cadastrado: " + dto.getCpf());
        if (funcionarioRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email já cadastrado: " + dto.getEmail());
        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionarioMapper.toEntity(dto)));
    }

    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        log.info("Atualizando funcionário: {}", id);
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
        dto.setId(id);
        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionarioMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando funcionário: {}", id);
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
        funcionarioRepository.deleteById(id);
    }
}

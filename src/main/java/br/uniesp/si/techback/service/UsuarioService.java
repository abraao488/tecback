package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.UsuarioDTO;
import br.uniesp.si.techback.mapper.UsuarioMapper;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listarTodos() {
        log.info("Listando todos os usuários");
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        log.info("Buscando usuário por id: {}", id);
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
    }

    public UsuarioDTO criar(UsuarioDTO dto) {
        log.info("Criando usuário: {}", dto.getEmail());
        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email já cadastrado: " + dto.getEmail());
        return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(dto)));
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        log.info("Atualizando usuário: {}", id);
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
        dto.setId(id);
        return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando usuário: {}", id);
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
        usuarioRepository.deleteById(id);
    }
}

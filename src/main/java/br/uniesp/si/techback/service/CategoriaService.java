package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.CategoriaDTO;
import br.uniesp.si.techback.mapper.CategoriaMapper;
import br.uniesp.si.techback.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> listarTodas() {
        log.info("Listando todas as categorias");
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) {
        log.info("Buscando categoria por id: {}", id);
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada: {}", id);
                    return new RuntimeException("Categoria não encontrada: " + id);
                });
    }

    public CategoriaDTO criar(CategoriaDTO dto) {
        log.info("Criando categoria: {}", dto.getNome());
        var entity = categoriaMapper.toEntity(dto);
        var salvo = categoriaRepository.save(entity);
        return categoriaMapper.toDTO(salvo);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        log.info("Atualizando categoria: {}", id);
        categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));
        dto.setId(id);
        var entity = categoriaMapper.toEntity(dto);
        return categoriaMapper.toDTO(categoriaRepository.save(entity));
    }

    public void deletar(Long id) {
        log.info("Deletando categoria: {}", id);
        categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));
        categoriaRepository.deleteById(id);
    }
}

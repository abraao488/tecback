package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.mapper.FilmeMapper;
import br.uniesp.si.techback.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeMapper filmeMapper;

    public List<FilmeDTO> listarTodos() {
        log.info("Listando todos os filmes");
        return filmeRepository.findAll().stream()
                .map(filmeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilmeDTO buscarPorId(Long id) {
        log.info("Buscando filme por id: {}", id);
        return filmeRepository.findById(id)
                .map(filmeMapper::toDTO)
                .orElseThrow(() -> {
                    log.warn("Filme não encontrado: {}", id);
                    return new RuntimeException("Filme não encontrado: " + id);
                });
    }

    public FilmeDTO criar(FilmeDTO dto) {
        log.info("Criando filme: {}", dto.getTitulo());
        return filmeMapper.toDTO(filmeRepository.save(filmeMapper.toEntity(dto)));
    }

    public FilmeDTO atualizar(Long id, FilmeDTO dto) {
        log.info("Atualizando filme: {}", id);
        filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado: " + id));
        dto.setId(id);
        return filmeMapper.toDTO(filmeRepository.save(filmeMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando filme: {}", id);
        filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado: " + id));
        filmeRepository.deleteById(id);
    }

    public List<FilmeDTO> buscarPorGenero(String genero) {
        log.info("Buscando filmes por gênero: {}", genero);
        return filmeRepository.findByGenero(genero).stream()
                .map(filmeMapper::toDTO)
                .collect(Collectors.toList());
    }
}

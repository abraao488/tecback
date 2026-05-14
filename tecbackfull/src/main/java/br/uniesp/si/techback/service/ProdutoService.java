package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.ProdutoDTO;
import br.uniesp.si.techback.mapper.ProdutoMapper;
import br.uniesp.si.techback.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public List<ProdutoDTO> listarTodos() {
        log.info("Listando todos os produtos");
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        log.info("Buscando produto por id: {}", id);
        return produtoRepository.findById(id)
                .map(produtoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public ProdutoDTO criar(ProdutoDTO dto) {
        log.info("Criando produto: {}", dto.getNome());
        return produtoMapper.toDTO(produtoRepository.save(produtoMapper.toEntity(dto)));
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        log.info("Atualizando produto: {}", id);
        produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        dto.setId(id);
        return produtoMapper.toDTO(produtoRepository.save(produtoMapper.toEntity(dto)));
    }

    public void deletar(Long id) {
        log.info("Deletando produto: {}", id);
        produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        produtoRepository.deleteById(id);
    }

    public List<ProdutoDTO> buscarPorCategoria(Long categoriaId) {
        log.info("Buscando produtos por categoria: {}", categoriaId);
        return produtoRepository.findByCategoriaId(categoriaId).stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

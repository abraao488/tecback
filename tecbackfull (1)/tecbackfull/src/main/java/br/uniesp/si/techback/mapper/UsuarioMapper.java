package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.UsuarioDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .cpf(usuario.getCpf())
                .dataNascimento(usuario.getDataNascimento())
                .telefone(usuario.getTelefone())
                .enderecoId(usuario.getEndereco() != null ? usuario.getEndereco().getId() : null)
                .build();
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        Endereco endereco = dto.getEnderecoId() != null
                ? Endereco.builder().id(dto.getEnderecoId()).build()
                : null;
        return Usuario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .cpf(dto.getCpf())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .endereco(endereco)
                .build();
    }
}

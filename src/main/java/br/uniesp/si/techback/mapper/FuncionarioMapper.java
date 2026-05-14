package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(Funcionario funcionario) {
        if (funcionario == null) return null;
        return FuncionarioDTO.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .cpf(funcionario.getCpf())
                .email(funcionario.getEmail())
                .cargo(funcionario.getCargo())
                .salario(funcionario.getSalario())
                .dataAdmissao(funcionario.getDataAdmissao())
                .enderecoId(funcionario.getEndereco() != null ? funcionario.getEndereco().getId() : null)
                .build();
    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) return null;
        Endereco endereco = dto.getEnderecoId() != null
                ? Endereco.builder().id(dto.getEnderecoId()).build()
                : null;
        return Funcionario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .cargo(dto.getCargo())
                .salario(dto.getSalario())
                .dataAdmissao(dto.getDataAdmissao())
                .endereco(endereco)
                .build();
    }
}

package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoDTO toDTO(Endereco endereco) {
        if (endereco == null) return null;
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;
        return Endereco.builder()
                .id(dto.getId())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .build();
    }
}

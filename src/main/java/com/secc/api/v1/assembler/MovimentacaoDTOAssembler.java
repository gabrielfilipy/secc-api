package com.secc.api.v1.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.Movimentacao;
import com.secc.api.model.MovimentacaoDTO;

@Component
public class MovimentacaoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public MovimentacaoDTO toModel(Movimentacao movimentacao) {
		return modelMapper.map(movimentacao, MovimentacaoDTO.class);
	}
	
	public MovimentacaoDTO toModel(Optional<Movimentacao> movimentacao) {
		return modelMapper.map(movimentacao, MovimentacaoDTO.class);
	}
	
	public List<MovimentacaoDTO> toCollectionModel(List<Movimentacao> collection) {
		return collection.stream()
					.map(m -> toModel(m))
					.collect(Collectors.toList());
	}
	
}

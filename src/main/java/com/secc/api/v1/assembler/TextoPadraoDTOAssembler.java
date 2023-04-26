package com.secc.api.v1.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.TextoPadrao;
import com.secc.api.model.TextoPadraoDTO;

@Component
public class TextoPadraoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public TextoPadraoDTO toModel(TextoPadrao textoPadrao) {
		return modelMapper.map(textoPadrao, TextoPadraoDTO.class);
	}
	
	public TextoPadraoDTO toModel(Optional<TextoPadrao> textoPadrao) {
		return modelMapper.map(textoPadrao, TextoPadraoDTO.class);
	}
	
	public List<TextoPadraoDTO> toCollectionModel(List<TextoPadrao> collection) {
		return collection.stream()
					.map(m -> toModel(m))
					.collect(Collectors.toList());
	}
	
}

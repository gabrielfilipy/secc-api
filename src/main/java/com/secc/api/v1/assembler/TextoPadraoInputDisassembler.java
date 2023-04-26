package com.secc.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.TextoPadrao;
import com.secc.api.model.input.TextoPadraoDTOInput;

@Component
public class TextoPadraoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public TextoPadrao toDomainObject(TextoPadraoDTOInput textoPadraoDTOInput) {
		return modelMapper.map(textoPadraoDTOInput, TextoPadrao.class);
	}
	
}

package com.secc.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.Movimentacao;
import com.secc.api.model.input.MovimentacaoDTOInput;

@Component
public class MovimentacaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Movimentacao toDomainObject(MovimentacaoDTOInput movimentacaoInput) {
		return modelMapper.map(movimentacaoInput, Movimentacao.class);
	}
	
}

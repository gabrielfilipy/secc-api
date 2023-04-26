package com.secc.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.Documento;
import com.secc.api.model.input.DocumentoDTOInput;

@Component
public class DocumentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Documento toDomainObject(DocumentoDTOInput documentoInput) {
		return modelMapper.map(documentoInput, Documento.class);
	}
	
}

package com.secc.api.v1.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secc.api.domain.model.Documento;
import com.secc.api.model.DocumentoDTO;

@Component
public class DocumentoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public DocumentoDTO toModel(Documento documento) {
		return modelMapper.map(documento, DocumentoDTO.class);
	}
	
	public DocumentoDTO toModel(Optional<Documento> documento) {
		return modelMapper.map(documento, DocumentoDTO.class);
	}
	
	public List<DocumentoDTO> toCollectionModel(List<Documento> collection) {
		return collection.stream()
					.map(m -> toModel(m))
					.collect(Collectors.toList());
	}
	
}

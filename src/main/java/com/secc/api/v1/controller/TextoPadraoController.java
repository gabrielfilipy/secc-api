package com.secc.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.domain.model.TextoPadrao;
import com.secc.api.domain.repository.TextoPadraoRepository;
import com.secc.api.model.TextoPadraoDTO;
import com.secc.api.model.input.TextoPadraoDTOInput;
import com.secc.api.v1.assembler.TextoPadraoDTOAssembler;
import com.secc.api.v1.assembler.TextoPadraoInputDisassembler;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Gestão texto padrão para módelo de documento.", version = "1.0"))
@RestController
@RequestMapping("/texto-padrao")
public class TextoPadraoController {
	
	public static final String CONSTRAINT_ERROR = "could not execute statement; SQL [n/a]; constraint"; 

	@Autowired
	private TextoPadraoRepository repository;
	
	@Autowired
	private TextoPadraoInputDisassembler disassembler;
	
	@Autowired
	private TextoPadraoDTOAssembler assembler;
	
	@GetMapping("/buscar")
	public List<TextoPadraoDTO> collection(String siglaPessoa, String modelo) {
		return assembler.toCollectionModel(repository.buscarColecaoDeTextos(siglaPessoa, modelo));
	}
	
	@GetMapping("/buscar-por-id")
	public TextoPadraoDTO collection(Long id) {
		return assembler.toModel(repository.findById(id));
	}
	
	@PostMapping()
	public TextoPadraoDTO adicionar(@RequestBody @Valid TextoPadraoDTOInput textoPadraoDTOInput) {
		try {
			TextoPadrao txtPadrao = disassembler.toDomainObject(textoPadraoDTOInput);
			return assembler.toModel(repository.save(txtPadrao));
		} catch (Exception e) {
			if(e.getMessage().substring(0, 50).equals(CONSTRAINT_ERROR))
				throw new RegraDeNegocioException("Já existe esse cadastro: Não é possível ter duplicidade em título, usuário e modelo.");
			else 
				throw new RegraDeNegocioException(e.getMessage());  
		}
	}
	
	@DeleteMapping("/{idTxtPadrao}")
	public ResponseEntity<?> excluir(@PathVariable Long idTxtPadrao) { 
		Optional<TextoPadrao> txtPadrao = repository.findById(idTxtPadrao);
		if (txtPadrao.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else {
			repository.deleteById(idTxtPadrao);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
}

package br.com.messiasjunior.converter.controller;

import br.com.messiasjunior.converter.exception.InvalidPeriodException;
import br.com.messiasjunior.converter.exception.InvalidTimeException;
import br.com.messiasjunior.converter.model.ConversionResponseDTO;
import br.com.messiasjunior.converter.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("converter")
public class ConverterController {
	private final ConverterService converterService;

	public ConverterController(ConverterService converterService) {
		this.converterService = converterService;
	}

	@GetMapping
	public ResponseEntity<ConversionResponseDTO> convertMatchTime(String matchTime)
			throws InvalidTimeException, InvalidPeriodException {
		return ResponseEntity.ok(new ConversionResponseDTO(converterService.convertMatchTime(matchTime)));
	}
}

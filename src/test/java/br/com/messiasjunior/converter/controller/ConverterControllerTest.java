package br.com.messiasjunior.converter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.messiasjunior.converter.exception.InvalidTimeException;
import br.com.messiasjunior.converter.service.ConverterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@DisplayName("Converter controller test")
class ConverterControllerTest {
	@MockBean
	private ConverterService converterService;

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Should return ok if conversion was made successfully")
	void convertMatchTime() throws Exception {
		var response = "90:00 +00:00 - FULL_TIME";

		when(converterService.convertMatchTime(any())).thenReturn(response);

		var request = get("/converter").contentType(MediaType.APPLICATION_JSON).requestAttr("matchTime",
				"[FT] 90:00.000");

		mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.response").value(response));
	}

	@Test
	@DisplayName("Should return bad request if the conversion throws exception")
	void throwsExceptionWhenConversionFails() throws Exception {
		when(converterService.convertMatchTime(any())).thenThrow(InvalidTimeException.class);

		var request = get("/converter").contentType(MediaType.APPLICATION_JSON).requestAttr("matchTime",
				"[FT] 90:00.000");

		mvc.perform(request).andExpect(status().isBadRequest());
	}
}
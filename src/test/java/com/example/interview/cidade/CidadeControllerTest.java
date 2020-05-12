package com.example.interview.cidade;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.interview.model.Cidade;
import com.example.interview.util.TestUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CidadeControllerTest {
	
	private static final String CIDADE_NOME_URL = "/cidade/nome/{nome}";
	private static final String CIDADE_ESTADO_URL = "/cidade/estado/{estado}";
	private static final String CIDADE_URL = "/cidade";
	
	@Autowired
	private MockMvc mockMvc;
	
	private static Cidade cidade = new Cidade();
	
	@BeforeClass
	public static void setUp() throws ParseException {
		String nome = "rec";
		String estado = "per";
		
		cidade.setNome(nome);
		cidade.setEstado(estado);
	}
	
	@Test
	public void testGetFindByNomeRecordAsJson() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CIDADE_NOME_URL, cidade.getNome()).accept(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetFindByEstadoRecordAsJson() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CIDADE_ESTADO_URL, cidade.getEstado()).accept(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testPostSaveCidadeRecordAsJson() throws Exception {
		
		String jsonFile = TestUtil.readJsonFile("/json/CidadeParameterInsert.json");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CIDADE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}

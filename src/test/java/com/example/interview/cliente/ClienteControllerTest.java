package com.example.interview.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.interview.model.Cidade;
import com.example.interview.model.Cliente;
import com.example.interview.util.TestUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
	private final static String CLIENTE_URL = "/cliente";
	private final static String CLIENTE_ID_URL = "/cliente/{id}";
	private final static String CLIENTE_NOME_URL = "/cliente/name/{name}";

	@Autowired
	private MockMvc mockMvc;

	private static Cliente cliente = new Cliente();

	private static Cidade cidade = new Cidade("Carpina", "Pernambuco");

	@BeforeClass
	public static void setUp() throws ParseException {
		Long idade = Long.getLong("0");

		cliente.setNomeCompleto("Tuatara");
		cliente.setGenero("Feminino");
		cliente.setDataNascimento(LocalDate.parse("2020-03-23"));
		cliente.setIdade(idade);
		cliente.setCidade(cidade);

	}

	@Test
	public void testPostSaveClienteRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterInsert.json");
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void testPostSaveClienteExecptionNomeRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterExceptionNomeInsert.json");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

	@Test
	public void testPostSaveClienteExecptionEstadoRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterExceptionEstadoInsert.json");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testPostSaveClienteExecptionNomeEmptyRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterExceptionEstadoEmptyInsert.json");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testPostSaveClienteExecptionEstadoEmptyRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterExceptionNomeEmptyInsert.json");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void testPostSaveClienteExecptionCidadeNullRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterExceptionCidadeNullInsert.json");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

	@Test
	public void testGetFindByIdRecordAsJson() throws Exception {
		
		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterGetInsert.json");
		
		this.mockMvc.perform(post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CLIENTE_ID_URL, 2L).accept(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetFindByIdNotFoundRecordAsJson() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CLIENTE_ID_URL, 2L).accept(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testGetFindByNomeCompletoRecordAsJson() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CLIENTE_NOME_URL, cliente.getNomeCompleto()).accept(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}

	@Test
	public void testPutUpdateNomeClienteRecordAsJson() throws Exception {
		
		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterUpdateName.json");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(CLIENTE_ID_URL, 2L).accept(APPLICATION_JSON)
				.content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testPutUpdateNomeClienteExceptionRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterUpdateName.json");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(CLIENTE_ID_URL, 0L).accept(APPLICATION_JSON)
				.content(jsonFile).contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	public void testDeleteByIdClienteRecordAsJson() throws Exception {

		String jsonFile = TestUtil.readJsonFile("/json/ClienteParameterDeleteInsert.json");
		
		this.mockMvc.perform(post(CLIENTE_URL).accept(APPLICATION_JSON).content(jsonFile).contentType(APPLICATION_JSON));
		

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(CLIENTE_ID_URL, 1L).accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteByIdClienteExceptionRecordAsJson() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(CLIENTE_ID_URL, 2L).accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

}

package com.example.pruebaalianza2.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pruebaalianza2.entities.Client;
import com.example.pruebaalianza2.services.ClientService;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientControllerTest {

	@MockBean
	private ClientService clientService;
	
	@Autowired
	private ClientController clientController;
	
	@Test
	@DisplayName(value = "test method getClients OK")
	void testGetClientsOk() {
		when(clientService.getClients()).thenReturn(getListClientMockData());
		ResponseEntity<?> listClient = clientController.getClients();
		assertNotNull(listClient);
	}
	
	@Test
	@DisplayName(value = "test method clients Failed")
	void testGetClientsFailed() throws Exception {
		when(clientService.getClients()).thenThrow(NullPointerException.class);
		ResponseEntity<?> listClient = clientController.getClients();
		assertEquals("Error al recuperar la informacion de los clientes", listClient.getBody());
	}
	
	@Test
	@DisplayName(value = "test method getBySharedKey OK")
	void testgGtBySharedKeyOk() {
		when(clientService.getBySharedKey(any())).thenReturn(getListClientMockData());
		ResponseEntity<?> listClient = clientController.getBySharedKey(any());
		assertNotNull(listClient);
	}
	
	@Test
	@DisplayName(value = "test method getBySharedKey Failed")
	void testGetBySharedKeyFailed() throws Exception {
		when(clientService.getBySharedKey(any())).thenThrow(NullPointerException.class);
		ResponseEntity<?> listClient = clientController.getBySharedKey("km");
		assertEquals("Error al recuperar la informacion del cliente con sharedKey: km", listClient.getBody());
	}
	
	@Test
	@DisplayName(value = "test method save OK")
	void testSaveOk() {
		when(clientService.saveClient(any())).thenReturn(getClientMockData());
		ResponseEntity<?> client = clientController.create(any());
		assertNotNull(client);
	}
	
	@Test
	@DisplayName(value = "test method save failed")
	void testSaveFailed() {
		when(clientService.saveClient(any())).thenThrow(NullPointerException.class);;
		ResponseEntity<?> client = clientController.create(any());
		assertEquals("Error al persistir la informacion del cliente", client.getBody());
	}
	
	@Test
	@DisplayName(value = "test method update OK")
	void testUpdateOk() {
		when(clientService.updateClient(any())).thenReturn(getClientMockData());
		ResponseEntity<?> client = clientController.update(any());
		assertNotNull(client);
	}
	
	@Test
	@DisplayName(value = "test method updated failed")
	void testUpdateFailed() {
		when(clientService.updateClient(any())).thenThrow(NullPointerException.class);;
		ResponseEntity<?> client = clientController.update(any());
		assertEquals("Error  al actualizar la informacion del cliente", client.getBody());
	}
	
	@BeforeEach
	void setup() {}
	
	@AfterEach
	void destroy() {}
	
	private List<Client> getListClientMockData() {
		Client client = new Client();
		client.setSharedKey("kmanuel");
		client.setBusinessId("Kerley Manuel");
		client.setEmail("km@alianza.com");
		client.setPhone("3000000");
		client.setDataAdded(new Date());
		
		List<Client> listClient = new ArrayList<Client>();
		listClient.add(client);
		
		return listClient;
	}
	
	private Client getClientMockData() {
		Client client = new Client();
		client.setSharedKey("kmanuel");
		client.setBusinessId("Kerley Manuel");
		client.setEmail("km@alianza.com");
		client.setPhone("3000000");
		client.setDataAdded(new Date());
		return client;
	}
}

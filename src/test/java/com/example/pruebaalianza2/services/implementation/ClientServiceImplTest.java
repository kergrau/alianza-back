package com.example.pruebaalianza2.services.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pruebaalianza2.entities.Client;
import com.example.pruebaalianza2.repositories.ClientRepository;
import com.example.pruebaalianza2.services.ClientService;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientServiceImplTest {

	@MockBean
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Test
	@DisplayName(value = "test method getClients OK")
	void testGetClients() {
		when(clientRepository.findAll()).thenReturn(getListClientMockData());
		List<Client> listClient = clientService.getClients();
		assertNotNull(listClient);
	}
	
	@Test
	@DisplayName(value = "test method getBySharedKey OK")
	void testGetBySharedKey() {
		when(clientRepository.findBySharedKey(any())).thenReturn(getListClientMockData());
		List<Client> listClient = clientService.getBySharedKey(anyString());
		assertNotNull(listClient);
	}
	
	@Test
	@DisplayName(value = "test method saveClient OK")
	void testSaveClientOK() {
		when(clientRepository.counterSharedKey(any())).thenReturn(1);
		when(clientRepository.save(any())).thenReturn(getClientMockData());
		Client client = clientService.saveClient(getClientMockData());
		assertNotNull(client);
	}
	
	@Test
	@DisplayName(value = "test method saveClient2 OK")
	void testSaveClient2OK() {
		when(clientRepository.counterSharedKey(any())).thenReturn(0);
		when(clientRepository.save(any())).thenReturn(getClientMockData());
		Client client = clientService.saveClient(getClientMockData());
		assertNotNull(client);
	}
	
	@Test
	@DisplayName(value = "test method updateClient OK")
	void testUpdateClientOK() {
		when(clientRepository.save(any())).thenReturn(getClientMockData());
		Client client = clientService.updateClient(getClientMockData());
		assertNotNull(client);
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

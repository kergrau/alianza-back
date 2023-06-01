package com.example.pruebaalianza2.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebaalianza2.entities.Client;
import com.example.pruebaalianza2.repositories.ClientRepository;
import com.example.pruebaalianza2.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> getClients() {
		List<Client> clients = clientRepository.findAll();
		return clients;
	}

	@Override
	public List<Client> getBySharedKey(String sharedKey) {
		List<Client> client = clientRepository.findBySharedKey(sharedKey);
		return client;
	}

	@Override
	public Client saveClient(Client client) {
		String sharedKey = validateSharedKey(client.getBusinessId());
		client.setSharedKey(sharedKey);
		Client clientSaved = clientRepository.save(client);
		return clientSaved;
	}

	@Override
	public Client updateClient(Client client) {
		Client clientSaved = clientRepository.save(client);
		return clientSaved;
	}

	private String validateSharedKey(String businessId) {
		String[] nameCompose = businessId.split(" ");
		char firstLetterName = nameCompose[0].toLowerCase().charAt(0);
		String sharedKey = firstLetterName + nameCompose[1].toLowerCase();
		Integer counter = clientRepository.counterSharedKey(sharedKey);
		if (counter > 0) {
			sharedKey = sharedKey + (counter + 1);
		}
		return sharedKey;
	}

}

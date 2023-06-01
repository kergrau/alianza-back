package com.example.pruebaalianza2.services;

import java.util.List;

import com.example.pruebaalianza2.entities.Client;

public interface ClientService {

	List<Client> getClients();

	List<Client> getBySharedKey(String sharedKey);

	Client saveClient(Client client);

	Client updateClient(Client client);

}

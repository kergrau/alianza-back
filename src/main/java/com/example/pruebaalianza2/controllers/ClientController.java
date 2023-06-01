package com.example.pruebaalianza2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebaalianza2.entities.Client;
import com.example.pruebaalianza2.services.ClientService;

@RestController
@RequestMapping(path = "/api/client")
@CrossOrigin(origins = "*")
public class ClientController {

	private static Logger _logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;

	@GetMapping(path = "/get-clients")
	public ResponseEntity<?> getClients() {
		try {
			List<Client> clients = clientService.getClients();
			return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);

		} catch (Exception e) {
			_logger.error("Error al recuperar la informacion de los clientes ", e);
			return new ResponseEntity<>("Error al recuperar la informacion de los clientes",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/get-client")
	public ResponseEntity<?> getBySharedKey(@RequestParam String sharedKey) {
		try {
			List<Client> client = clientService.getBySharedKey(sharedKey);
			return new ResponseEntity<List<Client>>(client, HttpStatus.OK);

		} catch (Exception e) {
			_logger.error("Error al recuperar la informacion de los clientes ", e);
			return new ResponseEntity<>("Error al recuperar la informacion del cliente con sharedKey: " + sharedKey,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/save")
	public ResponseEntity<?> create(@RequestBody Client client) {
		try {
			Client client_saved = clientService.saveClient(client);
			return new ResponseEntity<Client>(client_saved, HttpStatus.CREATED);
		} catch (Exception e) {
			_logger.error("Error al guardar la informacion del cliente ", e);
			return new ResponseEntity<>("Error al persistir la informacion del cliente",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/update")
	public ResponseEntity<?> update(@RequestBody Client client) {
		try {
			Client client_saved = clientService.updateClient(client);
			return new ResponseEntity<Client>(client_saved, HttpStatus.OK);
		} catch (Exception e) {
			_logger.error("Error al actualizar la informacion del cliente ", e);
			return new ResponseEntity<>("Error  al actualizar la informacion del cliente",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

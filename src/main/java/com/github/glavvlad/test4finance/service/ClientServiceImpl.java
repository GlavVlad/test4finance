package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.ClientDto;
import com.github.glavvlad.test4finance.entity.Client;
import com.github.glavvlad.test4finance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vlad on 08.10.17.
 */

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	@Override
	public ClientDto save(ClientDto dto) {
		Client client = repository.save(new Client(dto.getFirstName(), dto.getSurname()));
		return new ClientDto(client.getId(), client.getFirstName(), client.getSurname());
	}

}

package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.models.Client;
import com.sandrapeinados.pelugestion.persistence.entities.ClientEntity;
import com.sandrapeinados.pelugestion.persistence.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClienteRepository clientRepo;
    @Override
    public Client saveClient(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(client.getName());
        clientEntity.setSurname(client.getSurname());
        clientEntity.setCellphone(client.getCellphone());
        clientRepo.save(clientEntity);

        client.setId(clientEntity.getId());
        return client;
    }
}

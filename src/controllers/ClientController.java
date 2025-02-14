package controllers;

import domain.Client;
import factories.ClientFactory;
import services.ClientService;

public class ClientController {
    private final ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    public void registerClient() {
        try {
            Client novoCliente = ClientFactory.createClient();
            clientService.saveClient(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
} 
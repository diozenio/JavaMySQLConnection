package services;

import domain.Client;
import dao.ClientDAO;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public void saveClient(Client client) {
        clientDAO.save(client);
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Client findClientByCpf(String cpf) {
        return clientDAO.findByCpf(cpf);
    }
} 
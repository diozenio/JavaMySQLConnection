package factories;

import domain.Client;
import utils.Input;

public class ClientFactory {
    public static Client createClient() {
        String cpf = Input.getString("Digite o CPF do cliente: ");
        String name = Input.getString("Digite o nome do cliente: ");
        String address = Input.getString("Digite o endereço do cliente: ");
        String phone = Input.getString("Digite o telefone do cliente: ");
        return new Client(cpf, name, address, phone);
    }
} 
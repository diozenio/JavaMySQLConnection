package domain;

public class Client extends Person {
    public Client(String cpf, String name, String address, String phone) {
        super(cpf, name, address, phone);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + name + '\'' +
                ", endereco='" + address + '\'' +
                ", telefone='" + phone + '\'' +
                '}';
    }

    @Override
    public void print() {
        System.out.println("CPF: " + cpf + " | Nome: " + name + " | Endereço: " + address + " | Telefone: " + phone);
    }
} 
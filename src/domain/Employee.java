package domain;

public class Employee extends Person {
    public Employee(String cpf, String name, String address, String phone) {
        super(cpf, name, address, phone);
    }

    @Override
    public String toString() {
        return "Funcionário{" +
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
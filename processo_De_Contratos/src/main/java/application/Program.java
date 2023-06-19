package application;

import entities.Contract;
import entities.Installment;
import service.ContractService;
import service.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner leitor = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Numero: ");
        int number = Integer.parseInt(leitor.nextLine());
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(leitor.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        double totalValue = Double.parseDouble(leitor.nextLine());

        Contract contract = new Contract(number, date, totalValue);
        System.out.print("Entre com os número de parcelas: ");
        int n = Integer.parseInt(leitor.nextLine());

        ContractService contractService = new ContractService(new PaypalService());

        contractService.processContract(contract, n);

        System.out.println("Parcelas:");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }

        leitor.close();
    }

}

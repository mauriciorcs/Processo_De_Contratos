package service;
import entities.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ContractServiceTest {

    DateTimeFormatter fmt;
    LocalDate date;

    @BeforeEach
    void setUp() {
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date = LocalDate.parse("25/06/2023", fmt);

    }

    @Test
    void processContractDateTest() {
        Contract contract = new Contract(123, date, 600.0);
        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, 3);

        assertEquals("25/07/2023", contract.getDate().plusMonths(1).format(fmt));
        assertEquals("25/08/2023", contract.getDate().plusMonths(2).format(fmt));
        assertEquals("25/09/2023", contract.getDate().plusMonths(3).format(fmt));
    }

    @Test
    void gettsContract(){
        Contract contract = new Contract(123, date, 600.0);
        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, 3);
        assertEquals(600.0, contract.getTotalValue());
        assertEquals(123, contract.getNumber());

    }
}
package entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class InstallmentTest {

    @Test
    void testToString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("25/06/2023", fmt);
        Installment installment = new Installment(date, 600.0);
        assertEquals("25/06/2023 - 600,00", installment.toString());

    }
}
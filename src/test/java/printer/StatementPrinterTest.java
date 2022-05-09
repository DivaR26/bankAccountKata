package printer;

import transaction.Amount;
import transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatementPrinterTest {

    @Mock
    Console accountConsole;

    private StatementPrinter statementPrinter;

    @BeforeEach
    public void init() {
        statementPrinter = new StatementPrinter(accountConsole);
    }

    @Test
    public void printHeader() {
        statementPrinter.printAccountHistory(new ArrayList<>());
        verify(accountConsole).printLine("DATE | AMOUNT | ACCOUNT BALANCE");
    }

    @Test
    public void printAccountHistory() {
        List<Transaction> transactions = createMockTransactions();

        statementPrinter.printAccountHistory(transactions);

        InOrder inOrder = Mockito.inOrder(accountConsole);
        inOrder.verify(accountConsole).printLine("DATE | AMOUNT | ACCOUNT BALANCE");
        inOrder.verify(accountConsole).printLine("2022-03-05 | -50,00 | 1250,00");
        inOrder.verify(accountConsole).printLine("2022-02-03 | 1200,00 | 1300,00");
        inOrder.verify(accountConsole).printLine("2022-01-02 | 100,00 | 100,00");
    }

    private List<Transaction> createMockTransactions() {
        return Arrays.asList(
                new Transaction(LocalDate.of(2022, 1, 2), Amount.valueOf(100))
                , new Transaction(LocalDate.of(2022, 2, 3), Amount.valueOf(1200))
                , new Transaction(LocalDate.of(2022, 3, 5), Amount.valueOf(-50))
        );
    }

}
package featureAcceptanceTest;

import account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.Console;
import printer.StatementPrinter;
import transaction.Amount;
import transaction.TransactionRepository;
import utils.DateGenerator;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;

/**
 * Acceptance test for US3
 */
@ExtendWith(MockitoExtension.class)
class AccountHistoryFeatureTest {

    @Mock
    private Console accountConsole;
    @Mock
    private DateGenerator dateGenerator;

    private Account account;

    @BeforeEach
    public void init() {
        TransactionRepository transactionRepository = new TransactionRepository(dateGenerator);
        StatementPrinter statementPrinter = new StatementPrinter(accountConsole);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void checkHistoryOfOperations() {
        given(dateGenerator.getCurrentDate()).willReturn(
                LocalDate.of(2022, 1, 2),
                LocalDate.of(2022, 2, 3),
                LocalDate.of(2022, 3, 5)
        );

        //input
        account.deposit(Amount.valueOf(100));
        account.deposit(Amount.valueOf(1200));
        account.withdraw(Amount.valueOf(50));

        //treatment
        account.printStatement();

        //output
        InOrder inOrder = Mockito.inOrder(accountConsole);
        inOrder.verify(accountConsole).printLine("DATE | AMOUNT | ACCOUNT BALANCE");
        inOrder.verify(accountConsole).printLine("2022-03-05 | -50,00 | 1250,00");
        inOrder.verify(accountConsole).printLine("2022-02-03 | 1200,00 | 1300,00");
        inOrder.verify(accountConsole).printLine("2022-01-02 | 100,00 | 100,00");


    }
}

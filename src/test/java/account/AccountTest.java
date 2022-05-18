package account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.StatementPrinter;
import transaction.Amount;
import transaction.TransactionRepository;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountTest {

    private Account account;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private StatementPrinter statementPrinter;

    @BeforeEach
    void init() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
     void deposit() {
        account.deposit(Amount.valueOf(100));
        verify(transactionRepository, times(1)).createDepositTransaction(Amount.valueOf(100));
    }

    @Test
     void withdraw() {
        account.withdraw(Amount.valueOf(100));
        verify(transactionRepository, times(1)).createWithdrawalTransaction(Amount.valueOf(100));
    }

    @Test
    void printStatement() {
        given(transactionRepository.findAllTransactions()).willReturn(new ArrayList<>());
        account.printStatement();
        verify(statementPrinter, times(1)).printAccountHistory(any());
    }
}

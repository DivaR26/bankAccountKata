package account;

import account.Account;
import transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.StatementPrinter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    Account account;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;

    @BeforeEach
    public void init(){
        account=new Account(transactionRepository,statementPrinter);
    }

    @Test
    public void deposit(){
        account.deposit(100);
        verify(transactionRepository, times(1)).createDepositTransaction(100);
    }

    @Test
    public void withdraw(){
        account.withdraw(100);
        verify(transactionRepository, times(1)).createWithdrawalTransaction(100);
    }

    @Test
    public void printStatement(){
        given(transactionRepository.findAllTransactions()).willReturn(new ArrayList<>());
        account.printStatement();
        verify(statementPrinter, times(1)).printAccountHistory(any());
    }
}
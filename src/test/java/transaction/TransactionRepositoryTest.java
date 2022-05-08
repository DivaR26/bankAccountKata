package transaction;

import utils.DateGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    private static final LocalDate TRANSACTION_DATE = LocalDate.of(2022,4,22);

    @Mock
    DateGenerator dateGenerator;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void init() {
        transactionRepository = new TransactionRepository(dateGenerator);
        given(dateGenerator.getCurrentDate()).willReturn(TRANSACTION_DATE);
    }

    @Test
    public void createDepositTransaction() {
        given(dateGenerator.getCurrentDate()).willReturn(TRANSACTION_DATE);
        transactionRepository.createDepositTransaction(100);
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        assertEquals(1, transactions.size());
        assertThat(transactions.get(0), is(transaction(100)));
    }

    @Test
    public void createWithdrawalTransaction() {
        transactionRepository.createWithdrawalTransaction(50);
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        assertEquals(1, transactions.size());
        assertThat(transactions.get(0), is(transaction(-50)));
    }

    private Transaction transaction(int amount) {
        return new Transaction(TransactionRepositoryTest.TRANSACTION_DATE, amount);
    }

}
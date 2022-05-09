package transaction;

import utils.DateGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private final DateGenerator dateGenerator;
    List<Transaction> accountTransactions = new ArrayList<>();

    public TransactionRepository(DateGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }

    public void createDepositTransaction(Amount amount) {
        Transaction depositTransaction = new Transaction(dateGenerator.getCurrentDate(), amount);
        accountTransactions.add(depositTransaction);
    }

    public void createWithdrawalTransaction(Amount amount) {
        Transaction withdrawalTransaction = new Transaction(dateGenerator.getCurrentDate(), amount.asNegative());
        accountTransactions.add(withdrawalTransaction);
    }

    public List<Transaction> findAllTransactions() {
        return Collections.unmodifiableList(accountTransactions);
    }
}

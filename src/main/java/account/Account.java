package account;

import printer.StatementPrinter;
import transaction.Amount;
import transaction.Transaction;
import transaction.TransactionRepository;

import java.util.List;

public class Account {

    TransactionRepository transactionRepository;
    StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(Amount amount) {
        transactionRepository.createDepositTransaction(amount);
    }

    public void withdraw(Amount amount) {
        transactionRepository.createWithdrawalTransaction(amount);
    }

    public void printStatement() {
        List<Transaction> allTransactions = transactionRepository.findAllTransactions();
        statementPrinter.printAccountHistory(allTransactions);
    }
}

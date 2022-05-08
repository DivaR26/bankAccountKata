package printer;

import transaction.Transaction;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StatementPrinter {

    private final Console accountConsole;
    private int accountBalance = 0;
    private final DecimalFormat decimalFormatter = new DecimalFormat("#.00");

    public StatementPrinter(Console accountConsole) {
        this.accountConsole = accountConsole;
    }


    public void printAccountHistory(List<Transaction> allTransactions) {
        accountConsole.printLine("DATE | AMOUNT | ACCOUNT BALANCE");
        allTransactions.stream()
                .map(this::transactionAsString)
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(accountConsole::printLine);
    }

    private String transactionAsString(Transaction transaction) {
        return transaction.getDate().toString() + " | " + formatAmount(transaction.getAmount())
                + " | " + formatAmount(calculateAccountBalance(transaction.getAmount()));
    }

    private int calculateAccountBalance(int amount) {
        accountBalance += amount;
        return accountBalance;
    }

    private String formatAmount(int amount) {
        return decimalFormatter.format(amount);
    }
}

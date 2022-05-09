package printer;

import transaction.Amount;
import transaction.Transaction;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StatementPrinter {

    private final Console accountConsole;
    Amount accountBalance = Amount.ZERO;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

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

    private String formatAmount(Amount amount) {
        return amount.format(decimalFormat);

    }

    private Amount calculateAccountBalance(Amount amount) {
        accountBalance = accountBalance.addAndGet(amount);
        return accountBalance;
    }

}

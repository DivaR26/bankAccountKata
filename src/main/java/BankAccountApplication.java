import account.Account;
import printer.Console;
import printer.StatementPrinter;
import transaction.TransactionRepository;
import utils.DateGenerator;

import static transaction.Amount.valueOf;

public class BankAccountApplication {

    public static void main(String[] args) {

        DateGenerator dateGenerator = new DateGenerator();
        TransactionRepository transactionRepository = new TransactionRepository(dateGenerator);
        Console console = new Console();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        Account account = new Account(transactionRepository, statementPrinter);

        account.deposit(valueOf(100));
        account.deposit(valueOf(1200));
        account.withdraw(valueOf(50));

        account.printStatement();
    }
}

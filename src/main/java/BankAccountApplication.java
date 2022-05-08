import account.Account;
import utils.DateGenerator;
import transaction.TransactionRepository;
import printer.Console;
import printer.StatementPrinter;

public class BankAccountApplication {

    public static void main(String[] args) {

        DateGenerator dateGenerator = new DateGenerator();
        TransactionRepository transactionRepository = new TransactionRepository(dateGenerator);
        Console console = new Console();
        StatementPrinter statementPrinter= new StatementPrinter(console);
        Account account= new Account(transactionRepository, statementPrinter);

        account.deposit(100);
        account.deposit(1200);
        account.withdraw(50);

        account.printStatement();
    }
}

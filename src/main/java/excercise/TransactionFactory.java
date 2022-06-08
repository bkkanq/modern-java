package excercise;

import java.util.Arrays;
import java.util.List;

import excercise.model.Trader;
import excercise.model.Transaction;

public class TransactionFactory {
    public static List<Trader> getTrader() {
        return List.of(new Trader("Sam", "New York"),
                       new Trader("Minsu", "Seoul"),
                       new Trader("Alice", "Singapore"),
                       new Trader("Lilly", "Seoul"));
    }

    public static List<Transaction> getTransaction() {
        return List.of(new Transaction(new Trader("samy", "New York"), 2022, 100),
                       new Transaction(new Trader("minsu", "Seoul"), 2011, 200),
                       new Transaction(new Trader("mina", "Seoul"), 2011, 540),
                       new Transaction(new Trader("alice", "Singapore"), 2011, 310),
                       new Transaction(new Trader("lilly", "Busan"), 2012, 4000),
                       new Transaction(new Trader("sam", "New York"), 2020, 200));
    }

    public static List<Transaction> getTransactionAsList() {
        return Arrays.asList(new Transaction(new Trader("samy", "New York"), 2022, 100),
                             new Transaction(new Trader("minsu", "Seoul"), 2011, 200),
                             new Transaction(new Trader("mina", "Seoul"), 2011, 540),
                             new Transaction(new Trader("alice", "Singapore"), 2011, 310),
                             new Transaction(new Trader("lilly", "Busan"), 2012, 4000),
                             new Transaction(new Trader("sam", "New York"), 2020, 200));
    }
}

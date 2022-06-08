package excercise8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import excercise.TransactionFactory;
import excercise.model.Transaction;

public class ConcurrentModificationTest {
    private List<Transaction> transactions = TransactionFactory.getTransaction();
    private List<Transaction> transactionsAsList = TransactionFactory.getTransactionAsList();

//    @Test
//    public void concurrentModification_1() {
//        for (Transaction transaction : transactions) {
//            if (transaction.getYear() == 2011) {
//                transactions.remove(transaction);
//            }
//        }
//    }
//
//    @Test
//    public void concurrentModification_2() {
//        for (Iterator<Transaction> iterator = transactions.iterator();
//             iterator.hasNext(); ) {
//            Transaction transaction = iterator.next();
//            if (transaction.getYear() == 2011) {
//               transactions.remove(transaction);
//            }
//        }
//    }
//
//    @Test
//    public void right_fail() {
//        for (Iterator<Transaction> iterator = transactions.iterator();
//             iterator.hasNext();) {
//            Transaction transaction = iterator.next();
//            if (transaction.getYear() == 2011) {
//                iterator.remove();
//            }
//        }
//    }

    @Test
    public void right_removeif() {
        transactionsAsList.removeIf(transaction -> transaction.getYear() == 2011);
        System.out.println(transactionsAsList);
    }

    @Test
    public void map_order() {
        Map<String, String> favoriteMovies = Map.ofEntries(Map.entry("Raphael", "Starwars"),
                                                           Map.entry("Cristina", "Matrix"),
                                                           Map.entry("Olivia", "James Bond"));

        favoriteMovies.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }

    @Test
    public void map_remove() {
        Map<String, String> favoriteMovies = Map.ofEntries(Map.entry("Raphael", "Starwars"),
                                                           Map.entry("Cristina", "Matrix"),
                                                           Map.entry("Olivia", "James Bond"));

        System.out.println(favoriteMovies.remove("Olivia", "test"));
        ConcurrentHashMap<String, Long> test = new ConcurrentHashMap<>();
//        test.reduceEntriesToDouble()
//        test.size();
//        test.mappingCount();
//        test.keySet();
//        test.newKeySet();

    }
}

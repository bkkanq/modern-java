package excercise.service;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import excercise.TransactionFactory;
import excercise.model.Trader;
import excercise.model.Transaction;

public class TransactionService {
    private List<Transaction> transactions = TransactionFactory.getTransaction();

    public List<Transaction> exercise1() {
        // 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        return transactions.stream()
                           .filter(transaction -> transaction.getYear() == 2011)
                           //.sorted(Comparator.comparingInt(Transaction::getValue))
                           .sorted(comparing(Transaction::getValue))
//                .sorted(Comparator.comparingInt(Transaction::getValue).reversed())
                           .collect(Collectors.toList());
    }

    public List<String> exercise2() {
        // 거래자가 근무하는 도시를 중복없이 나열
        return transactions.stream()
                           .map(Transaction::getTrader)
                           .map(Trader::getCity)
                           .distinct()
                           .collect(Collectors.toList());
    }

    public List<Trader> exercise3() {
        // 서울에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        return transactions.stream()
                           .map(Transaction::getTrader)
//                           .filter(trader -> trader.getCity() == "Seoul")
                           .filter(trader -> trader.getCity().equals("Seoul"))
                           .distinct()
                           .sorted(comparing(Trader::getName))
                           .collect(Collectors.toList());
    }

    public List<String> exercise4() {
        // 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환

        String answer = transactions.stream()
                                    .map(transaction -> transaction.getTrader().getName())
                                    .distinct()
                                    .sorted()
                                    .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(answer);

        String answer_improve_using_joining = transactions.stream()
                                                          .map(transaction -> transaction.getTrader().getName())
                                                          .distinct()
                                                          .sorted()
                                                          .collect(Collectors.joining());
        System.out.println(answer_improve_using_joining);
        return transactions.stream()
                           .map(Transaction::getTrader)
                           .map(Trader::getName)
                           .map(name -> Arrays.stream(name.split("")).sorted().collect(Collectors.joining()))
                           .collect(Collectors.toList());

    }

    public boolean exercise5() {
        // 부산에 거래자가 있는가?
        // return transactions.stream().findAny().isPresent();
        return transactions.stream()
                           .anyMatch(transaction -> transaction.getTrader().getCity().equals("Busan"));
    }

    public List<Integer> exercise6() {
        // 서울에서 거주하는 거래자의 모든 트랜잭션 값을 출력
        return transactions.stream()
                           .filter(t -> "Seoul".equals(t.getTrader().getCity()))
//                           .filter(t -> t.getTrader().getCity() == "Seoul")
                           .map(Transaction::getValue)
                           .collect(Collectors.toList());
    }

    public int exercise7() {
        // 전체 트랜잭션 중 최댓값은 얼마?
        return transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
    }

    public int exercise8() {
        // 전체 트랜잭션 중 최솟값은 얼마?
//        return transactions.stream().map(Transaction::getValue).reduce(10000000, Integer::min);
        Optional<Transaction> min = transactions.stream()
                                            .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        return min.get().getValue();
    }
}

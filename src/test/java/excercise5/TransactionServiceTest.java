package excercise5;

import java.util.List;

import org.junit.Test;

import excercise.model.Transaction;
import excercise.service.TransactionService;

public class TransactionServiceTest {
    private TransactionService transactionService = new TransactionService();

    @Test
    public void exercise1() {
        // 2011년에 일어난 모든 트랜잭션값을 찾아 오름차순으로 정렬
        List<Transaction> result = transactionService.exercise1();
        result.stream().forEach(System.out::println);
    }

    @Test
    public void excise2() {
        // 거래자가 근무하는 도시를 중복없이 나열
        transactionService.exercise2()
                          .stream().forEach(System.out::println);
    }

    @Test
    public void exercise3() {
        // 서울에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        transactionService.exercise3()
                .stream().forEach(System.out::println);
    }

    @Test
    public void exercise4() {
        // 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환
        transactionService.exercise4()
                          .stream().forEach(System.out::println);
    }

    @Test
    public void exercise5() {
        // 부산에 거래자가 있는가?
        if(transactionService.exercise5()) {
            System.out.println("Exist");
        } else {
            System.out.println("Does not Exist");
        }
    }

    @Test
    public void exercise6() {
        // 서울에서 거주하는 거래자의 모든 트랜잭션 값을 출력
        transactionService.exercise6()
                          .stream().forEach(System.out::println);
    }

    @Test
    public void exercise7() {
        // 전체 트랜잭션 중 최댓값은 얼마?
        int max = transactionService.exercise7();
        System.out.println("Max : " + max);
    }

    @Test
    public void exercise8() {
        // 전체 트랜잭션 중 최솟값은 얼마?
        int min = transactionService.exercise8();
        System.out.println("Min : " + min);
    }

    @Test
    public void fibTest() {
        transactionService.fib().stream().forEach(e -> System.out.println("(" + e[0] + ", " + e[1] + ", " + e[2] + ")"));
    }
}

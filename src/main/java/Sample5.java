import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import model.Dish;
import model.Dish.Type;

public class Sample5 {

  private final List<Dish> menu;

  public Sample5(List<Dish> menu) {
    this.menu = menu;
  }

  public void filtering() {
    //predicate filtering
    List<Dish> vegeterianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());
    System.out.println(vegeterianMenu);

    //고유요소 필터링
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    numbers.stream()
           .filter(i -> i % 2 == 0)
           .distinct()
           .forEach(System.out::println);
  }

  /*
   * Stream 요소를 선택하거나 skip 하는 방법
   * 1. Predicate를 이용
   * 2. Stream 처음 몇개의 요소를 무시하는 방법
   * 3. 특정 크기로 스트림 줄이는 방법 등
   */
  public void takeWhile_dropWhile() {
    List<Dish> dishesSortedByCalories = menu.stream().sorted(comparing(Dish::getCalories)).collect(toList());
    takewhile(dishesSortedByCalories);
    dropwhile(dishesSortedByCalories);

    //drowhile은 takewhile과 정 반대 작업을 수행

  }

  private void takewhile(List<Dish> sortedDish) {
    // 350칼로리보다 크거나 작은 요리가 나왔을 때 반복작업을 중단
    sortedDish.stream()
              .takeWhile(dish -> dish.getCalories() <= 350).forEach(System.out::println);

  }

  private void dropwhile(List<Dish> sortedDish) {
    //350칼로리보다 큰 요소에 대해 탐색
    sortedDish.stream()
              .dropWhile(dish -> dish.getCalories() <= 350)
              .collect(toList());

    // predicate가 거짓이 되는 시점까지 발견된 오소를 버린다.
  }

  public void limit_skip() {
    List<Dish> dishesSortedByCalories = menu.stream().sorted(comparing(Dish::getCalories)).collect(toList());
    limit(dishesSortedByCalories);
    skip(dishesSortedByCalories);

    // limit과 skip은 서로 상호 보안적
  }

  //스트림 축소
  private void limit(List<Dish> sortedDish) {
    sortedDish.stream()
              .filter(dish -> dish.getCalories() > 300)
              .limit(3)
              .collect(toList());
  }

  private void skip(List<Dish> sortedDish) {
    sortedDish.stream()
              .filter(dish -> dish.getCalories() > 300)
              .skip(2)
              .collect(toList());
  }

  public void quize5_1() {
    //스트림을 이용하여 처음 등장하는 두 고기요리 필터링

    // //처음 두 고기요리를 가져오라는 뜻이었네
    menu.stream().filter(dish -> !dish.isVegetarian()).skip(2).forEach(System.out::println);

    //답
    menu.stream().filter(d -> d.getType() == Type.MEAT).limit(2).collect(toList());
  }

  // Mapping. map, flatMap
  // Practice-1 ["Hello", "World"] -> ["H", "e", "l", "o", "W", "r", "d"]
  public void practice_1() {

    // Hint 아래 예를 활용해볼까?
    // String[] arrayOfWords = {"Goodbye", "World"};
    // Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

    List<String> words = Arrays.asList("Hello", "World");
//    words.stream()
//         .map(word -> word.split(""))
//         .map(s -> Arrays.stream(s))
//         .distinct()
//         .forEach(System.out::println);

    //List<Stream<String>> 이 만들어지게되어 문제가 해결되지 않음

    //flatMap!
    //flatMap은 하나의 평면화된 스트림을 반환. 스트림의 각 값을 다른 스트림으로 만든 다음에 모든 스트림을 하나의 스트림으로 연결하는 기능 수행
    words.stream()
         .map(s -> s.split(""))
         .flatMap(Arrays::stream)
         .distinct()
         .forEach(System.out::println);
  }

  public void quiz5_2() {
    // 숫자리스트가 주어졌을 때 각숫자의 제곱근으로 이루어진 리스트를 반환하시오
    // [1,2,3,4,5] 가 주어지면 [1, 4, 9, 16, 25]
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    numbers.stream().map(n -> n * n).forEach(System.out::println);

    // 두개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
    // [1,2,3], [3,4]가 주어지면 [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)]
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);

    // 잘못된 것
    numbers1.stream()
            .map(e -> numbers2.stream()
                              .map(n -> new int[] { e, n })
                              .collect(toList()))
            .forEach(e -> {
              e.stream().map(n -> "(" + n[0] + ", " + n[1] + ")").forEach(System.out::println);
              System.out.println(", ");
            });
    //(1, 3)
    //(1, 4)
    //,
    //(2, 3)
    //(2, 4)
    //,
    //(3, 3)
    //(3, 4)
    //,

    // 정답
    numbers1.stream()
            .flatMap(i -> numbers2.stream()
                                  .map(j -> new int[] { i, j }))
            .map(i -> "(" + i[0] + ", " + i[1] + ")")
            .forEach(i -> {
              System.out.println(i);
              System.out.println(", ");
            });

    //(1, 3)
    //,
    //(1, 4)
    //,
    //(2, 3)
    //,
    //(2, 4)
    //,
    //(3, 3)
    //,
    //(3, 4)
    //,
  }

  public void quiz5_3() {
    //이전 예제에서 합이 3으로 나누어 떨어지는 쌍만 반환하려면 어떻게 해야할까? 예를들어 (2,4), (3,3)을 반환해야 한다
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);

    numbers1.stream()
            .flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
            .filter(array -> {
              if ((array[0] + array[1]) % 3 == 0) {
                //System.out.println("(" + array[0] + ", " + array[1] + ") pass !");
                return true;
              } else {
                //System.out.println("(" + array[0] + ", " + array[1] + ") fail !");
                return false;
              }
            })
            .map(array -> "(" + array[0] + ", " + array[1] + ")")
            .forEach(System.out::println);

    //위의것 보단..
    numbers1.stream()
            .flatMap(
                i -> numbers2.stream()
                             .filter(j -> (i + j) % 3 == 0)
                             .map(j -> new int[] { i, j })
            )
            .map(array -> "(" + array[0] + ", " + array[1] + ")")
            .forEach(System.out::println);

  }

}

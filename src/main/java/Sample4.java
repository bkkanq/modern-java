import static java.util.stream.Collectors.toList;

import java.util.List;
import model.Dish;

public class Sample4 {

  private final List<Dish> menu;

  public Sample4(List<Dish> menu) {
    this.menu = menu;
  }


  public List<String> getThreeHighCaloricDishNames() {
    return menu.stream()
               .filter(dish -> dish.getCalories() > 300)
               .map(Dish::getName)
               .limit(3)
               .collect(toList());
  }

  public List<String> getMenuName() {
    return menu.stream()
               .map(Dish::getName) //메서드 파라미터
               .collect(toList());
  }

  public List<String> quize4_1() {
    /*
     * Refactor following code
    List<String> highCaloricDishes = new ArrayList<>();
    Iterator<Dish> iterator = menu.iterator();
    while(iterator.hasNext()) {
      Dish dish = iterator.next();
      if(dish.getCalories() > 300) {
        highCaloricDishes.add(dish.getName());
      }
    }
    */
    /*
    List<String> names = new ArrayList<>();
    for (Dish dish: menu) {
      names.add(dish.getName());

    }
    */

    return menu.stream()
               .filter(dish -> dish.getCalories() > 300)
               .map(Dish::getName)
               .collect(toList());

  }

  public void quize4_2() {
    // 아래 파이프라인에서 중간연산자와 최종연산자를 구별
    long count = menu.stream()
                     .filter(d -> d.getCalories() > 300)
                     .distinct()
                     .limit(3)
                     .count();
    //중간연산자 : filter, distinct, limit
    //최종연산자 : count
    //해설 : 마지막 연산 count는 스트림이 아닌 long을 반환하므로 최종연산자.
    //      filter, distinct, limit는 stream을 반환하여 서로 연결하므로 중간연산자.
  }

  public void debug_stream_pipeline() {
    List<String> names =
        menu.stream()
            .filter(dish -> {
              System.out.println("filtering:" + dish.getName());
              return dish.getCalories() > 300;
            }) // 필터링한 요리명을 출력
            .map(dish -> {
              System.out.println("mapping:" + dish.getName());
              return dish.getName();
            })
            .limit(3)
            .collect(toList());

    System.out.println(names);
  }
}

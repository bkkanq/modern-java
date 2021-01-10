import java.util.Arrays;
import java.util.List;

import model.Dish;
import model.Dish.Type;

public class ModernJavaInAction {

  private static final List<Dish> menu = Arrays.asList(
      new Dish("port", false, 800, Type.MEAT),
      new Dish("beef", false, 700, Type.MEAT),
      new Dish("chicken", false, 400, Type.MEAT),
      new Dish("french fries", true, 530, Type.OTHER),
      new Dish("rice", true, 350, Type.OTHER),
      new Dish("season fruit", true, 120, Type.OTHER),
      new Dish("pizza", true, 550, Type.OTHER),
      new Dish("prawns", false, 300, Type.FISH),
      new Dish("salmon", false, 450, Type.FISH)
  );

  public static void main(String[] args) {
    /*
    Sample4 sample4 = new Sample4(menu);
    System.out.println(sample4.getThreeHighCaloricDishNames());

    System.out.println(sample4.getMenuName());

    sample4.debug_stream_pipeline();
    */

    Sample5 sample5 = new Sample5(menu);
    //sample5.takeWhile_dropWhile();
    //sample5.practice_1();
    //sample5.quiz5_2();
    sample5.quiz5_3();
  }
}

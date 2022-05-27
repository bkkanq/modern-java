package excercise.service;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import excercise.model.Menu;
import excercise.model.Menu.DishType;

public class MenuService {
    public static List<Menu> getMenu() {
        return List.of(new Menu(DishType.MEAT, "pork", 800),
                       new Menu(DishType.MEAT, "beef", 800),
                       new Menu(DishType.FISH, "salmon", 200),
                       new Menu(DishType.OTHERS, "french fries", 700),
                       new Menu(DishType.OTHERS, "pizza", 700)
        );
    }

    public static Map<String, List<String>> getDishTags() {
        return Map.of("pork", asList("greasy", "salty"),
                      "beef", asList("salty", "roasted"),
                      "chicken", asList("fried", "crisp"),
                      "french fries", asList("greasy", "fried"),
                      "rice", asList("light", "natural"),
                      "season fruit", asList("fresh", "natural"),
                      "pizza", asList("tasty", "salty"),
                      "prawns", asList("tasty", "roasted"),
                      "salmon", asList("delicious", "fresh"));
    }

    public void getDishNamesByType() {
        Map<String, List<String>> dishTags = getDishTags();
        Map<DishType, Set<String>> test = getMenu().stream()
                                                   .collect(groupingBy(Menu::getType,
                                     flatMapping(menu -> dishTags.get(menu.getName()).stream(),
                                             toSet())));
        //List<String>.stream() -> Stream<String>

        // 'mapping(java.util.function.Function<? super excercise.model.Menu,? extends java.util.stream.Stream<java.lang.String>>,
        // java.util.stream.Collector<? super java.util.stream.Stream<java.lang.String>,java.lang.Object,java.lang.Object>)'
        // in 'java.util.stream.Collectors' cannot be applied to '(<lambda expression>)'
    }
}

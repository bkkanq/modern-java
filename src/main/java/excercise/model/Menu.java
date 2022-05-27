package excercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Menu {
    private DishType type;
    private String name;
    private int calories;
    public enum DishType {MEAT, FISH, OTHERS}
}


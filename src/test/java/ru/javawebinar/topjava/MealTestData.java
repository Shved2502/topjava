package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 7;

    public static final Meal meal = new Meal(100002, LocalDateTime.of(2020, 1, 30, 10, 0), "Завтрак", 500);
    public static final Meal adminMeal = new Meal(100007, LocalDateTime.of(2020, 1, 31, 13, 0), "Обед", 500);

    public static final List<Meal> userMeal = Arrays.asList(
            new Meal(100008, LocalDateTime.of(2020, 1, 31, 20, 0), "Ужин", 410),
            new Meal(100006, LocalDateTime.of(2020, 1, 31, 10, 0), "Завтрак", 1000),
            new Meal(100004, LocalDateTime.of(2020, 1, 30, 20, 0), "Ужин", 500),
            new Meal(100002, LocalDateTime.of(2020, 1, 30, 10, 0), "Завтрак", 500)
    );

    public static final List<Meal> userDeletedMeal = Arrays.asList(
            new Meal(100008, LocalDateTime.of(2020, 1, 31, 20, 0), "Ужин", 410),
            new Meal(100006, LocalDateTime.of(2020, 1, 31, 10, 0), "Завтрак", 1000),
            new Meal(100004, LocalDateTime.of(2020, 1, 30, 20, 0), "Ужин", 500)
    );

    public static final List<Meal> filteredMeals = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2020, 1, 30, 10, 0), "Завтрак", 500),
            new Meal(100004, LocalDateTime.of(2020, 1, 30, 20, 0), "Ужин", 500)
    );

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, 7, 10, 16, 0), "Dinner", 333);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.of(2021, 7, 10, 17, 0));
        updated.setDescription("Dinner");
        updated.setCalories(666);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
    }
}

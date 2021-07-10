package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.getUpdated;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MealTestData.meal);
    }

    @Test
    public void delete() {
        service.delete(100002, USER_ID);
        assertMatch(service.getAll(USER_ID), userDeletedMeal);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filtered = service.getBetweenInclusive(LocalDate.of(2020, 1, 30),
                LocalDate.of(2020, 1, 30), USER_ID);
        assertMatch(filtered, filteredMeals);
    }

    @Test
    public void getAll() {
        List<Meal> allUser = service.getAll(USER_ID);
        assertMatch(allUser, userMeal);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        MealTestData.assertMatch(service.get(updated.getId(), USER_ID), getUpdated());
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(adminMeal, USER_ID));
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(LocalDateTime.of(2020, 1, 30, 10, 0),
                        "Breakfast", 1000), USER_ID));
    }
}
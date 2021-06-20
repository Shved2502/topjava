package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class MealsDaoImpl implements MealsDao {
    private List<MealTo> listMeals = MealsUtil.getWithExcess(MealsUtil.MEALS);

    @Override
    public synchronized void addMeal(Meal meal) {
        listMeals.add(new MealTo(meal, MealsUtil.getExcess(listMeals, meal.getDate())));
    }

    @Override
    public synchronized void deleteMeal(long id) {
        listMeals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public synchronized void updateMeal(Meal meal) {
        listMeals.add(new MealTo(meal, MealsUtil.getExcess(listMeals, meal.getDate())));
    }

    @Override
    public synchronized List<MealTo> getAllMeals() {
        return listMeals;
    }

    @Override
    public synchronized MealTo getById(long id) {
        for (MealTo meal : listMeals) {
            if (meal.getId() == id) return listMeals.get(listMeals.indexOf(meal));
        }
        return null;
    }
}

package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class MealsDao {
    private List<MealTo> list = MealsUtil.hardcoredList();

    public synchronized void addMeal(Meal meal){

    }
}

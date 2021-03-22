package ru.javawebinar.topjava.web.meal;

import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.service.MealService;

@RestController
public class MealRestController extends AbstractMealController {
    public MealRestController(MealService service) {
        super(service);
    }
}

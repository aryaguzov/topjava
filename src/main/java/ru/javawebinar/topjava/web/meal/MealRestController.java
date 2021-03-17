package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.service.MealService;

@RestController
public class MealRestController extends AbstractMealRestController {

    public MealRestController(MealService service) {
        super(service);
    }
}

package dev.profitsoft.backend.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/math/mathtask")
public class MathTasksController {
    @GetMapping()
    public List<String> getMathTasks(@RequestParam int count) {
        List<String> mathTasks = new ArrayList<>(count);

        Random rand = new Random();

        String[] operator = {"+", "-", "/", "*"};

        for (int i = 0; i < count; i++) {
            mathTasks.add(rand.nextInt(100) + operator[rand.nextInt(operator.length)] + rand.nextInt(100));
        }

        return mathTasks;
    }
}
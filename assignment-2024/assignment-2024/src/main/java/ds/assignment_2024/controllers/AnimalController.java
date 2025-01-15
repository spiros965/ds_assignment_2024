package ds.assignment_2024.controllers;

import ds.assignment_2024.entities.Animal;
import ds.assignment_2024.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("animal") 
public class AnimalController {
    
    AnimalService animalService;

    @GetMapping("")
    public String showAnimals(Model model){
        model.addAttribute("animals", animalService.getAnimals());
        return "animal/animals";
    }

    @GetMapping("/{id}")
    public String showAnimal(@PathVariable Integer id, Model model){
        model.addAttribute("animals", animalService.getAnimal(id));
        return "animal/animals";
    }

    @GetMapping("/new")
    public String addAnimal(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "animal/animals";
    }

    @PostMapping("/new")
    public String saveAnimal(@ModelAttribute("animal") Animal animal, Model model) {
        animalService.saveAnimal(animal);
        model.addAttribute("animal", animalService.getAnimals());
        return "animal/animals";
    }

}

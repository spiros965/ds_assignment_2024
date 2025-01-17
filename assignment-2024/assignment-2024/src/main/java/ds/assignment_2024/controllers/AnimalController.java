package ds.assignment_2024.controllers;

import ds.assignment_2024.entities.Animal;
import ds.assignment_2024.service.AnimalService;
import ds.assignment_2024.service.AdoptionRequestService;
import ds.assignment_2024.entities.AdoptionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @GetMapping("")
    public String showAnimals(Model model) {
        model.addAttribute("animals", animalService.getAnimals());
        return "animal/animals";
    }

    @GetMapping("/{id}")
    public String showAnimal(@PathVariable Integer id, Model model) {
        Animal animal = animalService.getAnimal(id);
        model.addAttribute("animal", animal);
        return "animal/animal-details"; // Separate template for details
    }

    @GetMapping("/new")
    public String showNewAnimalForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animal/animal";
    }

    @PostMapping("/new")
    public String saveAnimal(@ModelAttribute("animal") Animal animal) {
        animalService.saveAnimal(animal);
        return "redirect:/animal";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Animal animal = animalService.getAnimal(id);
        model.addAttribute("animal", animal);
        return "animal/animal";
    }

    @PostMapping("/edit/{id}")
    public String updateAnimal(@PathVariable Integer id, @ModelAttribute Animal animal) {
        animal.setId(id);
        animalService.saveAnimal(animal);
        return "redirect:/animal";
    }

    @GetMapping("/{id}/adopt")
    public String showAdoptionForm(@PathVariable Integer id, Model model) {
        Animal animal = animalService.getAnimal(id);
        model.addAttribute("animal", animal);
        return "animal/adoption-request";
    }

    @PostMapping("/{id}/adopt")
    public String submitAdoptionRequest(@PathVariable Integer id, 
                                      @RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String phone,
                                      @RequestParam String message) {
        Animal animal = animalService.getAnimal(id);
        
        AdoptionRequest request = new AdoptionRequest();
        request.setAnimal(animal);
        request.setName(name);
        request.setEmail(email);
        request.setPhone(phone);
        request.setMessage(message);
        request.setRequestDate(LocalDateTime.now());
        request.setStatus("PENDING");
        
        adoptionRequestService.saveRequest(request);
        
        return "redirect:/animal/" + id;
    }
}

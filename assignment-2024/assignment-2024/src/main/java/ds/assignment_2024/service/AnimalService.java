package ds.assignment_2024.service;

import ds.assignment_2024.entities.Animal;
import ds.assignment_2024.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AnimalService {
    
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    @Transactional
    public Animal getAnimal(Integer id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Animal not found with id: " + id));
    }

    @Transactional
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}

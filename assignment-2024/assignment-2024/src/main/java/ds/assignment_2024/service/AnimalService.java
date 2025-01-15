package ds.assignment_2024.service;

import ds.assignment_2024.entities.Animal;
import ds.assignment_2024.repositories.AnimalRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AnimalService {
    
    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    @Transactional
    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Transactional
    public Animal getAnimal(Integer animalId) {
        return animalRepository.findById(animalId).get();
    }
}

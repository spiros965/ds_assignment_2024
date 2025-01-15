package ds.assignment_2024.dao;

import ds.assignment_2024.entities.Animal;
import java.util.List;

public interface AnimalDAO {
    public List<Animal> getAnimals();
    public Animal getAnimal(Integer animal_id);
    public void saveAnimal(Animal animal);
    public void deleteAnimal(Integer animal_id);
}

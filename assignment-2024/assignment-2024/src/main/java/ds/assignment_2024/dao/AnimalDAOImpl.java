package ds.assignment_2024.dao;

import ds.assignment_2024.entities.Animal;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class AnimalDAOImpl implements AnimalDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Animal> getAnimals() {
        TypedQuery<Animal> query = entityManager.createQuery("from Animal", Animal.class);
        return query.getResultList();
    }

    @Override
    public Animal getAnimal(Integer animal_id) {
        return entityManager.find(Animal.class, animal_id);
    }

    @Override
    @Transactional
    public void saveAnimal(Animal animal) {
        System.out.println("animal "+ animal.getId());
        if (animal.getId() == null) {
            entityManager.persist(animal);
        } else {
            entityManager.merge(animal);
        }
    }

    @Override
    @Transactional
    public void deleteAnimal(Integer animal_id) {
        System.out.println("Deleting animal with id: " + animal_id);
        entityManager.remove(entityManager.find(Animal.class, animal_id));
    }
    
}

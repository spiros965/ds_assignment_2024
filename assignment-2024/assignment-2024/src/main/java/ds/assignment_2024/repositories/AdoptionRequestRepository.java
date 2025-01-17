package ds.assignment_2024.repositories;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByUser(User user);
}
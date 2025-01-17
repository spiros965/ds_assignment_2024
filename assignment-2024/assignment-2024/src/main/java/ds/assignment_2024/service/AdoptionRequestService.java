package ds.assignment_2024.service;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.entities.User;
import ds.assignment_2024.repositories.AdoptionRequestRepository;
import ds.assignment_2024.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final UserRepository userRepository;

    // Constructor-based dependency injection
    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository, UserRepository userRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.userRepository = userRepository;
    }

    // Get all adoption requests
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestRepository.findAll();
    }

    // Submit a new adoption request
    public AdoptionRequest submitRequest(AdoptionRequest request) {
        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUser().getId()));
        request.setUser(user);
        return adoptionRequestRepository.save(request);
    }

    // Update the status of an adoption request
    public AdoptionRequest updateRequestStatus(Long id, boolean status) {
        AdoptionRequest request = adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption request not found with id: " + id));
        request.setStatus(status);
        return adoptionRequestRepository.save(request);
    }

    // Get adoption requests by a specific user
    public List<AdoptionRequest> getRequestsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return adoptionRequestRepository.findByUser(user);
    }

    // Delete an adoption request
    public void deleteRequest(Long id) {
        if (!adoptionRequestRepository.existsById(id)) {
            throw new RuntimeException("Adoption request not found with id: " + id);
        }
        adoptionRequestRepository.deleteById(id);
    }
}
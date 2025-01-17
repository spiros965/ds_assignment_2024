package ds.assignment_2024.service;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.repositories.AdoptionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AdoptionRequestService {
    private final AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    @Transactional
    public AdoptionRequest saveRequest(AdoptionRequest request) {
        return adoptionRequestRepository.save(request);
    }

    @Transactional
    public AdoptionRequest updateRequestStatus(Integer id, String status) {
        AdoptionRequest request = adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption request not found with id: " + id));
        request.setStatus(status);
        return adoptionRequestRepository.save(request);
    }

    @Transactional
    public void deleteRequest(Integer id) {
        if (!adoptionRequestRepository.existsById(id)) {
            throw new RuntimeException("Adoption request not found with id: " + id);
        }
        adoptionRequestRepository.deleteById(id);
    }

    @Transactional
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestRepository.findAll();
    }

    @Transactional
    public AdoptionRequest getRequest(Integer id) {
        return adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption request not found with id: " + id));
    }
}
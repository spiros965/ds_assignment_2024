package ds.assignment_2024.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;  // Add this import
import org.springframework.stereotype.Service;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.entities.User;
import ds.assignment_2024.repositories.AdoptionRequestRepository;
import ds.assignment_2024.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AdoptionRequestService {
    private final AdoptionRequestRepository adoptionRequestRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository, 
                                UserRepository userRepository,
                                EmailService emailService) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void saveRequest(AdoptionRequest request, Integer userId) {
        if (userId != null) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
            request.setUser(user);
        }
        adoptionRequestRepository.save(request);
    }

    @Transactional
    public AdoptionRequest updateRequestStatus(Integer id, String status) {
        AdoptionRequest request = adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption request not found with id: " + id));
        request.setStatus(status);
        AdoptionRequest savedRequest = adoptionRequestRepository.save(request);
        
        // Send email based on status
        if ("ACCEPTED".equals(status)) {
            emailService.sendAdoptionApprovalEmail(savedRequest);
        } else if ("DENIED".equals(status)) {
            emailService.sendAdoptionRejectionEmail(savedRequest);
        }
        
        return savedRequest;
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

    @Transactional
    public List<AdoptionRequest> getRequestsByUserId(Integer userId) {
        return adoptionRequestRepository.findByUserId(userId);
    }

    

    
    public List<AdoptionRequest> getRequestsByStatus(String status) {
        return adoptionRequestRepository.findByStatus(status);
    }

    
}
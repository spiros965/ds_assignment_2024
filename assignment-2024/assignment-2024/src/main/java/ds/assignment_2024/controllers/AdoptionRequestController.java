package ds.assignment_2024.controllers;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.service.AdoptionRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    // Constructor-based injection
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    // Get all adoption requests (Admin-only access)
    @GetMapping
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestService.getAllRequests();
    }

    // Submit a new adoption request (User access)
    @PostMapping
    public ResponseEntity<AdoptionRequest> submitRequest(@RequestBody AdoptionRequest request) {
        AdoptionRequest submittedRequest = adoptionRequestService.submitRequest(request);
        return ResponseEntity.ok(submittedRequest);
    }

    // Update the status of an adoption request (Admin-only access)
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionRequest> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam boolean status) {
        AdoptionRequest updatedRequest = adoptionRequestService.updateRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }

    // Get requests by a specific user (Admin-only access)
    @GetMapping("/user/{userId}")
    public List<AdoptionRequest> getRequestsByUser(@PathVariable Long userId) {
        return adoptionRequestService.getRequestsByUser(userId);
    }

    // Delete an adoption request (Admin-only access)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        adoptionRequestService.deleteRequest(id);
        return ResponseEntity.ok("Adoption request deleted successfully!");
    }
}

package ds.assignment_2024.controllers;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.service.AdoptionRequestService;
import ds.assignment_2024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Controller
@RequestMapping("/adoptions")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    @Autowired
    private UserService userService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllRequests(Model model) {
        model.addAttribute("requests", adoptionRequestService.getAllRequests());
        return "adoption/requests";
    }

    @PostMapping
    public String submitRequest(@ModelAttribute AdoptionRequest request) {
        Integer userId = userService.getCurrentUserId();
        adoptionRequestService.saveRequest(request, userId);
        return "redirect:/animal/" + request.getAnimal().getId();
    }

    @PostMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRequestStatus(@PathVariable Integer id, @RequestParam String status) {
        adoptionRequestService.updateRequestStatus(id, status);
        return "redirect:/adoptions";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getRequest(@PathVariable Integer id, Model model) {
        model.addAttribute("request", adoptionRequestService.getRequest(id));
        return "adoption/request-details";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRequest(@PathVariable Integer id) {
        adoptionRequestService.deleteRequest(id);
        return "redirect:/adoptions";
    }

    @GetMapping("/my-requests")
    public String getMyRequests(Model model) {
        Integer userId = userService.getCurrentUserId();
        if (userService.isAdmin(userId)) {
            List<AdoptionRequest> pendingRequests = adoptionRequestService.getRequestsByStatus("PENDING");
            List<AdoptionRequest> acceptedRequests = adoptionRequestService.getRequestsByStatus("ACCEPTED");
            List<AdoptionRequest> deniedRequests = adoptionRequestService.getRequestsByStatus("DENIED");
            model.addAttribute("pendingRequests", pendingRequests);
            model.addAttribute("acceptedRequests", acceptedRequests);
            model.addAttribute("deniedRequests", deniedRequests);
            return "adoptions/admin-requests";
        } else {
            List<AdoptionRequest> requests = adoptionRequestService.getRequestsByUserId(userId);
            model.addAttribute("requests", requests);
            return "adoptions/my-adoptions";
        }
    }

    @GetMapping("/admin-requests")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllRequestsForAdmin(Model model) {
        List<AdoptionRequest> pendingRequests = adoptionRequestService.getRequestsByStatus("PENDING");
        List<AdoptionRequest> acceptedRequests = adoptionRequestService.getRequestsByStatus("ACCEPTED");
        List<AdoptionRequest> deniedRequests = adoptionRequestService.getRequestsByStatus("DENIED");
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("acceptedRequests", acceptedRequests);
        model.addAttribute("deniedRequests", deniedRequests);
        return "adoptions/admin-requests";
    }
    
    @PostMapping("/{id}/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public String acceptRequest(@PathVariable Integer id) {
        adoptionRequestService.updateRequestStatus(id, "ACCEPTED");
        return "redirect:/adoptions/admin-requests";
    }
    
    @PostMapping("/{id}/deny")
    @PreAuthorize("hasRole('ADMIN')")
    public String denyRequest(@PathVariable Integer id) {
        adoptionRequestService.updateRequestStatus(id, "DENIED");
        return "redirect:/adoptions/admin-requests";
    }
}

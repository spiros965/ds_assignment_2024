package ds.assignment_2024.controllers;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.service.AdoptionRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/adoptions")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

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
        adoptionRequestService.saveRequest(request);
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
}

package ds.assignment_2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ds.assignment_2024.entities.AdoptionRequest;
import ds.assignment_2024.entities.User;
import ds.assignment_2024.service.AdoptionRequestService;
import ds.assignment_2024.service.EmailService;
import ds.assignment_2024.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users-list";
    }

    @GetMapping("/users/{id}")
    public String viewUserDetails(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        List<AdoptionRequest> adoptionRequests = adoptionRequestService.getRequestsByUserId(id);
        
        model.addAttribute("user", user);
        model.addAttribute("adoptionRequests", adoptionRequests);
        
        return "admin/user-details";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id) {
        AdoptionRequest request = adoptionRequestService.getRequest(id);
        adoptionRequestService.updateRequestStatus(id, "APPROVED");
        emailService.sendAdoptionApprovalEmail(request);
        return "redirect:/admin/users/" + request.getUser().getId();
    }

    @PostMapping("/requests/{id}/reject")
    public String rejectRequest(@PathVariable Integer id) {
        AdoptionRequest request = adoptionRequestService.getRequest(id);
        adoptionRequestService.updateRequestStatus(id, "REJECTED");
        emailService.sendAdoptionRejectionEmail(request);
        return "redirect:/admin/users/" + request.getUser().getId();
    }
}

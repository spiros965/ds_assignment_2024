package ds.assignment_2024.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who made the request

    @Column(nullable = false)
    private String animalName; // The animal's name or ID being requested

    @Column(nullable = false)
    private Boolean status; // PENDING, APPROVED, DENIED

    // Constructors
    public AdoptionRequest() {}

    public AdoptionRequest(User user, String animalName, Boolean status) {
        this.user = user;
        this.animalName = animalName;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
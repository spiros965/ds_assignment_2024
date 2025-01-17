package ds.assignment_2024.entities;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
        super();
        this.setRole("ADMIN"); // Automatically set the role to ADMIN
    }

    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }
}
package ds.assignment_2024.entities;


import jakarta.persistence.*;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer age;

    @Column
    private String description;

    @Column 
    private Boolean isAdopted;

    
    public Animal() {
    }

    public Animal(String name, String type, Integer age, String description, Boolean isAdopted) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.description = description;
        this.isAdopted = isAdopted;
    }

    public Animal(Integer id, String name, String type, Integer age, String description, Boolean isAdopted) {
        this.Id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.description = description;
        this.isAdopted = isAdopted;
    }

    // Getters and Setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(Boolean isAdopted) {
        this.isAdopted = isAdopted;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "Id=" + Id +
                ", Name='" + name + '\'' +
                ", Type='" + type + '\'' +
                ", Age='" + age + '\'' +
                ", Description='" + description + '\'' +
                ", Available='" + isAdopted + '\'' +
                '}';
    }
}

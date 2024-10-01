package org.sviatdev.lecturesharing.models;

import jakarta.persistence.*;

@Entity
@Table(name = "LS_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(nullable = false, length = 16)
    private String name;

    @Column
    private String surname;

    @Column
    private int age;

    @Enumerated(EnumType.STRING)
    private University university;

    @Column
    private String role;

    public User() {
    }

    public User(Long id, String username, String password, String name, String lastname, int age, University university, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = lastname;
        this.age = age;
        this.university = university;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String lastname) {
        this.surname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

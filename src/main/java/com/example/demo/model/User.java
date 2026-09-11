package com.example.demo.model;

import jakarta.persistence.*;

/**
 * Entity class representing a User in the database.
 *
 * @Entity  — Marks this class as a JPA entity (mapped to a DB table)
 * @Table   — Specifies the table name
 * @Id     — Primary key
 * @GeneratedValue — Auto-increment strategy
 * @Column  — Maps to a specific column with constraints
 *
 * Node.js/Prisma equivalent:
 *   model User {
 *     id    Int    @id @default(autoincrement())
 *     name  String
 *     email String @unique
 *   }
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Default constructor required by JPA
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

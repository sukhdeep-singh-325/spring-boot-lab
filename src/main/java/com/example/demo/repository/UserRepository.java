package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data access layer for User entity.
 *
 * By extending JpaRepository<User, Long>, Spring Data JPA automatically
 * provides implementations for:
 *   - findAll(), findById(), save(), deleteById(), count(), existsById(), etc.
 *
 * You can also define custom queries by method naming conventions.
 *
 * @Repository marks this as a data-access component (also enables exception translation).
 *
 * Node.js equivalent:
 *   // userRepository.js — Prisma/Sequelize model queries
 *   const getAll = () => db.user.findMany();
 *   const findById = (id) => db.user.findUnique({ where: { id } });
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query: Spring generates SQL automatically from method name
    List<User> findByName(String name);

    // Find by email — returns Optional to handle missing record gracefully
    Optional<User> findByEmail(String email);
}

package net.javaguides.todo_management.repository;

import net.javaguides.todo_management.entity.ToDoManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoManagementRepository extends JpaRepository<ToDoManagement,Long> {
}

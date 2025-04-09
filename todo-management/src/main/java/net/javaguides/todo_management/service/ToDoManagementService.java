package net.javaguides.todo_management.service;

import net.javaguides.todo_management.dto.ToDoManagementDto;

import java.util.List;

public interface ToDoManagementService {
    ToDoManagementDto addToDo(ToDoManagementDto toDoManagementDto);

    ToDoManagementDto getToDo(Long taskId);

    List<ToDoManagementDto> getAllToDoTasks();

    ToDoManagementDto updateToDo(Long taskId,ToDoManagementDto toDoManagementDto);

    void deleteToDo(Long taskId);

    ToDoManagementDto completeToDo(Long taskId);

    ToDoManagementDto inCompleteToDo(Long taskId);


}

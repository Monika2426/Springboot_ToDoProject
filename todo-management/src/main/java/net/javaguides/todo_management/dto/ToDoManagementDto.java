package net.javaguides.todo_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoManagementDto {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
}

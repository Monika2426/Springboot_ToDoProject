package net.javaguides.todo_management.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo_management.dto.ToDoManagementDto;
import net.javaguides.todo_management.service.ToDoManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoManagementController {
    private ToDoManagementService toDoManagementService;

    @PreAuthorize("hasRole('ADMIN')")
    //Build AddToDo REST API
    @PostMapping
    public ResponseEntity<ToDoManagementDto> addToDo(@RequestBody ToDoManagementDto todo){
        ToDoManagementDto newToDo=toDoManagementService.addToDo(todo);
        return new ResponseEntity<>(newToDo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//Build getToDo REST API
    @GetMapping("{id}")
    public ResponseEntity<ToDoManagementDto> getToDo(@PathVariable("id") Long taskId){
        ToDoManagementDto toDoDto=toDoManagementService.getToDo(taskId);
        return new ResponseEntity<>(toDoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    //Build Get AllToDo tasks REST API
@GetMapping
    public ResponseEntity<List<ToDoManagementDto>> getAllToDos(){
        List<ToDoManagementDto> toDoDtoList=toDoManagementService.getAllToDoTasks();
        return ResponseEntity.ok(toDoDtoList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //Build UpdateToDo REST API
    @PutMapping("{id}")
    public ResponseEntity<ToDoManagementDto> updateToDo(@PathVariable("id") Long taskId,@RequestBody ToDoManagementDto toDoManagementDto){
        ToDoManagementDto updateToDoDto=toDoManagementService.updateToDo(taskId,toDoManagementDto);
        return ResponseEntity.ok(updateToDoDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //Build DeleteToDo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable("id") Long taskId){
        toDoManagementService.deleteToDo(taskId);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    //Build CompleteToDo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoManagementDto> completeToDo(@PathVariable("id") Long taskId){
        ToDoManagementDto updatedToDo=toDoManagementService.completeToDo(taskId);
        return ResponseEntity.ok(updatedToDo);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    //Build IncompleteToDo REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<ToDoManagementDto> inCompleteToDo(@PathVariable("id") Long taskId){
        ToDoManagementDto updatedToDo=toDoManagementService.inCompleteToDo(taskId);
        return ResponseEntity.ok(updatedToDo);
    }
}

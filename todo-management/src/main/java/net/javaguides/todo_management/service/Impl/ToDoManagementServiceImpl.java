package net.javaguides.todo_management.service.Impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo_management.dto.ToDoManagementDto;
import net.javaguides.todo_management.entity.ToDoManagement;
import net.javaguides.todo_management.exception.ResourceNotFoundException;
import net.javaguides.todo_management.repository.ToDoManagementRepository;
import net.javaguides.todo_management.service.ToDoManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoManagementServiceImpl implements ToDoManagementService {
    private ToDoManagementRepository toDoManagementRepository;
    private ModelMapper modelMapper;

    @Override
    public ToDoManagementDto addToDo(ToDoManagementDto toDoManagementDto) {
        //convert ToDoDto into ToDoJPA entity
        ToDoManagement todo=modelMapper.map(toDoManagementDto,ToDoManagement.class);
        //ToDoJPA entity
        ToDoManagement savedToDo=toDoManagementRepository.save(todo);
        //convert  ToDoJPA entity into ToDoDto
        ToDoManagementDto savedToDoDto=modelMapper.map(savedToDo,ToDoManagementDto.class);
        return savedToDoDto;
    }

    @Override
    public ToDoManagementDto getToDo(Long taskId) {
        ToDoManagement todoEntity=toDoManagementRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task with given id does not exists"+taskId));
        return modelMapper.map(todoEntity,ToDoManagementDto.class);
    }

    @Override
    public List<ToDoManagementDto> getAllToDoTasks() {
        List<ToDoManagement> toDoManagementList=toDoManagementRepository.findAll();
        return toDoManagementList.stream().map((toDoTask)->modelMapper.map(toDoTask,ToDoManagementDto.class)).collect(Collectors.toList());
    }

    @Override
    public ToDoManagementDto updateToDo(Long taskId, ToDoManagementDto toDoManagementDto) {
        ToDoManagement toDoManagement=toDoManagementRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task was not found with the given id"+taskId));
        toDoManagement.setTitle(toDoManagementDto.getTitle());
        toDoManagement.setDescription(toDoManagementDto.getDescription());
        toDoManagement.setCompleted(toDoManagementDto.isCompleted());
        ToDoManagement updatedToDo=toDoManagementRepository.save(toDoManagement);
        return modelMapper.map(updatedToDo,ToDoManagementDto.class);

    }

    @Override
    public void deleteToDo(Long taskId) {
        ToDoManagement toDoManagement=toDoManagementRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task does not exists with the given id"+taskId));
        toDoManagementRepository.deleteById(taskId);
    }

    @Override
    public ToDoManagementDto completeToDo(Long taskId) {
        ToDoManagement toDoManagement=toDoManagementRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task not found with given id"+taskId));
        toDoManagement.setCompleted(Boolean.TRUE);
        ToDoManagement todo=toDoManagementRepository.save(toDoManagement);
        return modelMapper.map(todo,ToDoManagementDto.class);
    }

    @Override
    public ToDoManagementDto inCompleteToDo(Long taskId) {
        ToDoManagement toDoManagement=toDoManagementRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task not found with given id"+taskId));
        toDoManagement.setCompleted(Boolean.FALSE);
        ToDoManagement todo=toDoManagementRepository.save(toDoManagement);
        return modelMapper.map(todo,ToDoManagementDto.class);
    }
}

package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.repository.TaskRepository;
import kz.bitlab.trello.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    @Override
    public List<Task> getTasksByFolder(Folder folder) {
        if (folder == null) {
            return new ArrayList<>();
        }
        return taskRepository.findTasksByFolder(folder);
    }
    @Override
    public Task addTask(Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task with id passed to method addTask");
        }
        task.setStatus(Task.Status.TODO);
        return taskRepository.save(task);
    }
}

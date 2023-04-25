package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.repository.TaskRepository;
import kz.bitlab.trello.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Override
    public Task getTaskById(Long id) {
        if (id == null) {
            return null;
        }
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getTasksByFolder(Folder folder) {
        if (folder == null) {
            return new ArrayList<>();
        }
        return taskRepository.findTasksByFolderOrderById(folder);
    }

    @Override
    public Map<Long, Integer> getTasksCountByFolderMap() {
        return taskRepository.getTasksCountByFolderMap()
                .stream().collect(Collectors.toMap(
                        objects -> (Long)objects[0],
                        objects -> ((Long)objects[1]).intValue()
                ));
    }

    @Override
    public Task addTask(Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task with id passed to method addTask");
        }
        task.setStatus(Task.Status.TODO);
        return taskRepository.save(task);
    }

    @Override
    public Task editTask(Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task without id passed to method editTask");
        }
        if (task.getTitle() == null || task.getTitle().isEmpty() ||
                task.getDescription() == null || task.getDescription().isEmpty() ||
                task.getStatus() == null) {
            return null;
        }
        return taskRepository.save(task);
    }
}

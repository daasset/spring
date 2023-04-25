package kz.bitlab.trello.service;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    Task getTaskById(Long id);
    List<Task> getTasksByFolder(Folder folder);
    Map<Long, Integer> getTasksCountByFolderMap();
    Task addTask(Task task);
    Task editTask(Task task);
}

package kz.bitlab.trello.service;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getTasksByFolder(Folder folder);

    Task addTask(Task task);
}

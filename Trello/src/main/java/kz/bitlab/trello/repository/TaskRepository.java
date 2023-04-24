package kz.bitlab.trello.repository;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByFolder(Folder folder);
}

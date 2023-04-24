package kz.bitlab.trello.repository;

import kz.bitlab.trello.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findFolderByName(String name);
}

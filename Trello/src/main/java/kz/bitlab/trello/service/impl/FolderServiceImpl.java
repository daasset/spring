package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.repository.FolderRepository;
import kz.bitlab.trello.service.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FolderServiceImpl implements FolderService {
    private FolderRepository folderRepository;

    @Override
    public Folder getFolderById(Long id) {
        if (id == null) {
            return null;
        }
        return folderRepository.findById(id).get();
    }

    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Folder addFolder(Folder folder) {
        if (folder.getId() != null) {
            throw new IllegalArgumentException("Folder with existing Id passed to addFolder method");
        }
        if (folderRepository.findFolderByName(folder.getName()) != null) { // Should be unique
            return null;
        }
        return folderRepository.save(folder);
    }

    @Override
    public Folder editFolder(Folder folder) {
        if (folder.getId() == null) {
            throw new IllegalArgumentException("Folder without id passed to method editFolder");
        }
        return folderRepository.save(folder);
    }
}

package kz.bitlab.trello.service;

import kz.bitlab.trello.entity.Category;
import kz.bitlab.trello.entity.Folder;

import java.util.List;

public interface FolderService {
    Folder getFolderById(Long id);
    List<Folder> getAllFolders();
    Folder addFolder(Folder folder);
    Folder editFolder(Folder folder);
}

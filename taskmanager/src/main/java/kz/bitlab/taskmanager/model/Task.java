package kz.bitlab.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private boolean complete;
}

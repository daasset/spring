package kz.bitlab.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Getter
    @AllArgsConstructor
    public enum Status {
        TODO("TO DO"),
        IN_PROGRESS("IN PROGRESS"),
        DONE("DONE"),
        CANCELLED("CANCELLED");

        private final String displayName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length = 5000, nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @ManyToOne
    private Folder folder;
}
package kz.bitlab.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000, nullable = false)
    private String text;

    @ManyToOne(optional = false)
    private Task task;
}

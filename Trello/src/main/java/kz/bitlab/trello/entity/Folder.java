package kz.bitlab.trello.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;
}

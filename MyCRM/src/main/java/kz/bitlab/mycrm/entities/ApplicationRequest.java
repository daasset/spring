package kz.bitlab.mycrm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "application_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name", length = 256)
    private String userName;
    @Column(name = "comment", length = 4096)
    private String comment;
    @Column(name = "phone", length = 12)
    private String phone;
    @Column(name = "handled")
    private boolean handled;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operator> operators;
}

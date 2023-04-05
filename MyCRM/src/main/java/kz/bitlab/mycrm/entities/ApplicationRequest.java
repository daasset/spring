package kz.bitlab.mycrm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "course_name", length = 256)
    private String courseName;
    @Column(name = "comment", length = 4096)
    private String comment;
    @Column(name = "phone", length = 12)
    private String phone;
    @Column(name = "handled")
    private boolean handled;
}

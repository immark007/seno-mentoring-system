package mark.api.seno.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mark.api.seno.model.Role;
import mark.api.seno.model.student.Student;
import mark.api.seno.model.teacher.Teacher;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Role roles;

    @CreatedDate
    @Column
    private LocalDateTime registration_date;

    @LastModifiedDate
    private LocalDateTime update_date;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    public User(String email, String password, Role roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

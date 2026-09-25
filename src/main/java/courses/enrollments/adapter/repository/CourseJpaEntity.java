package courses.enrollments.adapter.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class CourseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String title;

    @Column(name = "enrollment_limit")
    private int limit;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "course", orphanRemoval = true)
    private Set<EnrollmentJpaEntity> enrollments;

    public void addEnrollment(EnrollmentJpaEntity enrollment) {
        enrollments.add(enrollment);
        enrollment.setCourse(this);
    }
}

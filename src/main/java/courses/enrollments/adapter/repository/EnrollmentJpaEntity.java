package courses.enrollments.adapter.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    private LocalDateTime enrollmentDate;

    @ManyToOne
    private CourseJpaEntity course;
}
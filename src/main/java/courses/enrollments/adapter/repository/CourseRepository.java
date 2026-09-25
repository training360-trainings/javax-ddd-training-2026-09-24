package courses.enrollments.adapter.repository;

import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import courses.enrollments.domain.enrollments.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CourseRepository implements CourseRepositoryPort {

    private final CourseJpaRepository courseJpaRepository;

    @Override
    public Course save(Course course) {
        var entity = convert(course);
        courseJpaRepository.save(entity);
        return convert(entity);
    }

    @Override
    public Optional<Course> findById(long id) {
        return courseJpaRepository.findById(id).map(this::convert);
    }

    @Override
    public boolean isCourseWithCode(CourseCode code) {
        return courseJpaRepository.countByCode(code.value()) > 0;
    }

    private CourseJpaEntity convert(Course course) {
        var entity = new CourseJpaEntity(course.getId(), course.getCode().value(), course.getTitle(), course.getLimit(), new HashSet<>());
        for (Enrollment enrollment : course.getEnrollments()) {
            entity.addEnrollment(new EnrollmentJpaEntity(null, enrollment.employeeId().value(), enrollment.enrollmentDate(), entity));
        }
        return entity;
    }

    private Course convert(CourseJpaEntity course) {
        return new Course(course.getId(), new CourseCode(course.getCode()), course.getTitle(), course.getLimit(),
                course.getEnrollments().stream().map(e -> new Enrollment(new EmployeeId(e.getEmployeeId()), e.getEnrollmentDate()))
                        .collect(Collectors.toSet())
                );
    }

}

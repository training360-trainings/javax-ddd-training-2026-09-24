package courses.enrollments.application.outboundports;

import courses.enrollments.domain.enrollments.Course;

import java.util.Optional;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(long id);
}

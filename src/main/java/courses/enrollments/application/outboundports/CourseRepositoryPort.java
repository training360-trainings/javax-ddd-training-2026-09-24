package courses.enrollments.application.outboundports;

import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;

import java.util.Optional;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(long id);

    boolean isCourseWithCode(CourseCode code);
}

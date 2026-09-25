package courses.enrollments.application.outboundports;

import courses.enrollments.application.inboundports.CourseDto;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;

import java.util.List;
import java.util.Optional;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(long id);

    boolean isCourseWithCode(CourseCode code);

    List<CourseDto> findAll();

    List<Course> findCoursesWithEnrollments(EmployeeId employeeId);
}

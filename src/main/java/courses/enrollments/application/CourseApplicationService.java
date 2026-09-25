package courses.enrollments.application;

import courses.enrollments.application.inboundports.*;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseApplicationService implements CourseApplicationServicePort {

    private final CourseRepositoryPort repository;

    @Override
    public CourseDto announce(AnnounceCommand command) {
        var course = Course.announce(new CourseCode(command.code()), command.title(), command.limit());
        course = repository.save(course);
        return new CourseDto(course.getId(), course.getCode().value(), course.getTitle(), course.getLimit());
    }

    @Override
    public EnrollmentDto enroll(EnrollCommand command) {
        var course = repository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + command.courseId()));
        var enrollment = course.enroll(new EmployeeId(command.employeeId()));
        course = repository.save(course);
        return new EnrollmentDto(course.getId(), enrollment.employeeId().value(), enrollment.enrollmentDate());
    }
}

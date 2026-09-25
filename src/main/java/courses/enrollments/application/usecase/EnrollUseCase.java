package courses.enrollments.application.usecase;

import courses.enrollments.application.inboundports.EnrollCommand;
import courses.enrollments.application.inboundports.EnrollmentDto;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import courses.infra.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EnrollUseCase {

    private final CourseRepositoryPort repository;

    public EnrollmentDto enroll(EnrollCommand command) {
        var course = repository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + command.courseId()));
        var enrollment = course.enroll(new EmployeeId(command.employeeId()));
        course = repository.save(course);
        return new EnrollmentDto(course.getId(), enrollment.employeeId().value(), enrollment.enrollmentDate());
    }
}

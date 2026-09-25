package courses.enrollments.application.usecase;

import courses.employees.EmployeeGateway;
import courses.employees.EmployeeService;
import courses.enrollments.application.inboundports.EnrollCommand;
import courses.enrollments.application.inboundports.EnrollmentDto;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.application.outboundports.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import courses.infra.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EnrollUseCase {

    private final CourseRepositoryPort repository;

    private final EmployeeGatewayPort employeeGateway;

    public EnrollmentDto enroll(EnrollCommand command) {
        if (employeeGateway.findById(command.employeeId()).isEmpty()) {
            throw new IllegalArgumentException("Employee does not exist " + command.employeeId());
        }
        var course = repository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + command.courseId()));
        var enrollment = course.enroll(new EmployeeId(command.employeeId()));
        course = repository.save(course);
        return new EnrollmentDto(course.getId(), enrollment.employeeId().value(), enrollment.enrollmentDate());
    }
}

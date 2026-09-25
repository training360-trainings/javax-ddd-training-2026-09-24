package courses.enrollments.application.usecase;

import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import courses.infra.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CancelEnrollmentUseCase {

    private final CourseRepositoryPort repository;

    @Transactional
    public void cancel(EmployeeId employeeId) {
        var courses = repository.findCoursesWithEnrollments(employeeId);
        for (var course: courses) {
            course.cancel(employeeId);
            repository.save(course);
        }
    }
}

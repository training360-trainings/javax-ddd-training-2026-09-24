package courses.enrollments.domain.enrollments;

import java.time.LocalDateTime;

public record Enrollment(EmployeeId employeeId, LocalDateTime enrollmentDate) {
}

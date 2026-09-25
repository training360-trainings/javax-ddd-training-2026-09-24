package courses.enrollments.application.inboundports;

import java.time.LocalDateTime;

public record EnrollmentDto(long courseId, long employeeId, LocalDateTime enrollmentDate) {
}

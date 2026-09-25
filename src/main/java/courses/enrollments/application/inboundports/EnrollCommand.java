package courses.enrollments.application.inboundports;

public record EnrollCommand(long courseId, long employeeId) {
}

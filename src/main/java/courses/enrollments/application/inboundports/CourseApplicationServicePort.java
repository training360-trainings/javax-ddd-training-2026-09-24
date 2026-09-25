package courses.enrollments.application.inboundports;

public interface CourseApplicationServicePort {

    CourseDto announce(AnnounceCommand command);

    EnrollmentDto enroll(EnrollCommand command);

    void cancelEnrollmentByEmployee(long id);
}

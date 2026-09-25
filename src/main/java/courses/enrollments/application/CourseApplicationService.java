package courses.enrollments.application;

import courses.enrollments.application.inboundports.*;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.application.usecase.AnnounceUseCase;
import courses.enrollments.application.usecase.CancelEnrollmentUseCase;
import courses.enrollments.application.usecase.EnrollUseCase;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseApplicationService implements CourseApplicationServicePort {

    private final AnnounceUseCase announceUseCase;

    private final EnrollUseCase enrollUseCase;

    private final CancelEnrollmentUseCase cancelEnrollmentUseCase;

    @Override
    public CourseDto announce(AnnounceCommand command) {
        return announceUseCase.announce(command);
    }

    @Override
    public EnrollmentDto enroll(EnrollCommand command) {
        return enrollUseCase.enroll(command);
    }

    @Override
    public void cancelEnrollmentByEmployee(long id) {
        cancelEnrollmentUseCase.cancel(new EmployeeId(id));
    }
}

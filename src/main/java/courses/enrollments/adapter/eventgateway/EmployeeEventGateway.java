package courses.enrollments.adapter.eventgateway;

import courses.employees.EmployeeHasLeaved;
import courses.enrollments.application.inboundports.CourseApplicationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventGateway {

    private final CourseApplicationServicePort applicationService;

    @EventListener
    public void handleEvent(EmployeeHasLeaved event) {
        applicationService.cancelEnrollmentByEmployee(event.employeeId());
    }
}

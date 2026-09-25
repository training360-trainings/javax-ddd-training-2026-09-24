package courses.enrollments.application.outboundports;

import courses.employees.EmployeeDto;

import java.util.Optional;

public interface EmployeeGatewayPort {

    Optional<EmployeeDto> findById(long id);
}

package courses.enrollments.adapter.gateway;

import courses.employees.EmployeeDto;
import courses.employees.EmployeeGateway;
import courses.enrollments.application.outboundports.EmployeeGatewayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeGatewayAdapter implements EmployeeGatewayPort {

    private final EmployeeGateway employeeGateway;

    @Override
    public Optional<EmployeeDto> findById(long id) {
        return employeeGateway.findById(id);
    }
}

package courses.employees;

import java.util.Optional;

public interface EmployeeGateway {

    Optional<EmployeeDto> findById(long id);
}

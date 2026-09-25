package courses.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto join(EmployeeDto employee) {
        var entity = new Employee(null, employee.name());
        entity = employeeRepository.save(entity);
        return new EmployeeDto(entity.getId(), entity.getName());
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAllBy(EmployeeDto.class);
    }


}

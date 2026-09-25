package courses.employees.internal;

import courses.employees.EmployeeDto;
import courses.employees.EmployeeGateway;
import courses.employees.EmployeeHasLeaved;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeGateway {

    private final EmployeeRepository employeeRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public EmployeeDto join(EmployeeDto employee) {
        var entity = new Employee(null, employee.name());
        entity = employeeRepository.save(entity);
        return new EmployeeDto(entity.getId(), entity.getName());
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAllBy(EmployeeDto.class);
    }

    @Override
    public Optional<EmployeeDto> findById(long id) {
        return employeeRepository.findDtoById(id, EmployeeDto.class);
    }

    public void leave(long id) {
        applicationEventPublisher.publishEvent(new EmployeeHasLeaved(id));
        employeeRepository.deleteById(id);
    }
}

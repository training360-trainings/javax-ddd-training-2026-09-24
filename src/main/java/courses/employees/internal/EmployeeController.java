package courses.employees.internal;

import courses.employees.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto join(@RequestBody EmployeeDto employee) {
        return employeeService.join(employee);
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @DeleteMapping("{id}")
    public void leave(@PathVariable long id) {
        employeeService.leave(id);
    }
}

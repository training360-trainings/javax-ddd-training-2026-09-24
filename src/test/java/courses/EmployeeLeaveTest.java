package courses;

import courses.employees.EmployeeDto;
import courses.employees.internal.EmployeeService;
import courses.enrollments.application.inboundports.AnnounceCommand;
import courses.enrollments.application.inboundports.CourseApplicationServicePort;
import courses.enrollments.application.inboundports.CourseQueryServicePort;
import courses.enrollments.application.inboundports.EnrollCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = {
        "delete from employee",
        "delete from enrollments",
        "delete from courses"
})
class EmployeeLeaveTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CourseApplicationServicePort courseService;

    @Autowired
    private CourseQueryServicePort queryService;

    @Test
    void leave() {
        var employee = employeeService.join(new EmployeeDto(null, "John Doe"));
        var course = courseService.announce(new AnnounceCommand("JAVA-DDD", "Domain Driven Design", 5));
        courseService.enroll(new EnrollCommand(course.id(), employee.id()));
        employeeService.leave(employee.id());

        assertThat(queryService.findEnrollmentsByCourseId(course.id())).isEmpty();
    }
}

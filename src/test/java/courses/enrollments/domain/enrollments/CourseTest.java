package courses.enrollments.domain.enrollments;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void enroll() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        course.enroll(new EmployeeId(16));
        assertThat(course.getEnrollments()).hasSize(1);
    }

    @Test
    void enrollAgain() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        course.enroll(new EmployeeId(16));
        var found = course.enroll(new EmployeeId(16));
        assertEquals(new EmployeeId(16), found.employeeId());
    }

    @Test
    void full() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        IntStream.range(0, 5)
                .forEach(i -> course.enroll(new EmployeeId(i)));
        assertThrows(IllegalStateException.class, () -> course.enroll(new EmployeeId(16)));
    }

}
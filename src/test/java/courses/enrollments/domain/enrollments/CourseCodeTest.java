package courses.enrollments.domain.enrollments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseCodeTest {

    @Test
    void valid() {
        CourseCode courseCode = new CourseCode("JAVAX-DDD");
        assertEquals("JAVAX-DDD", courseCode.value());
    }

    @Test
    void invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new CourseCode("1-JAVAX-DDD"));
    }
}
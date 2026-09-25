package courses.enrollments.domain.enrollments;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@AllArgsConstructor // Ezt csak a perzisztens réteg hívhatja, a service nem
public class Course {

    private Long id;

    private CourseCode code;

    private String title;

    private int limit;

    private Set<Enrollment> enrollments;

    public static Course announce(CourseCode code, String title, int limit) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(title);
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        return new Course(null, code, title, limit, new HashSet<>());
    }

    public Enrollment enroll(EmployeeId employeeId) {
        Objects.requireNonNull(employeeId);
        var found = enrollments.stream().filter(e -> e.employeeId().equals(employeeId)).findAny();
        if (found.isPresent()) {
            return found.get();
        }
        if (enrollments.size() >= limit) {
            throw new IllegalStateException("Too many enrollments: " + limit);
        }
        var enrollment = new Enrollment(employeeId, LocalDateTime.now());
        enrollments.add(enrollment);
        return enrollment;
    }
}

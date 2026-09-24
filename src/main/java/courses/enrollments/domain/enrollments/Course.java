package courses.enrollments.domain.enrollments;

import lombok.Getter;

import java.util.Set;

@Getter
public class Course {

    private Long id;

    private CourseCode code;

    private String title;

    private int limit;

    private Set<Enrollment> enrollments;
}

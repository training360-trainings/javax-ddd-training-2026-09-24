package courses.enrollments.domain.enrollments;

public record CourseCode(String value) {

    public CourseCode {
        if (value == null || !value.matches("[a-zA-Z][a-zA-Z0-9-]+")) {
            throw new IllegalArgumentException("Invalid course code: " + value);
        }
    }
}

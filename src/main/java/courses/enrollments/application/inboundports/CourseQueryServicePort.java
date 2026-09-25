package courses.enrollments.application.inboundports;

import java.util.List;

public interface CourseQueryServicePort {

    List<CourseDto> findAll();
}

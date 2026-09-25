package courses.enrollments.application.usecase;

import courses.enrollments.application.inboundports.AnnounceCommand;
import courses.enrollments.application.inboundports.CourseDto;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.infra.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AnnounceUseCase {

    private final CourseRepositoryPort repository;

    public CourseDto announce(AnnounceCommand command) {
        if (repository.isCourseWithCode(new CourseCode(command.code()))) {
            throw new IllegalArgumentException("There is course with code " + command.code());
        }
        var course = Course.announce(new CourseCode(command.code()), command.title(), command.limit());
        course = repository.save(course);
        return new CourseDto(course.getId(), course.getCode().value(), course.getTitle(), course.getLimit());
    }
}

package courses.enrollments.application;

import courses.enrollments.application.inboundports.CourseDto;
import courses.enrollments.application.inboundports.CourseQueryServicePort;
import courses.enrollments.application.outboundports.CourseRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryService implements CourseQueryServicePort {

    private final CourseRepositoryPort repository;

    @Override
    public List<CourseDto> findAll() {
        return repository.findAll();
    }
}

package courses.enrollments.adapter.controller;

import courses.enrollments.application.inboundports.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseApplicationServicePort service;

    private final CourseQueryServicePort  queryService;

    @GetMapping
    public List<CourseDto> findAll() {
        return queryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto announce(@RequestBody AnnounceCommand command) {
        return service.announce(command);
    }

    @PutMapping("{courseId}/enrollments")
    public EnrollmentDto enroll(@PathVariable long courseId, @RequestBody EnrollCommand command) {
        if (courseId != command.courseId()) {
            throw new IllegalArgumentException("Course id does not match");
        }
        return service.enroll(command);
    }
}

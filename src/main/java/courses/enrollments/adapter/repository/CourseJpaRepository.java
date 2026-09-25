package courses.enrollments.adapter.repository;

import courses.enrollments.application.inboundports.EnrollmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {
    int countByCode(String code);

//    @Query("select new courses.enrollments.application.inboundports.CourseDto(c.id, c.code, c.title, c.limit) from CourseJpaEntity c")
//    List<CourseDto> findAllDto();

    <T> List<T> findAllBy(Class<T> clazz);

    @Query("""
            select c from CourseJpaEntity c join fetch c.enrollments
                        where c.id in (select e.course.id from EnrollmentJpaEntity e where e.employeeId = :employeeId)
                        """)
    List<CourseJpaEntity> findCoursesWithEnrollments(long employeeId);

    @Query("""
        select new courses.enrollments.application.inboundports.EnrollmentDto(e.course.id, e.employeeId, e.enrollmentDate)
                from EnrollmentJpaEntity e where e.course.id = :courseId
        """)
    List<EnrollmentDto> findEnrollmentsWithCourseId(long courseId);
}

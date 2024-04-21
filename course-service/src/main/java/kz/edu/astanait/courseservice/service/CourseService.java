package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.dto.CourseResponse;
import kz.edu.astanait.courseservice.dto.CreateCourseRequest;
import kz.edu.astanait.courseservice.dto.UpdateCourseRequest;
import kz.edu.astanait.courseservice.model.CourseEntity;

import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 11.02.2024
 */
public interface CourseService {
    CourseEntity create(CreateCourseRequest request);

    List<CourseResponse> list();

    List<CourseResponse> listById(List<Long> ids);

    Boolean addStudents(Set<Long> id, Long courseId);

    CourseResponse getById(Long id);

    Boolean delete(Long courseId);

    CourseEntity update(Long courseId, UpdateCourseRequest request);
}

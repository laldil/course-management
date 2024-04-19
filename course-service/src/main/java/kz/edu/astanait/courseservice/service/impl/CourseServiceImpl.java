package kz.edu.astanait.courseservice.service.impl;

import jakarta.ws.rs.BadRequestException;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.CourseResponse;
import kz.edu.astanait.courseservice.dto.CreateCourseRequest;
import kz.edu.astanait.courseservice.dto.FullUserDto;
import kz.edu.astanait.courseservice.dto.UserDto;
import kz.edu.astanait.courseservice.dto.api.ApiDataResponse;
import kz.edu.astanait.courseservice.dto.api.ApiListResponse;
import kz.edu.astanait.courseservice.mapper.CourseMapper;
import kz.edu.astanait.courseservice.model.CourseEntity;
import kz.edu.astanait.courseservice.repository.CourseRepository;
import kz.edu.astanait.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 10.02.2024
 */

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserClient userClient;

    @Override
    public CourseEntity create(CreateCourseRequest request) {
        CourseEntity course = CourseMapper.INSTANCE.mapToEntity(request);

        course.setCreateDate(new Date());

        return courseRepository.save(course);
    }

    @Override
    public List<CourseResponse> list() {
        List<CourseEntity> courseEntities = courseRepository.findAll();

        return courseEntities.stream().map(courseEntity -> {
            ApiDataResponse<UserDto> instructorResponse = userClient.findUserById(courseEntity.getInstructorId());
            ApiListResponse<UserDto> students = userClient.findUsersByIdIn(courseEntity.getStudentsId().stream().toList());

            CourseResponse response = CourseMapper.INSTANCE.mapToResponseDto(courseEntity);
            response.setInstructor(instructorResponse.getData());
            response.setStudents(students.getList());
            return response;
        }).toList();
    }

    @Override
    public List<CourseResponse> listById(List<Long> ids) {
        List<CourseEntity> courseEntities = courseRepository.findByIdIn(ids);
        if (courseEntities.isEmpty()) throw new BadRequestException("Courses not found");

        return courseEntities.stream().map(courseEntity -> {
            ApiDataResponse<UserDto> instructorResponse = userClient.findUserById(courseEntity.getInstructorId());
            ApiListResponse<UserDto> students = userClient.findUsersByIdIn(courseEntity.getStudentsId().stream().toList());

            CourseResponse response = CourseMapper.INSTANCE.mapToResponseDto(courseEntity);
            response.setInstructor(instructorResponse.getData());
            response.setStudents(students.getList());
            return response;
        }).toList();
    }

    @Override
    public Boolean addStudents(Set<Long> ids, Long courseId) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.getStudentsId().addAll(ids);

        try {
            // todo handle exceptions
            List<UserDto> users = userClient.findUsersByIdIn(ids.stream().toList()).getList();
            users.forEach(user -> {
                if (user.getCourseIds() == null)
                    user.setCourseIds(List.of(courseId));
                else
                    user.getCourseIds().add(courseId);

                userClient.updateById(user.getId(), new FullUserDto(user.getCourseIds()));
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        courseRepository.save(course);
        return true;
    }

    @Override
    public CourseResponse getById(Long id) {
        return listById(List.of(id)).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Boolean delete(Long courseId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
        return true;
    }
}

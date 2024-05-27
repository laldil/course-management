package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.BlogDto;
import kz.edu.astanait.courseservice.dto.BlogRequest;
import kz.edu.astanait.courseservice.mapper.BlogMapper;
import kz.edu.astanait.courseservice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aldi
 * @since 27.05.2024
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    private final UserClient userClient;

    public List<BlogDto> getList() {
        var list = blogRepository.findAll();
        var result = new ArrayList<BlogDto>();

        for (var blog : list) {
            var dto = BlogMapper.INSTANCE.mapToDto(blog);
            try {
                var response = userClient.findUserById(blog.getAuthorId());
                if (!response.getSuccess()) {
                    log.error(response.getMsg());
                    continue;
                }
                dto.setAuthor(response.getData().getName());

                result.add(dto);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return result;
    }

    public BlogDto create(BlogRequest request) {
        var blogEntity = BlogMapper.INSTANCE.mapToEntity(request);

        var savedBlog = blogRepository.save(blogEntity);
        return BlogMapper.INSTANCE.mapToDto(savedBlog);
    }

    public BlogDto update(Long blogId, BlogRequest request) {
        var blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog not found"));

        BlogMapper.INSTANCE.update(request, blogEntity);
        var savedBlog = blogRepository.save(blogEntity);

        return BlogMapper.INSTANCE.mapToDto(savedBlog);
    }

    public Boolean delete(Long id) {
        var blogEntity = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));

        blogRepository.delete(blogEntity);

        return true;
    }
}

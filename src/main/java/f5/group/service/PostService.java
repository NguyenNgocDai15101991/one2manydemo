package f5.group.service;

import f5.group.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void save(Post post);
    void deleteById(Long id);
}

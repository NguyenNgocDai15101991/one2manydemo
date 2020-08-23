package f5.group.service;

import f5.group.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findByPostId(Long post_id);
    Optional<Comment> findComment(Long post_id, Long id);
    void save(Comment comment);
    void deleteById(Long id);
}

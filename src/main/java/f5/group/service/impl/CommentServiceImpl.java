package f5.group.service.impl;

import f5.group.model.Comment;
import f5.group.repository.CommentRepository;
import f5.group.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> findByPostId(Long post_id) {
        return commentRepository.findByPostId(post_id);
    }

    @Override
    public Optional<Comment> findComment(Long post_id, Long id) {
        return commentRepository.findByIdAndPostId(post_id, id);
    }


    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}

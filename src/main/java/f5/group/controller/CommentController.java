package f5.group.controller;

import f5.group.model.Comment;
import f5.group.model.Post;
import f5.group.service.impl.CommentServiceImpl;
import f5.group.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/{post_id}")
    public ResponseEntity<List<Comment>> getAllComment(@PathVariable("post_id") Long postId) {
        Optional<Post> post = postService.findById(postId);
        if (post.isPresent()) {
            List<Comment> comments = post.get().getComment();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post/{post_id}")
    public ResponseEntity<Comment> addComment(@PathVariable("post_id") Long post_id, @RequestBody Comment comment) {
        Optional<Post> post = postService.findById(post_id);
        if (post.isPresent()) {
            comment.setPost(post.get());
            commentService.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/put/{post_id}/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("post_id") Long post_id,
                                                 @PathVariable("id") Long id,
                                                 @RequestBody Comment comment) {
        Optional<Post> post = postService.findById(post_id);
        Optional<Comment> commentCurrent = commentService.findComment(post_id, id);
        if (post.isPresent() && commentCurrent.isPresent()) {
            Comment _comment = commentCurrent.get();
            _comment.setText(comment.getText());
            _comment.setPost(post.get());
            commentService.save(_comment);
            return new ResponseEntity<>(_comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{post_id}/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("post_id") Long post_id,
                                                 @PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.findComment(post_id, id);
        if (comment.isPresent()) {
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
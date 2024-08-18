package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postsRepository;

    @Autowired
    private CommentRepository commentRepository;

   @GetMapping(path = "/{id}")
    public Post show(@PathVariable long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Post> showPostsList() {
        return postsRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        postsRepository.save(post);
        return post;
    }

    @PutMapping(path = "/{id}")
    public Post update(@PathVariable long id, @RequestBody Post data) {
        var post = postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        postsRepository.save(post);
        return post;
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable long id) {
        commentRepository.deleteByPostId(id);
        postsRepository.deleteById(id);
    }
}

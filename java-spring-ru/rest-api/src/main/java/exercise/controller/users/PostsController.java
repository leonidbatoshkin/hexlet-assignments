package exercise.controller.users;

import java.util.List;

import exercise.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit, @PathVariable String id) {
        return posts.stream()
                .filter(p -> id.equals(String.valueOf(p.getUserId())))
                .limit(limit).toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable String id, @RequestBody Post data) {
        Post post = new Post();
        post.setSlug(data.getSlug());
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        post.setUserId(Integer.parseInt(id));
        posts.add(post);

        return post;
    }
}
package com.example.post_service_new.service;

import com.example.post_service_new.client.UserClient;
import com.example.post_service_new.dto.PostDTO;
import com.example.post_service_new.dto.UserDTO;
import com.example.post_service_new.entity.Post;
import com.example.post_service_new.exception.PostNotFoundException;
import com.example.post_service_new.mapper.PostMapper;
import com.example.post_service_new.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserClient userClient;

    public PostDTO create(PostDTO dto){

        try{
            UserDTO user = userClient.getUserById(dto.getUserId());
            log.info("User Found {}: ",user.getName());
        }catch (Exception e){
            throw new RuntimeException("User not Found in user-service-new for ID: "+dto.getUserId());
        }
        Post post = postMapper.toEntity(dto);
        post = postRepository.save(post);
        log.info("Post Created: {}",post.getId());
        return postMapper.toDTO(post);
    }

    public PostDTO getById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("ID: "+id));
        return postMapper.toDTO(post);
    }

    public List<PostDTO> getPostByUserId(Long userId){
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream().map(postMapper :: toDTO).toList();
    }

    public List<PostDTO> getAll(){
        return postRepository.findAll().stream().map(postMapper :: toDTO).toList();
    }

    public Map<Long, List<PostDTO>> groupedPostsByUserId() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.groupingBy(PostDTO::getUserId));
    }


    public Map<Long, Map<String, List<PostDTO>>> nestedGroupByUserAndCategory() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.groupingBy(PostDTO::getUserId,
                        Collectors.groupingBy(post ->{
                            String title = post.getTitle().toLowerCase();
                            if(title.contains("spring")) return "Spring";
                            if(title.contains("java")) return "Java";
                            if(title.contains("python")) return "Python";
                            return "Other";
                        })));
    }
}

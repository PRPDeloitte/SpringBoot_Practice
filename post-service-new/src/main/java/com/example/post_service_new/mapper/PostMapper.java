package com.example.post_service_new.mapper;

import com.example.post_service_new.dto.PostDTO;
import com.example.post_service_new.entity.Post;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDTO(Post post);
    Post toEntity(PostDTO dto);
}

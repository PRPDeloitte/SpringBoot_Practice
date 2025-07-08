package com.example.user_service_new.service;


import com.example.user_service_new.dto.UserDTO;
import com.example.user_service_new.entity.User;
import com.example.user_service_new.exception.UserNotFoundException;
import com.example.user_service_new.mapper.UserMapper;
import com.example.user_service_new.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final UserMapper userMapper;

   public UserDTO createUser(UserDTO dto){
       User user = userMapper.toEntity(dto);
       user = userRepository.save(user);
       log.info("User Created: {} ",user.getId());
       return userMapper.toDTO(user);
   }

   public UserDTO getUserById(Long id){
       User user = userRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("User not found with ID: "+id));
       return userMapper.toDTO(user);
   }

   public List<UserDTO> getAllUser(){
       return userRepository.findAll().stream()
               .map(userMapper :: toDTO)
               .toList();
   }

   public void deleteUser(Long id){
       if(!userRepository.existsById(id)){
           throw new UserNotFoundException("Cannot Delete. User Not found with ID: "+id);
       }
       userRepository.deleteById(id);
       log.info("Deleted User: {} ",id);
   }

}

package service.implement;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userConverter.toEntity(userDTO);
        // Save UserEntity


        return userConverter.toDTO(userEntity);
    }
}

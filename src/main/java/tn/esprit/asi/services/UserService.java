package tn.esprit.asi.services;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.esprit.asi.dtos.CredentialsDto;
import tn.esprit.asi.dtos.SignUpDto;
import tn.esprit.asi.dtos.UserDto;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.exceptions.AppException;
import tn.esprit.asi.mappers.UserMapper;
import tn.esprit.asi.reposetories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    private final UserMapper userMapper;
    
    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
    
    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }
    
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
}

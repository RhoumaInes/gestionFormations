package tn.esprit.asi.services;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.esprit.asi.dtos.CredentialsDto;
import tn.esprit.asi.dtos.SignUpDto;
import tn.esprit.asi.dtos.UserDto;
import tn.esprit.asi.entities.Formateur;
import tn.esprit.asi.entities.Role;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.exceptions.AppException;
import tn.esprit.asi.mappers.UserMapper;
import tn.esprit.asi.reposetories.FormateurRep;
import tn.esprit.asi.reposetories.UserRepository;

@RequiredArgsConstructor
@Service
public class userService {
	@Autowired
	private final UserRepository userRepository ;
	@Autowired
	private final FormateurRep formateurRep;
	@Autowired
	private final UserMapper userMapper;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	public UserDto login(CredentialsDto credentialsDto) {
		System.out.println(credentialsDto.getLogin());
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
	
	public UserDto register(SignUpDto userDto) {
		System.out.println("okZZZE");
        Optional<Formateur> optionalUser = formateurRep.findByLogin(userDto.getLogin());
		
		System.out.println("ok");
        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        System.out.println("ok");
        User user = userMapper.signUpToUser(userDto);
        System.out.println(user);
        if (optionalUser.get().getTYPE_UP()=="o") {
        	//CUP
        	user.setRole(Role.CUP);
        } else if (optionalUser.get().getUP()=="o") {
        	//UP
        	user.setRole(Role.UP);
        } else {
        	//Ens
        	user.setRole(Role.ENS);
        }
        
        System.out.println(user.getPassword());
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

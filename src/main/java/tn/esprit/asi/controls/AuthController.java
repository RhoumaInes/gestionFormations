package tn.esprit.asi.controls;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.esprit.asi.configs.userAuthenticationProvider;
import tn.esprit.asi.dtos.CredentialsDto;
import tn.esprit.asi.dtos.SignUpDto;
import tn.esprit.asi.dtos.UserDto;
import tn.esprit.asi.services.userService;

@RequiredArgsConstructor
@RestController
public class AuthController {
	private final userService userService;
    private final userAuthenticationProvider userAuthenticationProvider;
	
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
    	System.out.println(user);
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }
}

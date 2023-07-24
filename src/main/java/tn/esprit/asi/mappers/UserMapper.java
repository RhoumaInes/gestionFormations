package tn.esprit.asi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tn.esprit.asi.dtos.SignUpDto;
import tn.esprit.asi.dtos.UserDto;
import tn.esprit.asi.entities.User;

@Mapper(componentModel = "spring")

public interface UserMapper {
	UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}

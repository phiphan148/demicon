package com.demicon.user;

import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    public abstract UserDto toDto(User user);

    @AfterMapping
    public void doAfterDtoMapping(User user, @MappingTarget UserDto userDto) {
        userDto.setName(user.getLogin().getName());
        userDto.setCountry(user.getLocation().getCountry());
    }
}

package com.demicon;

import com.demicon.user.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    public UserMapper underTest = Mappers.getMapper(UserMapper.class);

    @Test
    public void testToDtoMapping() {
        User user = getUser();
        UserDto userDto = getUserDto(user);

        assertThat(underTest.toDto(user)).isEqualTo(userDto);
    }

    private User getUser() {
        Login loginInfo = new Login();
        loginInfo.setName("userNameTest");

        Location locationInfo = new Location();
        locationInfo.setCountry("Germany");

        User user = User.builder().email("userEmailTest@gmail.com").login(loginInfo).location(locationInfo).build();
        return user;
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setCountry(user.getLocation().getCountry());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getLogin().getName());
        return userDto;
    }
}

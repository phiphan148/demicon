package com.demicon;

import com.demicon.user.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRestController underTest;

    @Test
    public void testImportUsers() {
        List<User> result = underTest.importUsers();

        assertThat(result).isInstanceOf(ArrayList.class);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testGetUsers() {
        Location location = new Location();
        location.setCountry("Germany");
        User mockedUser = User.builder().email("test@gmail.com").location(location).build();

        UserDto mockedUserDto = new UserDto();
        mockedUserDto.setEmail(mockedUser.getEmail());
        mockedUserDto.setCountry(mockedUser.getLocation().getCountry());

        List<User> mockedReturnUsers = new ArrayList<>();
        mockedReturnUsers.add(mockedUser);

        when(userService.findAll())
                .thenReturn(mockedReturnUsers);

        when(userMapper.toDto(mockedUser))
                .thenReturn(mockedUserDto);

        Map<String, Object> mockedCountryMap = new HashMap<>();
        mockedCountryMap.put("name", mockedUserDto.getCountry());
        mockedCountryMap.put("users", List.of(mockedUserDto));

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("countries", List.of(mockedCountryMap));

        assertThat(underTest.getUsers()).isEqualTo(expectedResult);
    }
}

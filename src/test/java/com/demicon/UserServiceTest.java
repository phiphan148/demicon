package com.demicon;

import com.demicon.user.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService underTest;

    @Test
    public void findCallsRepositoryWithParams() {
        User user = User.builder().email("test@gmail.com").build();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(underTest.findAll()).thenReturn(List.of(user));

        underTest.findAll();

        verify(userRepository, times(1)).findAll();
    }
}

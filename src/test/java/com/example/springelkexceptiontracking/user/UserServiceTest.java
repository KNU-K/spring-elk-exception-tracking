package com.example.springelkexceptiontracking.user;

import com.example.springelkexceptiontracking.dto.UserDTO;
import com.example.springelkexceptiontracking.entity.User;
import com.example.springelkexceptiontracking.exception.UserNotFoundException;
import com.example.springelkexceptiontracking.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayName("유저 서비스 테스트")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 유저_생성_테스트(){
        UserDTO userDTO = new UserDTO("name","email","password");
        User createdUser  =userService.register(userDTO);

        Assertions.assertEquals(createdUser.getId(), 1L);
        Assertions.assertEquals(createdUser.getName(), userDTO.getName());
        Assertions.assertEquals(createdUser.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(createdUser.getPassword(), userDTO.getPassword());
    }
    @Nested
    class 유저_조회_테스트 {
        UserDTO userDTO;
        @BeforeEach
        void setUp(){
            userDTO = new UserDTO("name","email","password");
            userService.register(userDTO);
        }

        @Test
        void 성공_테스트() {
            User foundUser = userService.findUserById(1L);

            Assertions.assertEquals(foundUser.getId(), 1L);
            Assertions.assertEquals(foundUser.getName(), userDTO.getName());
            Assertions.assertEquals(foundUser.getEmail(), userDTO.getEmail());
            Assertions.assertEquals(foundUser.getPassword(), userDTO.getPassword());
        }
        @Test
        void 실패_테스트() {
            Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.findUserById(2L);
            });
        }
    }
    @AfterEach
    void resetDatabase() {
        // users 테이블의 모든 데이터를 삭제합니다.
        jdbcTemplate.execute("TRUNCATE TABLE users");
        // H2에서 AutoIncrement 컬럼(ID)의 값을 1부터 다시 시작하도록 재설정합니다.
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
    }
}

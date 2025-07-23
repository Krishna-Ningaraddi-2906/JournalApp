package com.springbootProjects.JournalApp.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class UserServiceTests
{
    @Autowired
    UserRepository userRepository;
    @Test
   public void findByName() {
       // assertEquals(4,2+2); -- dummy test
        // here we get null pointer exception because we have not started the application and
        // hence the application context  has not been created bean, so we use @SpringBootTest annotation

        UserEntity user = userRepository.findByUserName("krishna");
        assertEquals("krishna", user.getUserName());
   }

//   @ParameterizedTest  // we are using parameterized test annotation to test multiple dynamic inputs
//   @CsvSource({ "1, 1, 2", "2, 2, 4", "3, 3, 9" })
//   public void test(int a, int b, int expected) {
//        assertEquals(expected, a + b);
//   }
//
//   @ParameterizedTest
//   // we are using ValueSource annotation instead of CsvSource we can use int,float etc instead of strings
//   @ValueSource(strings = { "krishna", "pramod", "KK" })
//
//   public void test2(String userName) {
//       {
//           assertNotNull(userRepository.findByUserName(userName), "failed for " + userName);
//       }
//   }


   // there are few more annotations we can use, these are used on methods
    // beforeEach - runs before each test
    // beforeAll - runs before all tests
    // afterAll - runs after all tests
    // afterEach - runs after each test
}

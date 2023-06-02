package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @DisplayName("Test the registerUser() method for adding a new user")
    @Test
    void registerNewUser() {
        User user = new User("Name", "Pass", "email");
        UserService userService = new UserService();
        assertTrue(userService.registerUser(user));
    }

    @DisplayName("Test the registerUser() method for adding a user that already exists")
    @Test
    void registerExistingUser() {
        User user = new User("Name", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        assertFalse(userService.registerUser(user));
    }

    @DisplayName("Test the registerUser() method for adding a user with the same username")
    @Test
    void registerDuplicateUser() {
        User user = new User("Name", "Pass", "email");
        User user2 = new User("Name", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        assertFalse(userService.registerUser(user2));
    }


    //I personally think this is a bug, adding a null user SHOULD throw an exception
    @DisplayName("Test the registerUser() method for adding a null user")
    @Test
    void registerNullUser() {
        User user = new User(null,null,null);
        UserService userService = new UserService();
        assertDoesNotThrow(() -> userService.registerUser(user));

    }

    @DisplayName("Test the loginUser() method for the user not existing")
    @Test
    void loginNotAUser() {
        User user = new User("name","null","null");
        UserService userService = new UserService();
        userService.registerUser(user);
        assertNull(userService.loginUser("userName", "null"));
    }

    @DisplayName("Test the loginUser() method for the wrong password")
    @Test
    void loginUserWrongPassword() {
        User user = new User("name","null","null");
        UserService userService = new UserService();
        userService.registerUser(user);
        assertNull(userService.loginUser("name", "pass"));
    }

    @DisplayName("Test the loginUser() method")
    @Test
    void loginUserTest() {
        User user = new User("name","null","null");
        UserService userService = new UserService();
        userService.registerUser(user);
        assertEquals(user, userService.loginUser("name", "null"));
    }

    //This is why a null user is a bug and should throw an exception
    @DisplayName("Test the loginUser() method for the user data being null")
    @Test
    void loginNullUser() {
        User user = new User(null,null,null);
        UserService userService = new UserService();
        userService.registerUser(user);
        assertThrows(NullPointerException.class, () ->userService.loginUser(null, null));
    }

    @DisplayName("Test updateUserProfile() method for if the username is taken")
    @Test
    void updateUserProfileTakenName() {
        User user = new User("name","null","null");
        User user2 = new User("Username", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        userService.registerUser(user2);
        assertFalse(userService.updateUserProfile(user, "Username", "null", "null"));
    }

    @DisplayName("Test updateUserProfile() method")
    @Test
    void updateUserProfileTest() {
        User user = new User("name","null","null");
        User user2 = new User("Username", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        userService.registerUser(user2);
        assertTrue(userService.updateUserProfile(user, "NewName", "null", "null"));
    }

    @DisplayName("Test updateUserProfile() method for null user")
    @Test
    void updateUserProfileNullExists() {
        User user = new User(null, null, null);
        User user2 = new User("Username", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        userService.registerUser(user2);
        assertFalse(userService.updateUserProfile(user2, null, "null", "null"),
                "null still counts as the username, and will not allow change to null if null exists as a name");
    }

    @DisplayName("Test updateUserProfile() method for null input")
    @Test
    void updateUserProfileNullName() {
        User user = new User("name", "null", "null");
        User user2 = new User("Username", "Pass", "email");
        UserService userService = new UserService();
        userService.registerUser(user);
        userService.registerUser(user2);
        assertTrue(userService.updateUserProfile(user2, null, "null", "null"),
                "null still counts as the username");
    }
}
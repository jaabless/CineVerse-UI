package com.example.cineverse.tests;

import com.example.cineverse.data.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.cineverse.pages.HomePage;
import org.example.cineverse.pages.SignInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Epic("User Management")
@Feature("User Login")
public class UserLoginTests extends BaseTest {



    @ParameterizedTest
    @MethodSource("com.example.cineverse.data.TestDataProvider#validLoginCredentials")
    @Story("US-A2: User Login")
    @Description("Test user login with valid input combinations")
    public void test_validLoginCredentials(TestDataProvider.AuthLoginData authData) {
        SignInPage signInPage = homePage.clickSignIn();
        signInPage.enterLoginIdentifier(authData.getEmail());
        signInPage.enterPassword(authData.getPassword());
        HomePage welcomePage = signInPage.clickLoginButton();
        Assertions.assertNotNull(welcomePage, "Login should redirect to the welcome page");
        String trendingText = homePage.getTrendingMovieLabelText();
        Assertions.assertTrue(trendingText.contains("Trending Movies"),
                "Welcome page should display trending movies section");
    }

//    @ParameterizedTest
//    @MethodSource("com.example.cineverse.data.TestDataProvider#invalidLoginCredentials")
//    @Story("US-A2: User Login")
//    @Description("Test user login with invalid input combinations")
//    public void test_invalidLoginCredentials(TestDataProvider.AuthLoginData authData) {
//        SignInPage signInPage = homePage.clickSignIn();
//        signInPage.enterLoginIdentifier(authData.getEmail());
//        signInPage.enterPassword(authData.getPassword());
//        signInPage.clickLoginButton();
//        String error = signInPage.getErrorMessage();
//        Assertions.assertFalse(error.isEmpty(), "Error message should be shown for invalid login");
//        Assertions.assertTrue(error.toLowerCase().contains("invalid"),
//                "Expected error message for failed login");
//    }

    @ParameterizedTest
    @MethodSource("com.example.cineverse.data.TestDataProvider#invalidLoginCredentials")
    @Story("US-A2: User Login")
    @Description("Test user login with invalid input combinations")
    public void test_invalidLoginCredentials(TestDataProvider.AuthLoginData authData) {
        SignInPage signInPage = homePage.clickSignIn();
        signInPage.enterLoginIdentifier(authData.getEmail());
        System.out.println(authData.getEmail());
        signInPage.enterPassword(authData.getPassword());
        System.out.println(signInPage.isLoginButtonEnabled());
        String errorMessage = signInPage.getClientErrorMessage();
        System.out.println(errorMessage);
        Assertions.assertFalse(errorMessage.isEmpty(), "Error message should be shown when login button is disabled");
        Assertions.assertTrue(errorMessage.toLowerCase().contains("required") ||
                        errorMessage.toLowerCase().contains("must contain") || errorMessage.toLowerCase().contains("please enter a valid email address") ,
                "Expected form validation error message for invalid input");
    }

    @ParameterizedTest
    @MethodSource("com.example.cineverse.data.TestDataProvider#nonExistingLoginCredentials")
    @Story("US-A2: User Login")
    @Description("Test user login with invalid input combinations")
    public void test_nonExistingLoginCredentials(TestDataProvider.AuthLoginData authData) {
        SignInPage signInPage = homePage.clickSignIn();
        signInPage.enterLoginIdentifier(authData.getEmail());
        System.out.println(authData.getEmail());
        signInPage.enterPassword(authData.getPassword());
        System.out.println(signInPage.isLoginButtonEnabled());
        signInPage.clickLoginButton();
        String error = signInPage.getClientErrorMessage();
        System.out.println(error);
        Assertions.assertFalse(error.isEmpty(), "Error message should be shown for invalid login");
        Assertions.assertFalse(error.toLowerCase().contains("invalid"),
                "Expected error message for failed login");
    }


//    @ParameterizedTest
//    @MethodSource("com.example.cineverse.data.TestDataProvider#validRegisterUsers")
//    @Story("US-A2: User Login")
//    @Description("Test user login with various input combinations")
//    public void test_validLoginCredentials(String identifier, String password, boolean shouldSucceed) {
//        SignInPage signInPage =  homePage.clickSignIn();
//
//        signInPage.enterLoginIdentifier(identifier);
//        signInPage.enterPassword(password);
//        System.out.println(signInPage.getErrorMessage());
//        if (signInPage.isLoginButtonEnabled()) {
//            signInPage.clickLoginButton();
//
//            if (shouldSucceed) {
//                // Verify successful login (check for JWT token or redirect)
//                Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"),
//                        "Login should redirect to dashboard");
//            } else {
//                String errorMessage = signInPage.getErrorMessage();
//                Assertions.assertEquals("Invalid username/email or password", errorMessage,
//                        "Expected error message for failed login");
//            }
//        } else {
//            String errorMessage = signInPage.getErrorMessage();
//            Assertions.assertFalse(errorMessage.isEmpty(),
//                    "Error message should be displayed when login button is disabled");
//            Assertions.assertTrue(errorMessage.contains("required") || errorMessage.contains("must contain"),
//                    "Error message should indicate validation failure");
//        }
//    }
}
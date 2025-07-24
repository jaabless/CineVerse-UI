package com.example.cineverse.tests;



import com.example.cineverse.data.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.cineverse.pages.HomePage;
import org.example.cineverse.pages.SignInPage;
import org.example.cineverse.pages.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Epic("User Management")
@Feature("User Registration")
public class UserRegistrationTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.cineverse.data.TestDataProvider#validRegisterUserCredentials")
    @Story("US-A1: User Registration")
    @DisplayName("Test user registration with various input combinations")
    public void test_validRegisteringCredentials(TestDataProvider.AuthRegisterData authData) {
        SignUpPage signUpPage = homePage.clickSignUp();

        signUpPage.enterFirstName(authData.getFirstName());
        signUpPage.enterLastName(authData.getLastName());
        signUpPage.enterEmail(authData.getEmail());
        signUpPage.clickNextButton();

        signUpPage.enterPassword(authData.getPassword());
        signUpPage.enterConfirmPassword(authData.getPassword());

        SignInPage signInPage = signUpPage.clickCreateAccountButton();

        signInPage.enterLoginIdentifier(authData.getEmail());
        signInPage.enterPassword(authData.getPassword());

        HomePage welcomePage = signInPage.clickLoginButton();
        Assertions.assertNotNull(welcomePage, "Login should redirect to the welcome page");

        String movies = homePage.getTrendingMovieLabelText();
        Assertions.assertTrue(movies.contains("Trending Movies"),
                "Welcome page should display trending movies section");
    }


    private void implicitlyWait(int i) {
        try {
            Thread.sleep(i * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new RuntimeException("Implicit wait interrupted", e);
        }
    }
}
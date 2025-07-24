package org.example.cineverse.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Sign Up']")
    private WebElement signUpLink;

    @FindBy(xpath = "//a[normalize-space()='Sign In']")
    private WebElement signInLink;

    @FindBy(xpath = "//h2[normalize-space()='Trending Movies']")
    private WebElement trendingMovieLabel;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage clickSignUp() {
        signUpLink.click();
        return new SignUpPage(driver);
    }

    public SignInPage clickSignIn() {
        signInLink.click();
        return new SignInPage(driver);
    }
    public String getTrendingMovieLabelText() {
        return trendingMovieLabel.getText();
    }
}
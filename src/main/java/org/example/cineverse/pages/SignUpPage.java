package org.example.cineverse.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SignUpPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // === Elements ===

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//button[normalize-space()='Create account']")
    private WebElement signupButton;

    @FindBy(id = "confirm-password-error")
    private WebElement errorMessage;

    // === Interaction Methods ===

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickNextButton() {
        wait.until(driver -> nextButton.isDisplayed() && nextButton.isEnabled());
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();

        // Optional: Give time for animation
        try {
            Thread.sleep(500); // adjust as needed for fade-in effect
        } catch (InterruptedException ignored) {}
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public SignInPage clickCreateAccountButton() {
        wait.until(driver -> signupButton.isDisplayed() && signupButton.isEnabled());
        wait.until(ExpectedConditions.elementToBeClickable(signupButton));
        signupButton.click();
        return new SignInPage(driver);
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (TimeoutException e) {
            return ""; // If not visible in time, return empty string
        }
    }
}

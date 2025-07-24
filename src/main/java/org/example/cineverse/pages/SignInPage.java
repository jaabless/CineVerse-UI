package org.example.cineverse.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // === Elements ===

    @FindBy(id = "mat-input-0")
    private WebElement emailField;

    @FindBy(id = "mat-input-1")
    private WebElement passwordField;

    @FindBy(xpath = "//span[@class='mdc-button__label']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(),'Invalid login credentials.')]")
    private WebElement errorMessage;

    @FindBy(css = "div[class='error-message-container'] div")
    private WebElement errorMessageContainer;

    // === Actions ===

    public void enterLoginIdentifier(String identifier) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(identifier);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new HomePage(driver);
    }

    public boolean isLoginButtonEnabled() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return loginButton.isEnabled();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (TimeoutException e) {
            return ""; // Safe fallback
        }
    }

    public String getClientErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
            return errorMessageContainer.getText();
        } catch (TimeoutException e) {
            return ""; // Safe fallback
        }
    }
}

package main.models.error;

import java.util.HashMap;

public class ErrorTypes {

    private static Integer notFound = 404;
    private static Integer notAuthorized = 401;
    private static Integer requiredField = 1001;
    private static Integer userAlreadyExists = 1011;
    private static Integer incorrectEmail = 1012;
    private static Integer nicknameTaken = 1013;
    private static Integer shortPassword = 1014;
    private static Integer incorrectRegData = 1015;
    private static Integer incorrectPassword = 1016;
    private static Integer incorrectEmailOrPassword = 1021;
    private static Integer incorrectLogoutFailed = 1031;

    private static HashMap<Integer, ErrorMessage> errorsMap;

    static {
        errorsMap = new HashMap<>();
        errorsMap.put(notFound, new ErrorMessage(notFound, "Data not found"));
        errorsMap.put(notAuthorized, new ErrorMessage(notAuthorized, "User not authorized"));
        errorsMap.put(requiredField, new ErrorMessage(requiredField, "This field is required"));
        errorsMap.put(userAlreadyExists, new ErrorMessage(userAlreadyExists, "This user already exists"));
        errorsMap.put(incorrectEmail, new ErrorMessage(incorrectEmail, "Incorrect email address"));
        errorsMap.put(nicknameTaken, new ErrorMessage(nicknameTaken, "This nickname is already taken"));
        errorsMap.put(shortPassword, new ErrorMessage(shortPassword, "The minimum length is 8 characters"));
        errorsMap.put(incorrectRegData, new ErrorMessage(incorrectRegData, "Incorrect registration data"));
        errorsMap.put(incorrectPassword, new ErrorMessage(incorrectPassword, "Incorrect password"));
        errorsMap.put(incorrectEmailOrPassword, new ErrorMessage(incorrectEmailOrPassword, "Incorrect email or password"));
        errorsMap.put(incorrectLogoutFailed, new ErrorMessage(incorrectLogoutFailed, "Logout failed"));
    }

    public static Integer getNotFound() {
        return notFound;
    }

    public static Integer getNotAuthorized() {
        return notAuthorized;
    }

    public static Integer getRequiredField() {
        return requiredField;
    }

    public static Integer getUserAlreadyExists() {
        return userAlreadyExists;
    }

    public static Integer getIncorrectEmail() {
        return incorrectEmail;
    }

    public static Integer getNicknameTaken() {
        return nicknameTaken;
    }

    public static Integer getShortPassword() {
        return shortPassword;
    }

    public static Integer getIncorrectRegData() {
        return incorrectRegData;
    }

    public static Integer getIncorrectPassword() {
        return incorrectPassword;
    }

    public static Integer getIncorrectEmailOrPassword() {
        return incorrectEmailOrPassword;
    }

    public static Integer getIncorrectLogoutFailed() {
        return incorrectLogoutFailed;
    }

    public static HashMap<Integer, ErrorMessage> getErrorsMap() {
        return errorsMap;
    }
}

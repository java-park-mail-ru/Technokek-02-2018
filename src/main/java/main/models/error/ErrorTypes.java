package main.models.error;

import java.util.HashMap;

public class ErrorTypes {

    public static Integer notFound = 404;
    public static Integer notAuthorized = 401;
    public static Integer requiredField = 1001;
    public static Integer userAlreadyExists = 1011;
    public static Integer incorrectEmail = 1012;
    public static Integer nicknameTaken = 1013;
    public static Integer shortPassword = 1014;
    public static Integer incorrectRegData = 1015;
    public static Integer incorrectPassword = 1016;
    public static Integer incorrectEmailOrPassword = 1021;
    public static Integer incorrectLogoutFailed = 1031;

    public static HashMap<Integer, ErrorMessage> errorsMap;

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
}

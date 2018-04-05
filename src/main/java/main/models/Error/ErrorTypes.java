package main.models.Error;

import java.util.HashMap;

public class ErrorTypes {

    public static Integer NOT_FOUND = 404;
    public static Integer NOT_AUTHORIZED = 401;
    public static Integer REQUIRED_FIELD = 1001;
    public static Integer USER_ALREADY_EXISTS = 1011;
    public static Integer INCORRECT_EMAIL = 1012;
    public static Integer NICKNAME_TAKEN = 1013;
    public static Integer SHORT_PASSWORD = 1014;
    public static Integer INCORRECT_REG_DATA = 1015;
    public static Integer INCORRECT_PASSWORD = 1016;
    public static Integer INCORRECT_EMAIL_OR_PASSWORD = 1021;
    public static Integer INCORRECT_LOGOUT_FAILED = 1031;

    public static HashMap<Integer, ErrorMessage> ERRORS_MAP;

    static {
        ERRORS_MAP = new HashMap<>();
        ERRORS_MAP.put(NOT_FOUND, new ErrorMessage(NOT_FOUND, "Data not found"));
        ERRORS_MAP.put(NOT_AUTHORIZED, new ErrorMessage(NOT_AUTHORIZED, "User not authorized"));
        ERRORS_MAP.put(REQUIRED_FIELD, new ErrorMessage(REQUIRED_FIELD, "This field is required"));
        ERRORS_MAP.put(USER_ALREADY_EXISTS, new ErrorMessage(USER_ALREADY_EXISTS, "This user already exists"));
        ERRORS_MAP.put(INCORRECT_EMAIL, new ErrorMessage(INCORRECT_EMAIL, "Incorrect email address"));
        ERRORS_MAP.put(NICKNAME_TAKEN, new ErrorMessage(NICKNAME_TAKEN, "This nickname is already taken"));
        ERRORS_MAP.put(SHORT_PASSWORD, new ErrorMessage(SHORT_PASSWORD, "The minimum length is 8 characters"));
        ERRORS_MAP.put(INCORRECT_REG_DATA, new ErrorMessage(INCORRECT_REG_DATA, "Incorrect registration data"));
        ERRORS_MAP.put(INCORRECT_PASSWORD, new ErrorMessage(INCORRECT_PASSWORD, "Incorrect password"));
        ERRORS_MAP.put(INCORRECT_EMAIL_OR_PASSWORD, new ErrorMessage(INCORRECT_EMAIL_OR_PASSWORD, "Incorrect email or password"));
        ERRORS_MAP.put(INCORRECT_LOGOUT_FAILED, new ErrorMessage(INCORRECT_LOGOUT_FAILED, "Logout failed"));
    }


}

package main.service.avatars;

public class AvatarGeneralException extends RuntimeException {

    public AvatarGeneralException(String message) {
        super(message);
    }

    public AvatarGeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}

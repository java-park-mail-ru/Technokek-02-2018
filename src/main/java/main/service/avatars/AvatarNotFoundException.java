package main.service.avatars;

public class AvatarNotFoundException extends AvatarException {

    public AvatarNotFoundException(String message) {
        super(message);
    }

    public AvatarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package tech.harbinger.springfx;

public class SpringFxException extends Exception {

    public SpringFxException() {
        super();
    }

    public SpringFxException(final String message) {
        super(message);
    }

    public SpringFxException(final Throwable cause) {
        super(cause);
    }

    public SpringFxException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

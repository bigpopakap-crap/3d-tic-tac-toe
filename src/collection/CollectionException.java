
package collection;

/**
 * This exception is used solely in Collection class methods and is a multi-purpose
 * exception. It may be thrown in place of an IndexOutOfBoundsException or
 * when an illegal operation is attempted. Details on the reason for the exception are given
 * by the .getMessage() string
 * @author Kapil Easwar
 */
public class CollectionException extends RuntimeException {
    String msg;

    public CollectionException() {
        msg = "CollectionException thrown";
    }
    public CollectionException(String message) {
        msg = message;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

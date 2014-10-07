
package collection;

/**
 * This exception is thrown only from the Collection class, when an attempt
 * to alter a finalized collection is made
 * @author Kapil Easwar
 */
public class CollectionIsFinalException extends CollectionException {

    @Override
    public String toString() {
        return "An attempt was made to alter a final collection";
    }

}

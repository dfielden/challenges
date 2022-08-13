/**
 * A functional interface for reducing an array down into a single object of the array type.
 */

package danny.work20220629;

@FunctionalInterface
public interface Reducible <T> {

    public T reduce(T[] arr);
}

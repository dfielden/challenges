package danny.work20220324;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class SupplierToConsumer<T> {
    public static <T> void copy(Supplier<T> supplier, Consumer<T> consumer, int n) {
        for (int i = 0; i < n; i++) {
            consumer.accept(supplier.get());
        }
    }
}

package danny.work20220324;

import java.util.List;
import java.util.function.Supplier;

public final class SupplierChallenge {

    private SupplierChallenge() {}

    static <T> void fillList(List<T> list, Supplier<T> s, int n) {
        for (int i = 0; i < n; i++) {
            list.add(s.get());
        }
    }
}

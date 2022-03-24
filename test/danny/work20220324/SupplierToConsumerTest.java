package danny.work20220324;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class SupplierToConsumerTest {

    @Test
    public void noTransfer() {
        IntSupplier intSupplier = new IntSupplier();
        IntConsumer intConsumer = new IntConsumer();
        assertEquals(intSupplier.nums, intConsumer.nums);
        SupplierToConsumer.copy(intSupplier, intConsumer, 0);
        assertEquals(intSupplier.nums, intConsumer.nums);
        assertTrue(intConsumer.nums.isEmpty());
    }

    @Test
    public void transferOneInteger() {
        IntSupplier intSupplier = new IntSupplier();
        IntConsumer intConsumer = new IntConsumer();
        assertEquals(intSupplier.nums, intConsumer.nums);
        SupplierToConsumer.copy(intSupplier, intConsumer, 1);
        assertEquals(intSupplier.nums, intConsumer.nums);
    }

    @Test
    public void transferOneHundredIntegers() {
        IntSupplier intSupplier = new IntSupplier();
        IntConsumer intConsumer = new IntConsumer();
        assertEquals(intSupplier.nums, intConsumer.nums);
        SupplierToConsumer.copy(intSupplier, intConsumer, 100);
        assertEquals(intSupplier.nums, intConsumer.nums);
    }


    static final class IntSupplier implements Supplier<Integer> {
        Random random = new Random();
        private final List<Integer> nums = new ArrayList<>();

        @Override
        public Integer get() {
            int num = random.nextInt(100);
            nums.add(num);
            return num;
        }
    }

    static final class IntConsumer implements Consumer<Integer> {
        private final List<Integer> nums = new ArrayList<>();

        @Override
        public void accept(Integer integer) {
            nums.add(integer);
        }
    }

}
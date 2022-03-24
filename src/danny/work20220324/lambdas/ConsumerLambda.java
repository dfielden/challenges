package danny.work20220324;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Lambdas {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("goodbye");

        // anonymous class
        for (String word : words) {
            AnonymousPrint.accept(word);
        }

        // regular class
        WordPrinter printer = new WordPrinter();
        for (String word : words) {
            printer.accept(word);
        }

        //lamdba
        words.forEach(print);

    }

    // lambda
    static Consumer<String> print = word -> System.out.println(word);

    // anonymous class
    static Consumer<String> AnonymousPrint =  new Consumer<String>() {
        @Override
        public void accept(String word) {
            System.out.println(word);
        }
    };

    // regular class
    static class WordPrinter implements Consumer<String> {
        @Override
        public void accept(String word) {
            System.out.println(word);
        }
    }
}

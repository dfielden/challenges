package danny.scratch;

public interface AA {
    public int num = 2;

    default String getString() {
        return "hi";
    };
}

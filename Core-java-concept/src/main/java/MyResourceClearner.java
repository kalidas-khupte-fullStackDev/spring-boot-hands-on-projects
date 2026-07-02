import java.lang.ref.Cleaner;
import java.util.Objects;

public class MyResourceClearner implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;

    public MyResourceClearner() {
        // Register the cleanup action
        this.cleanable = cleaner.register(this, () -> {
            System.out.println("Cleaning up resource...");
        });
    }

    @Override
    public void close() {
        cleanable.clean(); // Manual cleanup
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyResourceClearner that)) return false;
        return Objects.equals(cleanable, that.cleanable);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cleanable);
    }
}
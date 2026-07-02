import java.util.Objects;

public class Box<T> {

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Box{" +
                "content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Box<?> box)) return false;
        return Objects.equals(content, box.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content);
    }
}

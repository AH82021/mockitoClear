package basics;

import java.io.IOException;

public interface GateWay<T> {
    T getResponse() throws IOException, InterruptedException;
}

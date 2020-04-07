import java.io.IOException;
import java.io.PrintWriter;

@SaveTo(path = "E:\\My doc\\IT\\JAVA\\PRO\\Lesson 2\\Text2")
public class TextWr {
    @Saver
    public void save(String filePath, String textFromContainer) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("Null path to the file");
        }
        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            printWriter.write(textFromContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

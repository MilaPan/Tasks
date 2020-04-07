import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {

        TextContainer textContainer = new TextContainer("text container");

        try {
            Class<?> cls = TextWr.class;
            String path = cls.getAnnotation(SaveTo.class).path();
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    Annotation[] annotations = method.getAnnotations();
                    for (Annotation b : annotations) {
                        if (b instanceof Saver) {
                            method.invoke(new TextWr(), path, textContainer.getSomeString());
                        }
                    }
                }
            }
        } catch(IllegalAccessException e){
            e.printStackTrace();
        } catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
}

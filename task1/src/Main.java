import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {


	public static void main(String[] args) throws IOException {

		Test test = new Test();
		try {
			Class<?> cls = test.getClass();
			Method[] methods = cls.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(MyAnnotation.class)) {
					Annotation[] annotations = method.getAnnotations();
					for (Annotation a : annotations) {
						if (a instanceof MyAnnotation) {
							MyAnnotation annotation = (MyAnnotation) a;
							method.invoke(cls.newInstance(), annotation.a(), annotation.b());
						}
					}
				}
			}
		} catch(IllegalAccessException e){
			e.printStackTrace();
		} catch(InstantiationException e){
			e.printStackTrace();
		} catch(InvocationTargetException e){
			e.printStackTrace();
		}
	}
}
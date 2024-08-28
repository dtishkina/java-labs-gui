package lab2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {
    public static void main(String[] args) {
        try {
            MyClass call = new MyClass();
            Method[] methods = call.getClass().getDeclaredMethods();

            for (Method method : methods) {
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                String stringModifier = Modifier.toString(method.getModifiers());

                if (annotation != null & (stringModifier.equals("private") | stringModifier.equals("protected"))) {
                    method.setAccessible(true);
                    int repeat = annotation.repeat();

                    System.out.println(method.getName() + " with modifier: " + stringModifier);

                    for (int j = 0; j < repeat; ++j) {
                        Class<?>[] type = method.getParameterTypes();
                        if (type.length == 0){
                            System.out.println("\t" + method.invoke(call));
                        } else if (type[0] == String.class) {
                                String arg = Integer.toString(j + 1);
                                System.out.println("\t" + method.invoke(call, arg));
                        } else if (type[0] == int.class || type[0] == double.class) {
                            Integer r = j + 1;
                            System.out.println("\t" + method.invoke(call, r));
                        } else {
                            System.out.println("\t" + method.invoke(call,
                                    Class.forName(type[0].getName()).getConstructor().newInstance()));
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

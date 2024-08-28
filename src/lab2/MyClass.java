package lab2;

import java.lang.reflect.Method;

public class MyClass {
    public MyClass(){}
    public Method getMethodByOrder(int order) {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(MethodOrder.class)) {
                MethodOrder annotation = method.getAnnotation(MethodOrder.class);
                if (annotation.order() == order) {
                    return method;
                }
            }
        }
        return null;
    }

    @MethodOrder(order = 1)
    public String firstMethod(int repeatTimes){
        return "This method was called " + repeatTimes + " times";
    }

    @MethodOrder(order = 2)
    @MyAnnotation(repeat = 3)
    public String secondMethod(){
        return "This method was called";
    }

    @MethodOrder(order = 3)
    @MyAnnotation(repeat = 4)
    protected String thirdMethod(int repeatTimes) {
        return "This method was called " + repeatTimes + " times";
    }

    @MethodOrder(order = 4)
    @MyAnnotation(repeat = 12)
    protected String fourthMethod(String repeat){
        return "Method with string args " + repeat + " times";
    }

    @MethodOrder(order = 5)
    private String fifthMethod(int repeatTimes) {
        return "This method was called " + repeatTimes + " times";
    }

    @MethodOrder(order = 6)
    @MyAnnotation(repeat = 2)
    private String sixthMethod(int repeatTimes){
        return "This method was called " + repeatTimes + " times";
    }

    @MethodOrder(order = 7)
    @MyAnnotation
    private String seventhMethod(AnotherArgs args) {
        return  "Method with custom agr type was called";
    }
}

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.*;
import java.util.ArrayList;

// Where can you put annotations?
// ====> Class annotations, interface annotation, methods annotations

// What does @Target and @Retention mean?
// ====> When we declare an @interface annotation, then we need to specify the Target and Retention. Target indicates the contexts in which an annotation type is applicable (FIELD, METHOD, etc)
// Retention ===> Indicates how long annotations with the annotated type are to be retained.  If no Retention annotation is present on an annotation type declaration, the retention policy defaults to RetentionPolicy.CLASS.

//Name 3 annotations that you have seen!
// ====> @Override, @BeforeEach, @AfterEach, @Getter, @Setter, @Retention, @Target, @WebServlet, @Controller

//Why do annotations exist?
// Annotations provide data about a program that is not part of the program itself. They have no direct effect on the operation of the code they annotate.


@FunctionalInterface
//you can have only one method in the interface
interface Abc{
    void show();
}

class A{
    @Deprecated
    public void show(){
        System.out.println("This is class A");
    }
}

class B extends A{

    @Override

    //override a method from class A
    public void show(){
        ArrayList obj = new ArrayList();
        System.out.println("This is class B");
    }
}

//Marker Annotation - > no value inside
//Single value annotation -> one value inside
//Multi value annotation -> multiple values inside

//Meta Annotation (Method Level, Field Level etc)
//Retention -> Until when to use this annotation - in our case until runtime
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone{
    String os() default "Symbian";
    int version() default 1;
}

//if we don't specify default values then we can add these values to the annotation
//if we have default values and we also specify some values to the annotation, then we will override default values
@SmartPhone(os = "Android", version = 6)
//Lombok annotation
@Getter @Setter @AllArgsConstructor
class NokiaASeries{
    String model;
    int size;
}

public class AnnotationDemo {

    public static void main(String[] args) {
        //we can do configuration using annotation or xml, but everybody uses annotations
        //spring and hibernate are good examples of annotation usage
        B obj = new B();
        obj.show();

        NokiaASeries phone = new NokiaASeries("Fire", 5);

        //we use Reflection API to fetch the values of the annotation
        //With Reflection API we can get the features of the object
        Class classObject = phone.getClass();
        Annotation annotation = classObject.getAnnotation(SmartPhone.class);
        SmartPhone smartPhone = (SmartPhone) annotation;

        System.out.println(smartPhone.os());
        System.out.println(smartPhone.version());
    }

}

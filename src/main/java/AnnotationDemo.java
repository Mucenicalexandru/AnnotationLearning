import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.*;
import java.util.ArrayList;

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
        Class classObject = obj.getClass();
        Annotation annotation = classObject.getAnnotation(SmartPhone.class);
        SmartPhone smartPhone = (SmartPhone) annotation;

        System.out.println(smartPhone.os());
        System.out.println(smartPhone.version());
    }

}

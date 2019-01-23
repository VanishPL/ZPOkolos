import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface DefaultStudent {
    String imie() default "UNKNOWN";
    String nazwisko() default "UNKNOWN";
    float[] oceny() default 0;
    int index() default 0;
}

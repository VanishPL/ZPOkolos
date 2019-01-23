import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Float ns[] = new Float[2];
        ns[0] = 1.2f;
        ns[1] = 3.3f;
        List<Student> list = new ArrayList<>();
        Student s = new Student("Paotr", "Nazssa", 122931, ns);
        Student sd = new Student("Paotr", "Nisz", 422931, ns);;

        list.add(s);
        list.add(sd);


        System.out.println( s +"\n\n" + s.equals(sd) + "\n\n" + sd);
        System.out.println("\n\n\n" + list.get(0).compareTo(sd));

    }
}

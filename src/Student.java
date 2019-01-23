import jdk.nashorn.internal.objects.annotations.Constructor;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;


@DefaultStudent
public class Student implements Comparable<Student> {

    @CompareOrder(order = 0)
    String imie;

    @CompareOrder(order = 1)
    String nazwisko;

    int index;
    List<Float> oceny = new ArrayList<>();

    public Student() {
        DefaultStudent defaultStudent = this.getClass().getAnnotation(DefaultStudent.class);

        this.imie = defaultStudent.imie();
        this.nazwisko = defaultStudent.nazwisko();

        Float ocenyTemp[] = new Float[defaultStudent.oceny().length];
        int i = 0;
        for (float o: defaultStudent.oceny()){
            ocenyTemp[i++] = new Float(o);
        }

        this.oceny.addAll(Arrays.asList(ocenyTemp));

        this.index = defaultStudent.index();

    }

    public Student(String imie, String nazwisko, int index, Float[] oceny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.index = index;
        this.oceny.addAll(Arrays.asList(oceny));

    }

    @Override
    public String toString() {
        return  "Imie: " + this.imie
                + "\nNazwisko: " + this.nazwisko
                + "\nIndex: " + this.index
                + "\nOceny: " + this.oceny;
    }

    @Override
    public boolean equals(Object o) {
        Boolean result = true;
        String field;
        Class c = Student.class;
        Field[] m = c.getDeclaredFields();

        for(int i = 0; i<m.length; i++)
            if(m[i].getAnnotation(IgnoreEquals.class) == null){

                try{
                        field = m[i].getName();
                        result = result && m[i].get(this).equals(o.getClass().getDeclaredField(field).get(o));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        return result;
    }


    @Override
    public int compareTo(Student o) {
        Class c = this.getClass();
        Field[] m = c.getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        int r;
        for (Field s:
             m) {
            if(s.getAnnotation(CompareOrder.class) != null) fields.add(s);
        }

        Collections.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                CompareOrder os1 = o1.getAnnotation(CompareOrder.class);
                CompareOrder os2 = o2.getAnnotation(CompareOrder.class);
                return os2.order() - os1.order();
            }
        });

        try{
            Class<Comparable> cls = Comparable.class;
            for (Field f : fields) {
                if (cls.isAssignableFrom(f.getType())) {
                    Comparable cd = (Comparable)f.get(this);
                    r = cd.compareTo((Comparable)f.get(o));
                    if (r != 0) return r;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static Comparator<Student> StudentNameComparator
            = new Comparator<Student>() {

        public int compare(Student fruit1, Student fruit2) {


            //ascending order
            return fruit1.compareTo(fruit2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}

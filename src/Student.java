import jdk.nashorn.internal.objects.annotations.Constructor;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@DefaultStudent
public class Student {

    @IgnoreEquals
    String imie;

    @IgnoreEquals
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


}

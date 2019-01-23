public class Main {

    public static void main(String[] args) {
        Float ns[] = new Float[2];
        ns[0] = 1.2f;
        ns[1] = 3.3f;
        Student s = new Student("Paotr", "Naz", 122931, ns);
        Student sd = new Student("Posas", "Nisz", 422931, ns);;

        System.out.println( s +"\n\n" + s.equals(sd) + "\n\n" + sd);
        System.out.println(s.compareTo(sd));
    }
}

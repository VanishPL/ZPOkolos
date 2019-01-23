public class Main {

    public static void main(String[] args) {
        Float ns[] = new Float[2];
        ns[0] = 1.2f;
        ns[1] = 3.3f;
        Student s = new Student("Piotr", "Naz", 422931, ns);
        Student sd = new Student("Piotr", "Naz", 422931, ns);;

        System.out.println( s +"\n\n" + s.equals(sd) + "\n\n" + sd);
    }
}

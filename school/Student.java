package school;

public abstract class Student extends Person{
    Student(String nam, Classroom classroom){
        super(nam, "out", classroom);
        System.out.println("A New Student has been created!");
        System.out.println("Name: " + name);
        System.out.println("Floor number: " + traits.get_corr_no());
        System.out.println("Classroom number: " + traits.get_no());
    }
     
    abstract void attend(int N);
}
package school;

public class Schoolyard extends Space{
    public Schoolyard(int number){
        super(number);   
    }
    void enter(Student student){
        increase_people(1);
        String name = student.get_name();
        System.out.println(name + " enters schoolyard!");
        student.set_where("scoolyard");
    }
    void exit(Student student){
        decrease_people(1);
        String name = student.get_name();
        System.out.println(name + " exits schoolyard!");
    }
}
package school;

public class Stairs extends Space{
	public Stairs(int number){
        super(number);   
    }

    void enter(Student student){
        increase_people(1);
        String name = student.get_name();
        System.out.println(name + " enters stairs!");
        student.set_where("stairs");
    }
    void exit(Student student){
        decrease_people(1);
        String name = student.get_name();
        System.out.println(name + " exits stairs!");
    }
}
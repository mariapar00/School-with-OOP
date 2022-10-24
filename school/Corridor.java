package school;
import java.util.Vector;  

public class Corridor extends Space{
    int floor_no;
    Vector<Classroom> classes;

    public Corridor(int no, int number, int Cclass){
        super(number);   
        floor_no = no;
        classes = new Vector<Classroom>(6);
        for(int i=0 ; i<6 ; i++){
            Classroom c = new Classroom(i+1, floor_no, Cclass, 0);
            classes.add(c);
        }
    }

    void enter(Student student){
        increase_people(1);
        String name = student.get_name();
        if(floor_no==1) System.out.println(name + " enters 1st floor!");
        if(floor_no==2) System.out.println(name + " enters 2nd floor!");
        if(floor_no==3) System.out.println(name + " enters 3rd floor!");
        System.out.println(name + " enters corridor!");
        student.set_where("corridor");
    }
    void exit(Student student){
        decrease_people(1);
        String name = student.get_name();
        System.out.println(name + " exits corridor!");
        if(floor_no==1) System.out.println(name + " exits 1st floor!");
        if(floor_no==2) System.out.println(name + " exits 2nd floor!");
        if(floor_no==3) System.out.println(name + " exits 3rd floor!");
    }

    int get_floor_no(){
        return floor_no;
    }

    Classroom get_class(int no){
        return classes.get(no-1);
    }

    @Override
    void operate(int N){
        for(int i=0 ; i<6 ; i++) classes.get(i).operate(N);
    }
}
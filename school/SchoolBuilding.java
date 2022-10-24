package school;
import java.util.Vector;  

public class SchoolBuilding extends Space{
    Schoolyard yard;
    Stairs stair;
    Vector<Corridor> corr;
    int numberof_teachers;

    public SchoolBuilding(int number, int Cclass){
        super(number);
        yard = new Schoolyard(number);
        stair = new Stairs(number);
        corr = new Vector<Corridor>(3);
        for(int i=0 ; i<3 ; i++){
            Corridor c = new Corridor(i+1, number, Cclass);
            corr.add(c);
        }
    }
   
    void set_numberof_teachers(int n){
        numberof_teachers = n;
    }

    void enter(Student student){
        increase_people(1);
        String name = student.get_name();
        System.out.println(name + " enters school!");
        student.set_where("in");
    }
    void exit(Student student){
        decrease_people(1);
        String name = student.get_name();
        System.out.println(name + " exits school!");
        student.set_where("out");
    }

    Schoolyard get_yard(){
        return yard;
    }

    Stairs get_stair(){
        return stair;
    }

    Corridor get_corr(int no){
        return corr.get(no-1);
    }

    int get_numberof_teachers(){
        return numberof_teachers;
    }

    @Override
    void operate(int N){
        for(int i=0 ; i<3 ; i++) corr.get(i).operate(N);
    }
}
package school;
import java.util.Vector;  

public class Classroom extends Space{
    protected int no;
    int corr_no;
    int capacity;
    Teacher teacher;
    Vector<Student> students;

    public Classroom(int number, int floor, int Cclass, int people){
        super(people);
        no = number;
        corr_no = floor;
        capacity = Cclass;
        students = new Vector<Student>(Cclass);
        teacher = null;
    }

    public Classroom(int people){
        super(people);
    }
    
    void set_teacher(Teacher teacher){
        this.teacher = teacher;
    }

    void enter(Student student){
        increase_people(1);
        students.add(student);
        String name = student.get_name();
        System.out.println(name + " enters classroom!");
        student.set_where("classroom");
    }

    void exit(Student student){
        students.remove(student);
        decrease_people(1);
        String name = student.get_name();
        System.out.println(name + " exits classroom!");
        student.set_where("out of classroom");
    }

    int get_no(){
        return no;
    }
    int get_corr_no(){
        return corr_no;
    }
    int get_Cclass(){
        return capacity;
    }
    Teacher get_teacher(){
        return teacher;
    }
    Vector<Student> get_students(){
        return students;
    }

    @Override
    void operate(int N){
        for(int i=0 ; i<people ; i++) students.get(i).attend(N);
        if(teacher!=null) teacher.teach(N);
    }
}
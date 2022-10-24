package school;

//Person**************************************************************************************************************************
public abstract class Person{
    protected String name;
    protected String appearance;
    protected Classroom traits;
    protected int grade_tiredness;

    Person(String nam, String here, Classroom classroom){
        name = nam;
        appearance = here;
        traits = classroom;
    }

    void set_class(Classroom classroom){
        traits = classroom;
    }

    void set_where(String here){
        appearance = here;
    }

    void increase_tiredness(int n){
        grade_tiredness += n;
    }

    String get_name(){
        return name;
    }

    Classroom get_class(){
        return traits;
    }

    String get_where(){
        return appearance;
    }

    int get_grade(){
        return grade_tiredness;
    }
}
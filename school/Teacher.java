package school;

public class Teacher extends Person{
    int Lt;
    Teacher(String nam, Classroom classroom, int L){
        super(nam, "out", classroom);
        Lt = L;
    }
     
    void teach(int N){
        grade_tiredness += Lt*N;
    }
}
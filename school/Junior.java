package school;

public class Junior extends Student{
    int Lj;
    Junior(String nam, Classroom classroom, int L){
        super(nam, classroom);
        Lj = L;
    }

    void attend(int N){
        grade_tiredness += Lj*N;
    }
}
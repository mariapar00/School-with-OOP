package school;

public class Senior extends Student{
    int Ls;
    Senior(String nam, Classroom classroom, int L){
        super(nam, classroom);
        Ls = L;
    }

    void attend(int N){
        grade_tiredness += Ls*N;
    }
}
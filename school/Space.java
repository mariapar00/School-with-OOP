package school;

public abstract class Space{
    protected int people;

    public Space(int number){
        people = number;
    }

    final void increase_people(int n){
        people += n;
    }

    final void decrease_people(int n){
        people -= n;
    }

    abstract void enter(Student student);
    abstract void exit(Student student);

    int get_people(){
        return people;
    }

    void operate(int N){}
}
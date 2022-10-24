package school;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.security.SecureRandom;


public class Main{
    static SchoolBuilding school;
    public static void main(String[] args){
        int Cclass = Integer.parseInt(args[0]);
        int Lj = Integer.parseInt(args[1]);
        int Ls = Integer.parseInt(args[2]);
        int Lt = Integer.parseInt(args[3]);
        int N = Integer.parseInt(args[4]);
        String file = args[5];

        school = new SchoolBuilding(0, Cclass);

        int junior_counter = 0;
        int senior_counter = 0;
        int teacher_counter = 0;

        try {
            File myObj = new File(file);
            Scanner input = new Scanner(myObj);
            while (input.hasNext()) {
                String word  = input.next();
                String role = word;
                String name;
                word  = input.next();
                name = word;
                name += " ";
                word  = input.next();
                name += word;

                if(role.equals("junior")){
                    if(junior_counter == Cclass*9){             //9 of the 18 classes are junior classes. Juniors shouldn't exceed Cclass*9.
                        System.out.println("Junior classrooms are full. " + name + " cannot enter.");
                        continue;
                    }
                    Classroom classroom = arrange_student(1, Cclass);
                    Junior student = new Junior(name, classroom, Lj);
                    enter(student);
                    junior_counter++;
                }

                if(role.equals("senior")){
                    if(senior_counter == Cclass*9){             //9 of the 18 classes are senior classes. Seniors shouldn't exceed Cclass*9.
                        System.out.println("Senior classrooms are full. " + name + " cannot enter.");
                        continue;
                    }
                    Classroom classroom = arrange_student(2, Cclass);
                    Senior student = new Senior(name, classroom, Ls);
                    enter(student);
                    senior_counter++;
                }

                if(role.equals("teacher")){
                    if(teacher_counter == 18){                  //School has 18 classes, teachers shouldn't exceed this number.
                        System.out.println("All classrooms have a teacher. " + name + " cannot enter.");
                        continue;
                    }
                    Classroom classroom = arrange_teacher();
                    Teacher teacher = new Teacher(name, classroom, Lt);
                    classroom.set_teacher(teacher);
                    place(teacher);
                    teacher_counter++;
                }
            }
            input.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        school.operate(N);      
        print();
        empty();
        teachers_out();
        //print();
    }

    static Classroom arrange_student(int role, int Cclass){
        Classroom classroom = new Classroom(0);
        Random rand = new SecureRandom();
        while(true){
            int no = rand.nextInt(9) + 1; 
            /*  no is:
                1-3 -> 1st floor
                4-6 -> 2nd floor
                7-9 -> 3rd floor
                if the student is junior their class number must be 1, 2 or 3 so in 1st floor: we take no as it is
                                                                                in 2nd floor: we subtract 3 from no
                                                                                in 3rd floor: we subtract 6 from no
                if the student is senior their class number must be 4, 5 or 6 so in 1st floor: we add 3 to no
                                                                                in 2nd floor: we take no as it is
                                                                                in 3rd floor: we subtract 3 from no
            */
            if(no < 4){     
                Corridor corr = school.get_corr(1);    //1st floor
                if(role == 1) classroom = corr.get_class(no);   
                if(role == 2) classroom = corr.get_class(no+3);  
                if(classroom.get_people() >= Cclass) continue;          //Class is full so let's search for another
                else break;
            }
            else if(no < 7){
                Corridor corr = school.get_corr(2);    //2nd floor
                if(role == 1) classroom = corr.get_class(no-3);
                if(role == 2) classroom = corr.get_class(no);
                if(classroom.get_people() >= Cclass) continue;          //Class is full so let's search for another
                else break;
            }
            else{
                Corridor corr = school.get_corr(3);    //3rd floor
                if(role == 1) classroom = corr.get_class(no-6);
                if(role == 2) classroom = corr.get_class(no-3);
                if(classroom.get_people() >= Cclass) continue;          //Class is full so let's search for another
                else break;
            }  
        }
        return classroom;
    }
    

    static Classroom arrange_teacher(){
        Classroom classroom = new Classroom(0);
        Random rand = new SecureRandom();
        while(true){
            int no = rand.nextInt(18) + 1; 
            /*  no is:
                1-6 -> 1st floor
                7-12 -> 2nd floor
                13-18 -> 3rd floor
                Classes numbers in each floor are 1, 2, 3, 4, 5 or 6 so in 1st floor: we take no as it is
                                                                        in 2nd floor: we subtract 6 from no
                                                                        in 3rd floor: we subtract 12 from no
            */ 
            if(no < 7){
                Corridor corr = school.get_corr(1);    //1st floor
                classroom = corr.get_class(no);
                if(classroom.get_teacher() == null) break;              //Class doesn't have a teacher so stop searching
            }
            else if(no < 13){
                Corridor corr = school.get_corr(2);    //2nd floor
                classroom = corr.get_class(no-6);
                if(classroom.get_teacher() == null) break;              //Class doesn't have a teacher so stop searching
            }
            else{
                Corridor corr = school.get_corr(3);    //3rd floor
                classroom = corr.get_class(no-12);
                if(classroom.get_teacher() == null) break;              //Class doesn't have a teacher so stop searching
            }
        }
        return classroom;
    }


    static void enter(Student student){
        school.enter(student);                             //enters school 
        school.get_yard().enter(student);                 //enters schoolyard
        school.get_yard().exit(student);                  //exits schoolyard
    
        school.get_stair().enter(student);                //enters stairs
        school.get_stair().exit(student);                 //exits stairs
        
        int floor = student.get_class().get_corr_no();    
        int number = student.get_class().get_no();
    
        Corridor corr = school.get_corr(floor);
        corr.enter(student);                               //enters floor + corridor
        System.out.println(student.get_name() + " exits corridor!");
    
        Classroom classroom = corr.get_class(number);
        classroom.enter(student);                          //enters classroom
        System.out.println();
    }
    
    
    static void place(Teacher teacher){
        teacher.set_where("classroom");
    }

    static void print(){    
        System.out.println("School life consists of: \n");
        for(int i=1 ; i<=3 ; i++){
            Corridor corr = school.get_corr(i);
            System.out.println("Floor number " + corr.get_floor_no() + " contains: ");
            for(int j=1 ; j<=6 ; j++){
                Classroom classroom = corr.get_class(j);
                System.out.print("   People in class " + j +" are: ");
                int number = classroom.get_people();
                if(number == 0) System.out.println("none ");
                else{
                    Vector<Student> students = classroom.get_students();
                    for(int z=0 ; z<number ; z++){ 
                        Student student = students.get(z);
                        int l = student.get_name().length();
                        String space = "";
                        for(int k = 30-l ; k>0 ; k--) space += " ";
                        if(student == students.firstElement()) System.out.println(student.get_name() + space + "Tiredness: " + student.get_grade());
                        else System.out.println("                          " + student.get_name() + space + "Tiredness: " + student.get_grade());
                    }
                }
                Teacher teacher = classroom.get_teacher();
                if(teacher != null){
                    int l = teacher.get_name().length();
                    String space = "";
                    for(int k = 30-l ; k>0 ; k--) space += " ";
                    System.out.println("                          The teacher is: \n" + "                          " + teacher.get_name() + space + "Tiredness: " + teacher.get_grade());
                }
                else System.out.println("                          There is no teacher ");
            }
            System.out.println();
        }
    }

    static void empty(){
        for(int i=1 ; i<=3 ; i++){
            Corridor corr = school.get_corr(i);
            for(int j=1 ; j<=6 ; j++){
                Classroom classroom = corr.get_class(j);
                if(classroom.get_people() != 0){
                    Vector<Student> students = classroom.get_students();
                    while(classroom.get_people()>0){ 
                        Student student = students.firstElement();
                        System.out.println(student.get_name() + " starts exiting!");
                        exit(student, classroom, corr);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    static void exit(Student student, Classroom classroom, Corridor corr){
        classroom.exit(student);
        corr.exit(student);
        school.get_stair().enter(student);
        school.get_stair().exit(student);
        school.get_yard().enter(student);
        school.get_yard().exit(student);
        school.exit(student);
        System.out.println();
    }

    static void teachers_out(){
        for(int i=1 ; i<=3 ; i++){
            Corridor corr = school.get_corr(i);
            for(int j=1 ; j<=6 ; j++){
                Classroom classroom = corr.get_class(j);
                Teacher teacher = classroom.get_teacher();
                if(teacher!=null){
                    System.out.println(teacher.get_name() + " teacher is out!");
                    teacher.set_where("out of classroom");
                    classroom.set_teacher(null);
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

}



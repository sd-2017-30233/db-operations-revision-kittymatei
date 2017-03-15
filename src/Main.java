import java.util.Scanner;

/**
 * Created by Cristina on 3/11/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        ConnectionToDB con=new ConnectionToDB();
        Scanner in = new Scanner(System.in);
        while(true)
        {
            System.out.println("CHOOSE ONE OF THE FOLLOWING : ");
            System.out.println("1.Add Student.");
            System.out.println("2.Add Course.");
            System.out.println("3.Update Student.");
            System.out.println("4.Update Course.");
            System.out.println("5.Delete Student.");
            System.out.println("6.Delete Course");
            System.out.println("7.View Student.");
            System.out.println("8.View Course.");
            System.out.println("9.View Enrolled Students.");
            System.out.println("10.Enroll Students.");
            System.out.print("YOUR CHOISE: ");

            int val = in.nextInt();

            switch(val)
            {
                case 1: Student s=new Student("Gliga Paula","1995-04-14","Cluj-Napoca, Donath 174");
                        con.addStudent(s);
                        System.out.println("Done!");
                        break;
                case 2: Course c=new Course("PC","Ignat",1);
                        con.addCourse(c);
                        System.out.println("Done!");
                        break;
                case 3: con.updateStudent("Puia Andreea", "Dej, Florilor 64");
                        System.out.println("Done!");
                        break;
                case 4: con.updateCourse("IS","Mitrea");
                        System.out.println("Done!");
                        break;
                case 5: con.deleteStudent("Matei Cristina");
                        System.out.println("Done!");
                        break;
                case 6: con.deleteCourse("SSC");
                        System.out.println("Done!");
                        break;
                case 7: System.out.println("STUDENT INFORMATION : "+con.viewStudent("Lazar Raluca"));
                        break;
                case 8: System.out.println("COURSE INFORMATION : "+con.viewCourse("PL"));
                        break;
                case 9: System.out.println("STUDENTS ENROLLED AT PS : "+con.viewEnrolledStudents("PS"));
                        break;
                case 10:con.enroll("Sechel Raluca","IIA");
                        System.out.println("Done!");
                        break;
                default: System.out.println("Wrong Choise!");
                        break;
            }

        }
    }
}

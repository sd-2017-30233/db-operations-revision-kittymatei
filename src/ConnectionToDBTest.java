import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cristina on 3/14/2017.
 */
public class ConnectionToDBTest {

    ConnectionToDB connection=new ConnectionToDB();

    @org.junit.Test
    public void addStudent() throws Exception {
        Student student=new Student("Radu Paul","1992-12-28","Sibiu, Viena 4");
        connection.addStudent(student);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from STUDENT where nume='" + student.getName() + "'and data_nasterii='" + student.getBirthDate() + "'and adresa='" + student.getAddress() + "';";
            ResultSet s=stmt.executeQuery(query);
            String n="",d="",a="";
            while (s.next()) {
                n=s.getString("nume");
                d=s.getString("data_nasterii");
                a=s.getString("adresa");
            }
            assert n.equals(student.getName()) && d.equals(student.getBirthDate()) && a.equals(student.getAddress());
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    @org.junit.Test
    public void addCourse() throws Exception {
        Course course=new Course("PMP","Danescu",3);
        connection.addCourse(course);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from CURS where nume='" + course.getName() + "'and profesor='" + course.getTeacher() + "'and an_studiu='" + course.getStudyYear() + "';";
            ResultSet s=stmt.executeQuery(query);
            String n="",t="";
            int y=0;
            while (s.next()) {
                n=s.getString("nume");
                t=s.getString("profesor");
                y=s.getInt("an_studiu");
            }
            assert n.equals(course.getName())&& t.equals(course.getTeacher()) && y==course.getStudyYear();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    @org.junit.Test
    public void updateStudent() throws Exception {
        Student student=new Student("Barbat Alex","1994-10-04","Sibiu, Orhideilor 4");
        String address="Cluj-Napoca, Observatorului 7";
        connection.addStudent(student);
        connection.updateStudent(student.getName(),address);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from STUDENT where nume='" + student.getName() + "'and data_nasterii='" + student.getBirthDate() + "';";
            ResultSet s=stmt.executeQuery(query);
            String n="",d="",a="";
            while (s.next()) {
                n=s.getString("nume");
                d=s.getString("data_nasterii");
                a=s.getString("adresa");
            }
            assert n.equals(student.getName()) && d.equals(student.getBirthDate()) && a.equals(address);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    @org.junit.Test
    public void updateCourse() throws Exception {
        Course course=new Course("AC","Baruch",2);
        String teacher="Oniga";
        connection.addCourse(course);
        connection.updateCourse(course.getName(),teacher);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from CURS where nume='" + course.getName() + "'and an_studiu='" + course.getStudyYear() +  "';";
            ResultSet s=stmt.executeQuery(query);
            String n="",t="";
            int y=0;
            while (s.next()) {
                n=s.getString("nume");
                t=s.getString("profesor");
                y=s.getInt("an_studiu");
            }
            assert n.equals(course.getName())&& t.equals(teacher) && y==course.getStudyYear();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }


    @org.junit.Test
    public void enroll() throws Exception {
        Student student=new Student("Deac Dan","1995-04-10","Simleul Silvaniei, Viena 4");
        Course course=new Course("LFT","Negrescu",3);
        connection.addStudent(student);
        connection.addCourse(course);
        connection.enroll(student.getName(),course.getName());
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select *  from STUDENT,CURS,INREGISTRARE where STUDENT.nume='" + student.getName() + "'and CURS.nume='" + course.getName() +  "' and STUDENT.id=INREGISTRARE.id_student and CURS.id=INREGISTRARE.id_curs;";
            ResultSet s=stmt.executeQuery(query);
            String st="",c="";
            while (s.next()) {
                st=s.getString("student.nume");
                c=s.getString("curs.nume");

            }
            assert st.equals(student.getName())&& c.equals(course.getName());

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    @org.junit.Test
    public void deleteStudent() throws Exception {
        Student student=new Student("Abrudan Darius","1995-03-03","Cluj-Napoca, Dambul Rotund 4");
        connection.addStudent(student);
        connection.deleteStudent(student.getName());
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from STUDENT where nume='" + student.getName() + "';";
            ResultSet s=stmt.executeQuery(query);
            assert !s.next();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    @org.junit.Test
    public void deleteCourse() throws Exception {
        Course course=new Course("PI","Danescu",3);
        connection.addCourse(course);
        connection.deleteCourse(course.getName());
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from CURS where nume='" + course.getName() +"';";
            ResultSet s=stmt.executeQuery(query);
            assert !s.next();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }



}
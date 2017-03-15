/**
 * Created by Cristina on 3/11/2017.
 */
import java.sql.*;



public class ConnectionToDB {
    private String url = "jdbc:mysql://localhost/University";
    private String uid = "root";
    private String pw = "podeni";
    private Connection con;

    public ConnectionToDB() {
        con = init();
    }

    Connection getConnection()
    {
        return con;
    }

    public Connection init() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e);
        }

        con = null;
        try {
            con = DriverManager.getConnection(url, uid, pw);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            System.exit(1);
        }
        return con;
    }

    public void addStudent(Student s) {
        try {
            Statement stmt = con.createStatement();
            String query = "insert into STUDENT (nume, data_nasterii, adresa) values ('" + s.getName() + "','" + s.getBirthDate() + "','" + s.getAddress() + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

    }

    public void addCourse(Course c) {
        try {
            Statement stmt = con.createStatement();
            String query = "insert into CURS (nume, profesor, an_studiu) values ('" + c.getName() + "','" + c.getTeacher() + "'," + c.getStudyYear() + ");";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

    }

    public void updateStudent(String name, String address) {
        try {
            Statement stmt = con.createStatement();
            String query = "call UPDATE_STUDENT ('" + name + "','" + address + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    public void updateCourse(String name, String teacher) {
        try {
            Statement stmt = con.createStatement();
            String query = "call UPDATE_CURS ('" + name + "','" + teacher + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    public void deleteStudent(String name) {
        try {
            Statement stmt = con.createStatement();
            String query = "call DELETE_STUDENT ('" + name + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }


    public void deleteCourse(String name) {
        try {
            Statement stmt = con.createStatement();
            String query = "call DELETE_CURS ('" + name + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    public void enroll(String nameS, String nameC) {
        try {
            Statement stmt = con.createStatement();
            String query = "call ENROLL ('" + nameS + "','" + nameC + "');";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    public String viewStudent(String name) {
        try {

            Statement stmt = con.createStatement();
            String query = "select nume, data_nasterii, adresa from STUDENT where nume='" + name + "';";
            ResultSet s = stmt.executeQuery(query);

            String info = "";
            while (s.next()) {
                info = info + s.getString("nume") + "  ";
                info = info + s.getString("data_nasterii") + "  ";
                info = info + s.getString("adresa");
            }

            return info;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
        return "Fail!";
    }

    public String viewCourse(String name) {
        try {

            Statement stmt = con.createStatement();
            String query = "select nume, profesor, an_studiu from CURS where nume='" + name + "';";
            ResultSet s = stmt.executeQuery(query);

            String info = "";
            while (s.next()) {
                info = info + s.getString("nume") + "  ";
                info = info + s.getString("profesor") + "  ";
                info = info + s.getInt("an_studiu");
            }

            return info;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
        return "Fail!";
    }

    public String viewEnrolledStudents(String name)
    {
        try {

            Statement stmt = con.createStatement();
            String query = "select STUDENT.nume from STUDENT,CURS,INREGISTRARE where CURS.nume='" + name + "' and STUDENT.id=INREGISTRARE.id_student and CURS.id=INREGISTRARE.id_curs;";
            ResultSet s = stmt.executeQuery(query);

            String info = "";
            while (s.next()) {
                info = info + s.getString("nume") + "  ";
            }

            return info;
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
        return "Fail!";
    }
}

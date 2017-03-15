/**
 * Created by Cristina on 3/11/2017.
 */
public class Course
{
    private String name;
    private String teacher;
    private int studyYear;

    public Course(String name, String teacher, int studyYear )
    {
        this.name=name;
        this.teacher=teacher;
        this.studyYear=studyYear;
    }

    public String getName()
    {
        return name;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public int getStudyYear()
    {
        return studyYear;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }

    public void setStudyYear(int studyYear)
    {
        this.studyYear = studyYear;
    }
}

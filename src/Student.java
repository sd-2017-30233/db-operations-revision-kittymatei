/**
 * Created by Cristina on 3/11/2017.
 */
public class Student
{
    private String name;
    private String birthDate;
    private String address;

    public Student(String name, String birthDate, String address)
    {
        this.name=name;
        this.birthDate=birthDate;
        this.address=address;
    }

    public String getName()
    {
        return name;
    }

    public String getBirthDate()
    {
        return birthDate;
    }

    public String getAddress()
    {
        return address;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setBirthDate(String birthDate)
    {
        this.birthDate=birthDate;
    }

    public void setAddress(String address)
    {
        this.address=address;
    }


}

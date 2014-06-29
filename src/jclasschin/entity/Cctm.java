package jclasschin.entity;
// Generated Jun 22, 2014 8:22:51 PM by Hibernate Tools 3.6.0

/**
 * Cctm generated by hbm2java
 */
public class Cctm implements java.io.Serializable
{

    private Integer id;
    private Weekday weekday;
    private Person person;
    private Course course;
    private Period period;
    private Dedication dedication;
    private String description;

    public Cctm()
    {
    }

    public Cctm(Weekday weekday, Person person, Course course, Period period, Dedication dedication, String description)
    {
        this.weekday = weekday;
        this.person = person;
        this.course = course;
        this.period = period;
        this.dedication = dedication;
        this.description = description;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Weekday getWeekday()
    {
        return this.weekday;
    }

    public void setWeekday(Weekday weekday)
    {
        this.weekday = weekday;
    }

    public Person getPerson()
    {
        return this.person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Course getCourse()
    {
        return this.course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Period getPeriod()
    {
        return this.period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public Dedication getDedication()
    {
        return this.dedication;
    }

    public void setDedication(Dedication dedication)
    {
        this.dedication = dedication;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}

/*
 * The MIT License
 *
 * Copyright 2014 HP.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jclasschin.model;

import java.util.List;
import jclasschin.entity.Cctm;
import jclasschin.entity.Course;
import jclasschin.entity.Ctacss;
import jclasschin.entity.Dedication;
import jclasschin.entity.Period;
import jclasschin.entity.Person;
import jclasschin.entity.Weekday;
import jclasschin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class CCManager
{

    private Session session;
    private Cctm cctm;

    public boolean insert(String day, String className, String time, String courseName, String prof)
    {
        WeekDayManager wdm = new WeekDayManager();
        Weekday weekday = wdm.selectByName(day);

        DedicationManager dm = new DedicationManager();
        Dedication dedication = dm.selectDedicationByTermAndFieldAndClassroom(CtacssManager.currentTerm.getId(), Login.loggedUserField, className);

        String[] sePeriod = time.split(" - ");
        ScheduleManager sm = new ScheduleManager();
        Period p = sm.selectByPeriodStartAndEndAndScheduleID(sePeriod[0], sePeriod[1], CtacssManager.currentSchedule.getId());

        CourseManager cm = new CourseManager();
        Course course = cm.selectByCourseName(courseName);

        String[] sPorf = prof.split(" - ");
        Integer profID = Integer.parseInt(sPorf[0]);

        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Person professor = (Person) session.load(Person.class, profID);

            cctm = new Cctm(weekday, professor, course, p, dedication, null);
            session.save(cctm);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public boolean update(Integer oldCctmId, String day, String className, String time, String courseName, String prof)
    {
        WeekDayManager wdm = new WeekDayManager();
        Weekday weekday = wdm.selectByName(day);

        DedicationManager dm = new DedicationManager();
        Dedication dedication = dm.selectDedicationByTermAndFieldAndClassroom(CtacssManager.currentTerm.getId(), Login.loggedUserField, className);

        String[] sePeriod = time.split(" - ");
        ScheduleManager sm = new ScheduleManager();
        Period p = sm.selectByPeriodStartAndEndAndScheduleID(sePeriod[0], sePeriod[1], CtacssManager.currentSchedule.getId());

        CourseManager cm = new CourseManager();
        Course course = cm.selectByCourseName(courseName);

        String[] sPorf = prof.split(" - ");
        Integer profID = Integer.parseInt(sPorf[0]);

        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Person professor = (Person) session.load(Person.class, profID);

            cctm = (Cctm) session.load(Cctm.class, oldCctmId);
            cctm.setWeekday(weekday);
            cctm.setPerson(professor);
            cctm.setCourse(course);
            cctm.setPeriod(p);
            cctm.setDedication(dedication);

            session.update(cctm);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public boolean delete(Integer cctmId)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            cctm = (Cctm) session.load(Cctm.class, cctmId);
            session.delete(cctm);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public List selectAll()
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Cctm c where c.dedication.term.name =:tn");
            q.setParameter("tn", CtacssManager.currentTerm.getName());
            List resultList = q.list();

            session.getTransaction().commit();
            return resultList;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public List selectAllByLike(String filterProperty, String likeValue)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String query = "from Cctm c where c." + filterProperty + " like '" + likeValue + "%'" + " and c.dedication.term.name =:tn";
            Query q = session.createQuery(query);
            q.setParameter("tn", CtacssManager.currentTerm.getName());
            List resultList = q.list();

            session.getTransaction().commit();
            return resultList;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public int checkExist(String day, String className, String time)
    {
        String[] sePeriod = time.split(" - ");
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Cctm c where c.weekday.dayName=:dn and c.dedication.classroom.name=:cn "
                    + "and c.period.start=:tn and c.dedication.term.name=:tena");
            q.setParameter("dn", day);
            q.setParameter("cn", className);
            q.setParameter("tn", sePeriod[1]);
            q.setParameter("tena", CtacssManager.currentTerm.getName());
            List l = q.list();
            session.getTransaction().commit();
            return l.size();
        }
        catch (HibernateException he)
        {
            return 1;
        }
    }

}

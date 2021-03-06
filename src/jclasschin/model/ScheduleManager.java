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

import java.awt.PageAttributes;
import java.util.List;
import jclasschin.entity.Dedication;
import jclasschin.entity.Period;
import jclasschin.entity.Schedule;
import jclasschin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class ScheduleManager
{

    private Session session;
    private Schedule schedule;
    private Period period;

    public boolean insert(String scheduleName, Integer numberOfPeriod, String[] start, String[] end)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            schedule = new Schedule(scheduleName, numberOfPeriod, null, null);
            session.save(schedule);

            for (int i = 0; i < numberOfPeriod; i++)
            {
                period = new Period(schedule, start[i], end[i], null);
                session.save(period);
            }
            session.getTransaction().commit();
            return true;

        }
        catch (HibernateException he)
        {
            return false;
        }

    }

    public boolean update(Schedule oldSchedule, String[] start, String[] end, 
            String oldStart[], String oldEnd[])
    {
        try
        {
            //deleteByScheduleID(oldSchedule.getId());
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //schedule = (Schedule) session.load(Schedule.class, oldSchedule.getId());

            for (int i = 0; i < oldSchedule.getNumberOfPeriods(); i++)
            {
                Query q = session.createQuery("from Period p where p.start=:st and p.end=:en and p.schedule.id=:sid");
                q.setParameter("st", oldStart[i]);
                q.setParameter("en", oldEnd[i]);
                q.setParameter("sid", oldSchedule.getId());
                List l = q.list();
                period = (Period) l.get(0);

                period.setStart(start[i]);
                period.setEnd(end[i]);
                session.update(period);
            }
            session.getTransaction().commit();
            return true;

        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public boolean delete(Integer schId)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            schedule = (Schedule) session.load(Schedule.class, schId);
            session.delete(schedule);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public boolean deleteByScheduleID(Integer sid)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery q = session.createSQLQuery("delete from period where schedule_id=:sid");
            q.setParameter("sid", sid);
            q.executeUpdate();
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public List selectAllSchedule()
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List resultList;
            resultList = session.createQuery("from Schedule").list();
            session.getTransaction().commit();
            return resultList;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public Schedule selectByName(String sname)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List resultList;
            Query q = session.createQuery("from Schedule s where s.name=:sn");
            q.setParameter("sn", sname);
            resultList = q.list();
            Schedule s = (Schedule) resultList.get(0);
            session.getTransaction().commit();
            return s;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public List selectAllPeriod()
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List l = (List) session.createQuery("from Period").list();
            session.getTransaction().commit();
            return l;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public Period selectByPeriodStartAndEndAndScheduleID(String end, String start, Integer sid)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Period p where p.start=:s and p.end=:e and p.schedule.id=:sid");
            q.setParameter("s", start);
            q.setParameter("e", end);
            q.setParameter("sid", sid);
            List l = q.list();
            Period p = (Period) l.get(0);
            session.getTransaction().commit();
            return p;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }
    
    public int selectByName2(String sname)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List resultList;
            Query q = session.createQuery("from Schedule s where s.name=:sn");
            q.setParameter("sn", sname);
            resultList = q.list();
            
            session.getTransaction().commit();
            return resultList.size();
        }
        catch (HibernateException he)
        {
            return 1;
        }
    }
}

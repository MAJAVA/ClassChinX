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
import java.util.function.Consumer;
import jclasschin.entity.Classroom;
import jclasschin.entity.Dedication;
import jclasschin.entity.Field;
import jclasschin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class DedicationManager
{

    private Dedication dedication;
    private Session session;
    private Field field;
    private final FieldManager fieldManager = new FieldManager();
    private Classroom classroom;
    private final ClassManager classManager = new ClassManager();

    public boolean insert(String fieldName, List selectedClass)
    {
         
        field = fieldManager.selectByName(fieldName);
        deleteByFieldID(field.getId());
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            selectedClass.stream().forEach((Object sc) ->
            {
                classroom = classManager.selectByName((String) sc);
                dedication = new Dedication(CtacssManager.currentTerm, classroom, field, null);

                session.save(dedication);

            });
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public boolean update(Integer fieldID, String fieldName, List selectedClass)
    {
        deleteByFieldID(fieldID);
        field = fieldManager.selectByName(fieldName);
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            selectedClass.stream().forEach((Object sc) ->
            {
                classroom = classManager.selectByName((String) sc);
                dedication = new Dedication(CtacssManager.currentTerm, classroom, field, null);
                session.save(dedication);
            });
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }

    public Dedication selectDedicationByTermAndFieldAndClassroom(Integer tid, String fid, String cid)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Dedication d where d.term.id=:tid and d.field.name=:fid and d.classroom.name=:cid");
            q.setParameter("tid", tid);
            q.setParameter("fid", fid);
            q.setParameter("cid", cid);
            List resultList = q.list();
            if (resultList == null || resultList.isEmpty())
            {
                session.getTransaction().commit();
                return null;
            }
            else
            {
                Dedication d = (Dedication) resultList.get(0);
                session.getTransaction().commit();
                return d;
            }
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    private Dedication selcetDedicationByFieldIdAndClassroomID(Integer fid, Integer cid)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Dedication d where d.field.id=:fid and d.classroom.id=:cid");
            q.setParameter("fid", fid);
            q.setParameter("cid", cid);
            List resultList = q.list();
            //session.createQuery("from Field f where f.name=\""+fieldName+"\"").list();
            session.getTransaction().commit();
            dedication = (Dedication) resultList.get(0);
            return dedication;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public boolean deleteByFieldID(Integer fid)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery q = session.createSQLQuery("delete from dedication where field_id=:fid");
            q.setParameter("fid", fid);
            q.executeUpdate();
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
            List l = (List) session.createQuery("from Dedication").list();
            session.getTransaction().commit();
            return l;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

}

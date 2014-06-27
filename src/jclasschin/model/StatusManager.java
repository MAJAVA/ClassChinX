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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jclasschin.entity.Field;
import jclasschin.entity.Status;
import jclasschin.entity.Term;
import jclasschin.util.HibernateUtil;
import jclasschin.util.Utilities;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class StatusManager
{

    Session session;
    Status status;

    public boolean insert()
    {

        FieldManager filedManager = new FieldManager();
        Field f = filedManager.selectByName(Login.loggedUserField);

        Term t = CtacssManager.currentTerm;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String ctime = Utilities.getCurrentShamsidate() + "  " + dateFormat.format(date);

        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            status = new Status(t, f, ctime, Boolean.FALSE);
            session.save(status);
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
            List resultList = session.createQuery("from Status").list();
            session.getTransaction().commit();
            return resultList;
        }

        catch (HibernateException he)
        {
            return null;
        }
    }

    public List selectAllByTerm(String name)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Status s where s.term.name=:tn");
            q.setParameter("tn", name);
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        }

        catch (HibernateException he)
        {
            return null;
        }
    }

}

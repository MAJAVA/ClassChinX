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
import jclasschin.entity.Mail;
import jclasschin.entity.Person;
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
public class MailManager
{

    private Mail mail;
    private Session session;

    public boolean insert(String receiverName, String subject, String text)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Person sender = (Person) session.load(Person.class, Login.loggedUser.getPerson().getId());
            UserManager um = new UserManager();
            Person receiver = um.selectByUserName(receiverName).getPerson();
            Term term = CtacssManager.currentTerm;
            mail = new Mail(term, receiver, sender, subject, text,
                    Utilities.getCurrentShamsidate(), Boolean.FALSE, Boolean.FALSE);
            session.save(mail);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }

    }

    public List selectForInbox()
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Mail m where m.personByReceiverPersonId.id=:rid and m.receiverDelete=false");
            q.setParameter("rid", Login.loggedUser.getPerson().getId());
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public List selectForOutbox()
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Mail m where m.personBySenderPersonId.id=:sid and m.senderDelete=false");
            q.setParameter("sid", Login.loggedUser.getPerson().getId());
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        }
        catch (HibernateException he)
        {
            return null;
        }
    }

    public boolean insertForReply(Person personBySenderPersonId, String subject, String text)
    {
         try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Person sender = (Person) session.load(Person.class, Login.loggedUser.getPerson().getId());
            Term term = CtacssManager.currentTerm;
            mail = new Mail(term,  personBySenderPersonId, sender, subject, text,
                    Utilities.getCurrentShamsidate(), Boolean.FALSE, Boolean.FALSE);
            
            session.save(mail);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }

    }
    
    public boolean deleteForOutbox(Integer id)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            mail =(Mail) session.load(Mail.class, id);
            mail.setSenderDelete(Boolean.TRUE);
            session.update(mail);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }
    
    
    public boolean deleteForInbox(Integer id)
    {
        try
        {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            mail =(Mail) session.load(Mail.class, id);
            mail.setReceiverDelete(Boolean.TRUE);
            session.update(mail);
            session.getTransaction().commit();
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
    }
    
}

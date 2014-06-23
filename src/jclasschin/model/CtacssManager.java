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

import jclasschin.entity.Ctacss;
import jclasschin.entity.Schedule;
import jclasschin.entity.Term;
import jclasschin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class CtacssManager
{
    public static Term currentTerm;
    private static Schedule currentSchedule;
    
    private TermManager termManager;
    private Ctacss ctacss;
    private Session session;
    private Term term;

    public boolean updateCurrentTerm(String termName)
    {
        
        termManager = new TermManager();
        term = termManager.selectByName(termName);
        
        try
        { 
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            ctacss = (Ctacss) session.load(Ctacss.class, 1);
            ctacss.setTerm(term);
            session.update(ctacss);
            currentTerm = term;
            session.getTransaction().commit(); 
            return true;
        }
        catch (HibernateException he)
        {
            return false;
        }
           
    }
    
    
    public void initCurrentTerm()
    {
            session = (Session) HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            ctacss = (Ctacss) session.load(Ctacss.class, 1);
            currentTerm = ctacss.getTerm();
            session.getTransaction().commit(); 
       
    }
}

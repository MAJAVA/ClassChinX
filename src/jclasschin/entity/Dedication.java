package jclasschin.entity;
// Generated Jun 22, 2014 8:22:51 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Dedication generated by hbm2java
 */
public class Dedication  implements java.io.Serializable {


     private Integer id;
     private Term term;
     private Classroom classroom;
     private Field field;
     private Set cctms = new HashSet(0);

    public Dedication() {
    }

    public Dedication(Term term, Classroom classroom, Field field, Set cctms) {
       this.term = term;
       this.classroom = classroom;
       this.field = field;
       this.cctms = cctms;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Term getTerm() {
        return this.term;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }
    public Classroom getClassroom() {
        return this.classroom;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    public Field getField() {
        return this.field;
    }
    
    public void setField(Field field) {
        this.field = field;
    }
    public Set getCctms() {
        return this.cctms;
    }
    
    public void setCctms(Set cctms) {
        this.cctms = cctms;
    }




}



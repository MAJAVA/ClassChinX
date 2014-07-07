/*
 * The MIT License
 *
 * Copyright 2014 Ali.
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
package jclasschin.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import jclasschin.entity.Course;
import jclasschin.entity.Field;
import jclasschin.entity.Person;
import jclasschin.model.CourseManager;
import jclasschin.model.CtacssManager;
import jclasschin.model.FieldManager;
import jclasschin.model.Login;
import jclasschin.model.PersonManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ReportsLayoutController implements Initializable
{

    @FXML
    private Button reportOnEmptyClassesButton;
    @FXML
    private Button reportOnGroupButton;
    @FXML
    private Button reportOnFieldButton;
    @FXML
    private Button reportOnProfessorButton;
    @FXML
    private Button reportOnCourseButton;
    @FXML
    private Button reportOnClassChinButton;

    @FXML
    private ComboBox<String> professorInReportOnProfessorComboBox;
    @FXML
    private ComboBox<String> professorInReportOnGourpComboBox;
    @FXML
    private ComboBox<String> courseInReportOnCourseComboBox;
    @FXML
    private ComboBox<String> courseInReportOnGourpComboBox;
    @FXML
    private ComboBox<String> fieldInReportOnFieldComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void reportOnEmptyClassesButtonOnAction(ActionEvent event)
    {
    }

    @FXML
    private void reportOnGroupButtonOnAction(ActionEvent event)
    {
//        if (courseInReportOnCourseComboBox.getValue() == null || professorInReportOnGourpComboBox.getValue() == null)
//        {
//            MainLayoutController.statusProperty.setValue("لطفا یک درس و یک استاد را انتخاب نمایید.");
//        }
//        else
//        {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://172.20.5.32:3306/class_chin_db?user=root");
            String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ClassChinX\\src\\jclasschin\\report\\reportOnGroup.jrxml";
            Map parameters = new HashMap();
            CourseManager cm = new CourseManager();
            Course c = cm.selectByCourseName(courseInReportOnGourpComboBox.getValue());
            String[] personID = professorInReportOnGourpComboBox.getValue().split(" - ");

            parameters.put("termName", CtacssManager.currentTerm.getName());
            parameters.put("courseId", c.getId());
            parameters.put("personId", Integer.parseInt(personID[0]));

            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
            JasperViewer.viewReport(jp, false);
            conn.close();

        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ScheduleLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (JRException ex)
        {

        }
        // }
    }

    @FXML
    private void reportOnFieldButtonOnAction(ActionEvent event)
    {
        if (fieldInReportOnFieldComboBox.getValue() == null)
        {
            MainLayoutController.statusProperty.setValue("یک رشته را انتخاب نمایید.");
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://172.20.5.32:3306/class_chin_db?user=root");
                String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ClassChinX\\src\\jclasschin\\report\\reportOnField.jrxml";
                Map parameters = new HashMap();
                FieldManager fm = new FieldManager();
                Field f = fm.selectByName(fieldInReportOnFieldComboBox.getValue());

                parameters.put("termName", CtacssManager.currentTerm.getName());
                parameters.put("fieldId", f.getId());

                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
                JasperViewer.viewReport(jp, false);
                conn.close();
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(ScheduleLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (JRException ex)
            {

            }
        }
    }

    @FXML
    private void reportOnProfessorButtonOnAction(ActionEvent event)
    {
        if (professorInReportOnProfessorComboBox.getValue() == null)
        {
            MainLayoutController.statusProperty.setValue("لطفا یک استاد را انتخاب نمایید.");
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://172.20.5.32:3306/class_chin_db?user=root");
                String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ClassChinX\\src\\jclasschin\\report\\reportOnProfessor.jrxml";
                String[] personID = professorInReportOnProfessorComboBox.getValue().split(" - ");
                Map parameters = new HashMap();

                parameters.put("termName", CtacssManager.currentTerm.getName());
                parameters.put("personId", Integer.parseInt(personID[0]));

                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
                JasperViewer.viewReport(jp, false);
                conn.close();

            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(ScheduleLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (JRException ex)
            {

            }
        }
    }

    @FXML
    private void reportOnCourseButtonOnAction(ActionEvent event)
    {
        if (courseInReportOnCourseComboBox.getValue() == null)
        {
            MainLayoutController.statusProperty.setValue("لطفا یک درس را انتخاب نمایید.");
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://172.20.5.32:3306/class_chin_db?user=root");
                String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ClassChinX\\src\\jclasschin\\report\\reportOnCourse.jrxml";
                Map parameters = new HashMap();
                CourseManager cm = new CourseManager();
                Course c = cm.selectByCourseName(courseInReportOnCourseComboBox.getValue());

                parameters.put("termName", CtacssManager.currentTerm.getName());
                parameters.put("courseId", c.getId());

                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
                JasperViewer.viewReport(jp, false);
                conn.close();

            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(ScheduleLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (JRException ex)
            {

            }
        }
    }

    @FXML
    private void reportOnClassChinButtonOnAction(ActionEvent event)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://172.20.5.32:3306/class_chin_db?user=root");
            String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ClassChinX\\src\\jclasschin\\report\\reportOnClassChin.jrxml";
            Map parameters = new HashMap();
           
            parameters.put("termName", CtacssManager.currentTerm.getName());

            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
            JasperViewer.viewReport(jp, false);
            conn.close();

        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ScheduleLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (JRException ex)
        {

        }

    }

    public void initDialog()
    {
        fillProfessorComboBox();
        fillCourseComboBox();
        fillFieldComboBox();

    }

    private void fillProfessorComboBox()
    {
        professorInReportOnGourpComboBox.getItems().clear();
        professorInReportOnProfessorComboBox.getItems().clear();

        PersonManager pm = new PersonManager();
        List l = pm.selectAllByFieldName(Login.loggedUserField);
        Collections.sort(l, (Person pi1, Person pi2) -> pi1.getId().compareTo(pi2.getId()));
        l.stream().forEach((p) ->
        {
            professorInReportOnGourpComboBox.getItems().add(((Person) p).getId() + " - " + ((Person) p).getFirstName()
                    + " " + ((Person) p).getLastName());
            professorInReportOnProfessorComboBox.getItems().add(((Person) p).getId() + " - " + ((Person) p).getFirstName()
                    + " " + ((Person) p).getLastName());
        });
    }

    private void fillCourseComboBox()
    {
        courseInReportOnCourseComboBox.getItems().clear();
        courseInReportOnGourpComboBox.getItems().clear();

        CourseManager cm = new CourseManager();
        List l = cm.selectAllByFieldName(Login.loggedUserField);
        Collections.sort(l, (Course pi1, Course pi2) -> pi1.getName().compareTo(pi2.getName()));
        l.stream().forEach((s) ->
        {
            courseInReportOnCourseComboBox.getItems().add(((Course) s).getName());
            courseInReportOnGourpComboBox.getItems().add(((Course) s).getName());
        });
    }

    private void fillFieldComboBox()
    {
        fieldInReportOnFieldComboBox.getItems().clear();

        FieldManager fm = new FieldManager();
        List l = fm.selectAll();
        Collections.sort(l, (Field pi1, Field pi2) -> pi1.getName().compareTo(pi2.getName()));
        l.stream().forEach((f) ->
        {
            fieldInReportOnFieldComboBox.getItems().add(((Field) f).getName());
        });

    }

}

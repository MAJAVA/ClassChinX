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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Course;
import jclasschin.entity.Dedication;
import jclasschin.entity.Period;
import jclasschin.entity.Person;
import jclasschin.entity.Weekday;
import jclasschin.model.CCManager;
import jclasschin.model.CourseManager;
import jclasschin.model.CtacssManager;
import jclasschin.model.DedicationManager;
import jclasschin.model.Login;
import jclasschin.model.PersonManager;
import jclasschin.model.ScheduleManager;
import jclasschin.model.StatusManager;
import jclasschin.model.WeekDayManager;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ScheduleNewDialogController implements Initializable
{

    private final ValidationSupport validationSupport = new ValidationSupport();
    private Stage scheduleNewDialogStage;
    private CCManager ccm;

    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private ComboBox<String> classComboBox;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private ComboBox<String> professorComboBox;
    @FXML
    private HBox okHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox cancelHBox;
    @FXML
    private ImageView cancelImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));
        ccm = new CCManager();
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("لطفا فیلدهای الزامی را تکمیل نمایید.");
        } else
        {
            int i;
            i = ccm.checkExist(dayComboBox.getValue(), classComboBox.getValue(), timeComboBox.getValue());
            if (i != 0)
            {
                MainLayoutController.statusProperty.setValue("متاسفیم. این کلاس در این روز و در این ساعت رزرو شده است.");
            } else
            {
                if (ccm.insert(dayComboBox.getValue(), classComboBox.getValue(), timeComboBox.getValue(),
                        courseComboBox.getValue(), professorComboBox.getValue()))
                {
                    StatusManager sm = new StatusManager();
                    sm.insert();
                    MainLayoutController.statusProperty.setValue("برنامه جدید ثبت شد.");
                } else
                {
                    MainLayoutController.statusProperty.setValue("عملیات ثبت برنامه جدید با شکست مواجه شد.");
                }
                scheduleNewDialogStage.close();
            }
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("عملیات ثبت برنامه جدید لغو شد.");
        scheduleNewDialogStage.close();
    }

    /**
     * @return the scheduleNewDialogStage
     */
    public Stage getScheduleNewDialogStage()
    {
        return scheduleNewDialogStage;
    }

    /**
     * @param scheduleNewDialogStage the scheduleNewDialogStage to set
     */
    public void setScheduleNewDialogStage(Stage scheduleNewDialogStage)
    {
        this.scheduleNewDialogStage = scheduleNewDialogStage;
    }

    void initDialog()
    {
        fillDayComboBox();
        fillClassComboBox();
        fillTimeComboBox();
        fillCourseComboBox();
        fillProfessorComboBox();

        validationSupport.registerValidator(dayComboBox, Validator.createEmptyValidator("روز را وارد کنید."));
        validationSupport.registerValidator(classComboBox, Validator.createEmptyValidator("کلاس را وارد کنید."));
        validationSupport.registerValidator(timeComboBox, Validator.createEmptyValidator("زمان را وارد کنید."));
        validationSupport.registerValidator(courseComboBox, Validator.createEmptyValidator("درس را وارد کنید."));
        validationSupport.registerValidator(professorComboBox, Validator.createEmptyValidator("استاد را وارد کنید."));

    }

    private void fillDayComboBox()
    {
        WeekDayManager wdm = new WeekDayManager();
        dayComboBox.getItems().clear();
        dayComboBox.setPromptText("انتخاب نمایید . . .");

        List l = wdm.selectAll();
        Collections.sort(l, (Weekday pi1, Weekday pi2) -> pi1.getId().compareTo(pi2.getId()));
        l.stream().forEach((wd) ->
        {
            dayComboBox.getItems().add(((Weekday) wd).getDayName());
        });
    }

    private void fillClassComboBox()
    {
        classComboBox.getItems().clear();
        classComboBox.setPromptText("انتخاب نمایید . . .");
        DedicationManager dm = new DedicationManager();
        List l = dm.selectAll();
        Collections.sort(l, (Dedication pi1, Dedication pi2) -> pi1.getClassroom().getName().compareTo(pi2.getClassroom().getName()));
        l.stream().forEach((d) ->
        {
            if (((Dedication) d).getField().getName().equals(Login.loggedUserField)
                    && ((Dedication) d).getTerm().getName().equals(CtacssManager.currentTerm.getName()))
            {
                classComboBox.getItems().add(((Dedication) d).getClassroom().getName());
            }

        });
    }

    private void fillTimeComboBox()
    {
        timeComboBox.getItems().clear();
        timeComboBox.setPromptText("انتخاب نمایید . . .");
        ScheduleManager sm = new ScheduleManager();
        List l = sm.selectAllPeriod();
        Collections.sort(l, (Period pi1, Period pi2) -> pi1.getId().compareTo(pi2.getId()));

        l.stream().forEach((s) ->
        {
            if (((Period) s).getSchedule().getName().equals(CtacssManager.currentSchedule.getName()))

            {
                timeComboBox.getItems().add(((Period) s).getEnd() + " - " + ((Period) s).getStart());
            }

        });
    }

    private void fillCourseComboBox()
    {
        courseComboBox.getItems().clear();
        courseComboBox.setPromptText("انتخاب نمایید . . .");
        CourseManager cm = new CourseManager();
        List l = cm.selectAllByFieldName(Login.loggedUserField);
        Collections.sort(l, (Course pi1, Course pi2) -> pi1.getName().compareTo(pi2.getName()));
        l.stream().forEach((s) ->
        {
            courseComboBox.getItems().add(((Course) s).getName());
        });
    }

    private void fillProfessorComboBox()
    {
        professorComboBox.getItems().clear();
        professorComboBox.setPromptText("انتخاب نمایید . . .");
        PersonManager pm = new PersonManager();
        List l = pm.selectAllByFieldName(Login.loggedUserField);
        Collections.sort(l, (Person pi1, Person pi2) -> pi1.getId().compareTo(pi2.getId()));
        l.stream().forEach((p) ->
        {
            professorComboBox.getItems().add(((Person) p).getId() + " - " + ((Person) p).getFirstName()
                    + " " + ((Person) p).getLastName());
        });
    }

    @FXML
    private void okHBoxOnMouseExited(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));

    }

    @FXML
    private void okHBoxOnMouseEntered(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonHover.png"));

    }

    @FXML
    private void cancelHBoxOnMouseExited(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButton.png"));

    }

    @FXML
    private void cancelHBoxOnMouseEntered(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonHover.png"));

    }
}

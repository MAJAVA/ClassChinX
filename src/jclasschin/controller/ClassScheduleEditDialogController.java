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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.persistence.metamodel.PluralAttribute;
import jclasschin.JClassChin;
import jclasschin.entity.Period;
import jclasschin.entity.Schedule;
import jclasschin.model.ScheduleManager;
import jclasschin.util.Effect;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassScheduleEditDialogController implements Initializable
{

    private Stage classScheduleEditDialogStage;
    private Schedule schedule;
    private ScheduleManager scheduleManager;
    private Period period;
    private ValidationSupport validationSupport;
    ArrayList<String> majavaTimes = new ArrayList<>();

    GridPane gridPane;
    int periodsNumber;
    Label[] periodTitle;
    Label[] fromLable;
    TextField[] startOfPeriodTextField;
    Label[] toLable;
    TextField[] endOfPeriodTextField;
    Tooltip timeTooltip;

    String[] oldStartOfPeriod;
    String[] oldEndOfPeriod;

    @FXML
    private ScrollPane classScheduleNewDialogScrollPane;
    @FXML
    private AnchorPane classScheduleNewDialogAnchorPane;
    @FXML
    private AnchorPane backwardSizerAnchorPane;
    @FXML
    private TextField scheduleNameTextField;
    @FXML
    private TextField periodsNumberTextField;
    @FXML
    private GridPane periodsGridPane;
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
    private void periodsNumberTextFieldOnTextChanged(InputMethodEvent event)
    {
    }

    @FXML
    private void periodsNumberTextFieldOnAction(ActionEvent event)
    {

    }

    void initDialog()
    {

        for (int i = 0; i < 24; i++)
        {
            for (int j = 0; j < 60; j++)
            {
                if (i < 10)
                {
                    if (j < 10)
                    {
                        majavaTimes.add("0" + i + ":" + "0" + j);
                    }
                    else
                    {
                        majavaTimes.add("0" + i + ":" + j);
                    }
                }
                else
                {
                    if (j < 10)
                    {
                        majavaTimes.add(i + ":" + "0" + j);
                    }
                    else
                    {
                        majavaTimes.add(i + ":" + j);
                    }
                }
            }
        }

        String css = JClassChin.class.getResource("gallery/css/CSS.css").toString();
        classScheduleNewDialogScrollPane.getStylesheets().add(css);
        timeTooltip = new Tooltip("زمان را به شیوه 00:00 وارد کنید\nبرای مثال 11:40");
        timeTooltip.setTextAlignment(TextAlignment.CENTER);

        scheduleNameTextField.setText(schedule.getName());
        periodsNumberTextField.setText(schedule.getNumberOfPeriods() + "");
        scheduleNameTextField.setDisable(true);
        periodsNumberTextField.setDisable(true);

        periodsGridPane.getChildren().clear();
        validationSupport = new ValidationSupport();

        periodsNumber = Integer.parseInt(periodsNumberTextField.getText());
        oldStartOfPeriod = new String[periodsNumber];
        oldEndOfPeriod = new String[periodsNumber];

        ArrayList<Period> p = new ArrayList<>(schedule.getPeriods());
        Collections.sort(p, (Period pi1, Period pi2) -> pi1.getId().compareTo(pi2.getId()));

        periodTitle = new Label[periodsNumber];
        fromLable = new Label[periodsNumber];
        startOfPeriodTextField = new TextField[periodsNumber];
        toLable = new Label[periodsNumber];
        endOfPeriodTextField = new TextField[periodsNumber];

        for (int i = 0; i < periodsNumber; i++)
        {
            periodTitle[i] = new Label("بازه " + (i + 1) + " :");
            fromLable[i] = new Label("از");

            startOfPeriodTextField[i] = new TextField();
            startOfPeriodTextField[i].setMaxWidth(75);
            startOfPeriodTextField[i].setPromptText("00:00");
            startOfPeriodTextField[i].setTooltip(timeTooltip);
            startOfPeriodTextField[i].setText(p.get(i).getStart());
            oldStartOfPeriod[i] = p.get(i).getStart();
            validationSupport.registerValidator(startOfPeriodTextField[i], Validator.createEqualsValidator("مقدار نامعتبر است", majavaTimes));

            toLable[i] = new Label("تا");

            endOfPeriodTextField[i] = new TextField();
            endOfPeriodTextField[i].setMaxWidth(75);
            endOfPeriodTextField[i].setPromptText("00:00");
            endOfPeriodTextField[i].setTooltip(timeTooltip);
            endOfPeriodTextField[i].setText(p.get(i).getEnd());
            oldEndOfPeriod[i] = p.get(i).getEnd();
            validationSupport.registerValidator(endOfPeriodTextField[i], Validator.createEqualsValidator("مقدار نامعتبر است", majavaTimes));

            int j = 0;
            new Effect().fadeInTransition(periodTitle[i], 500);
            periodsGridPane.add(periodTitle[i], j++, i);
            new Effect().fadeInTransition(fromLable[i], 500);
            periodsGridPane.add(fromLable[i], j++, i);
            new Effect().fadeInTransition(startOfPeriodTextField[i], 500);
            periodsGridPane.add(startOfPeriodTextField[i], j++, i);
            new Effect().fadeInTransition(toLable[i], 500);
            periodsGridPane.add(toLable[i], j++, i);
            new Effect().fadeInTransition(endOfPeriodTextField[i], 500);
            periodsGridPane.add(endOfPeriodTextField[i], j++, i);
        }
        backwardSizerAnchorPane.setPrefHeight(periodsNumber * 56.5 + 80);
    }

    /**
     * @return the classScheduleEditDialogStage
     */
    public Stage getClassScheduleEditDialogStage()
    {
        return classScheduleEditDialogStage;
    }

    /**
     * @param classScheduleEditDialogStage the classScheduleEditDialogStage to
     * set
     */
    public void setClassScheduleEditDialogStage(Stage classScheduleEditDialogStage)
    {
        this.classScheduleEditDialogStage = classScheduleEditDialogStage;
    }

    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("مقادیر بازه های زمانی را معتبر وارد نمایید.");
        }
        else
        {
            String[] startOfPeriod = new String[periodsNumber];
            String[] endOfPeriod = new String[periodsNumber];
            for (int i = 0; i < periodsNumber; i++)
            {
                startOfPeriod[i] = startOfPeriodTextField[i].getText();
                endOfPeriod[i] = endOfPeriodTextField[i].getText();
            }
            scheduleManager = new ScheduleManager();
            if (scheduleManager.update(schedule, startOfPeriod, endOfPeriod,
                    oldStartOfPeriod, oldEndOfPeriod))
            {
                MainLayoutController.statusProperty.setValue("بروز رسانی بازه های زمانی با موفقیت انجام شد.");
            }
            else
            {
                MainLayoutController.statusProperty.setValue("عملیات بروز رسانی بازه های زمانی با شکست مواجه شد.");
            }
            classScheduleEditDialogStage.close();
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات بروز رسانی بازه های زمانی لغو شد.");
        classScheduleEditDialogStage.close();
    }

    /**
     * @return the schedule
     */
    public Schedule getSchedule()
    {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Schedule schedule)
    {
        this.schedule = schedule;
    }

}

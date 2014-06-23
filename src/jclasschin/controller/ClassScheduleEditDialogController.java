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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Period;
import jclasschin.entity.Schedule;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassScheduleEditDialogController implements Initializable
{
    private Stage classScheduleEditDialogStage;
    private Schedule schedule;
    private Period period;
    
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
    }

    /**
     * @return the classScheduleEditDialogStage
     */
    public Stage getClassScheduleEditDialogStage()
    {
        return classScheduleEditDialogStage;
    }

    /**
     * @param classScheduleEditDialogStage the classScheduleEditDialogStage to set
     */
    public void setClassScheduleEditDialogStage(Stage classScheduleEditDialogStage)
    {
        this.classScheduleEditDialogStage = classScheduleEditDialogStage;
    }

    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        classScheduleEditDialogStage.close();
        
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
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

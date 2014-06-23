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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import jclasschin.JClassChin;
import jclasschin.model.Effect;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassScheduleNewDialogController implements Initializable
{
    GridPane gridPane;
    int periodsNumber;
    Label[] periodTitle;
    Label[] fromLable;
    TextField[] startOfPeriodTextField;
    Label[] toLable;
    TextField[] endOfPeriodTextField;
    Tooltip timeTooltip;

    @FXML
    private ScrollPane classScheduleNewDialogScrollPane;
    @FXML
    private AnchorPane classScheduleNewDialogAnchorPane;
    @FXML
    private TextField scheduleNameTextField;
    @FXML
    private TextField periodsNumberTextField;
    @FXML
    private GridPane periodsGridPane;
    @FXML
    private AnchorPane backwardSizerAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void initDialog()
    {
        String css = JClassChin.class.getResource("gallery/css/CSS.css").toString();
        classScheduleNewDialogScrollPane.getStylesheets().add(css);
        timeTooltip=new Tooltip("زمان را به شیوه 00:00 وارد کنید\nبرای مثال 11:40");
        timeTooltip.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    private void periodsNumberTextFieldOnTextChanged(InputMethodEvent event)
    {

    }

    @FXML
    private void periodsNumberTextFieldOnAction(ActionEvent event)
    {
        periodsGridPane.getChildren().clear();
        periodsNumber = Integer.parseInt(periodsNumberTextField.getText());

        periodTitle = new Label[periodsNumber];
        for (int i = 0; i < periodsNumber; i++)
        {
            periodTitle[i] = new Label("بازه" + (i + 1) + " :");
        }

        fromLable = new Label[periodsNumber];
        for (int i = 0; i < periodsNumber; i++)
        {
            fromLable[i] = new Label("از");
        }

        startOfPeriodTextField = new TextField[periodsNumber];
        for (int i = 0; i < periodsNumber; i++)
        {
            startOfPeriodTextField[i] = new TextField();
            startOfPeriodTextField[i].setMaxWidth(75);
            startOfPeriodTextField[i].setPromptText("00:00");
            startOfPeriodTextField[i].setTooltip(timeTooltip);
        }

        toLable = new Label[periodsNumber];
        for (int i = 0; i < periodsNumber; i++)
        {
            toLable[i] = new Label("تا");
        }

        endOfPeriodTextField = new TextField[periodsNumber];
        for (int i = 0; i < periodsNumber; i++)
        {
            endOfPeriodTextField[i] = new TextField();
            endOfPeriodTextField[i].setMaxWidth(75);
            endOfPeriodTextField[i].setPromptText("00:00");
            endOfPeriodTextField[i].setTooltip(timeTooltip);
        }

        for (int i = 0; i < periodsNumber; i++)
        {
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

        backwardSizerAnchorPane.setPrefHeight(periodsNumber * 56.5);
    }

}
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
    private ComboBox<?> professorInReportOnGourpComboBox;
    @FXML
    private Button reportOnGroupButton;
    @FXML
    private Button reportOnFieldButton;
    @FXML
    private ComboBox<?> professorInReportOnProfessorComboBox;
    @FXML
    private Button reportOnProfessorButton;
    @FXML
    private ComboBox<?> courseInReportOnCourseComboBox;
    @FXML
    private Button reportOnCourseButton;
    @FXML
    private Button reportOnClassChinButton;
    @FXML
    private ComboBox<?> courseInReportOnGourpComboBox;
    @FXML
    private ComboBox<?> fieldInReportOnFieldComboBox;

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
    }

    @FXML
    private void reportOnFieldButtonOnAction(ActionEvent event)
    {
    }

    @FXML
    private void reportOnProfessorButtonOnAction(ActionEvent event)
    {
    }

    @FXML
    private void reportOnCourseButtonOnAction(ActionEvent event)
    {
    }

    @FXML
    private void reportOnClassChinButtonOnAction(ActionEvent event)
    {
    }
    
}

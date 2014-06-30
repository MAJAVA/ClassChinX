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
package jclasschin.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Mail;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardInboxReadDialogController implements Initializable
{
    
    private Stage dashboardInboxReadDialogStage;
    private Mail mail;
    
    @FXML
    private TextArea messegeTextArea;
    @FXML
    private ComboBox<String> fromComboBox;
    @FXML
    private TextField subjectTextField;
    @FXML
    private HBox closeHBox;
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
    private void closeHboxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات خواندن نامه لغو شد.");
        dashboardInboxReadDialogStage.close();
    }

    /**
     * @return the dashboardInboxReadDialogStage
     */
    public Stage getDashboardInboxReadDialogStage()
    {
        return dashboardInboxReadDialogStage;
    }

    /**
     * @param dashboardInboxReadDialogStage the dashboardInboxReadDialogStage to
     * set
     */
    public void setDashboardInboxReadDialogStage(Stage dashboardInboxReadDialogStage)
    {
        this.dashboardInboxReadDialogStage = dashboardInboxReadDialogStage;
    }

    /**
     * @return the mail
     */
    public Mail getMail()
    {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(Mail mail)
    {
        this.mail = mail;
    }
    
    public void initDialog()
    {
        fromComboBox.getItems().clear();
        fromComboBox.setValue(mail.getPersonBySenderPersonId().getFirstName() + " " + mail.getPersonBySenderPersonId().getLastName());
        fromComboBox.setDisable(true);
        subjectTextField.setText(mail.getType());
        messegeTextArea.setText(mail.getText());
    }
    
}

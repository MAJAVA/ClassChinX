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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Mail;
import jclasschin.model.Login;
import jclasschin.model.MailManager;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author HP
 */
public class DashboardInboxReplyDialogController implements Initializable
{

    private ValidationSupport validationSupport = new ValidationSupport();
    private Stage dashboardInboxReplyMailDialogStage;
    private Mail mail;

    @FXML
    private TextArea messegeTextArea;
    @FXML
    private ComboBox<String> toComboBox;
    @FXML
    private TextField subjectTextField;
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
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("فیلد های اجباری را تکمیل فرمایید.");
        } else
        {
            MailManager mailManager = new MailManager();
            if (mailManager.insertForReply(mail.getPersonBySenderPersonId(), subjectTextField.getText(), messegeTextArea.getText()))
            {
                MainLayoutController.statusProperty.setValue("نامه شما ارسال شد!");
            } else
            {
                MainLayoutController.statusProperty.setValue("ارسال نامه با شکست مواجه شد.");
            }
            dashboardInboxReplyMailDialogStage.close();
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("پاسخ به نامه لغو شد.");
        dashboardInboxReplyMailDialogStage.close();
    }

    /**
     * @return the dashboardInboxReplyMailDialogStage
     */
    public Stage getDashboardInboxReplyMailDialogStage()
    {
        return dashboardInboxReplyMailDialogStage;
    }

    /**
     * @param dashboardInboxReplyMailDialogStage the
     * dashboardInboxReplyMailDialogStage to set
     */
    public void setDashboardInboxReplyMailDialogStage(Stage dashboardInboxReplyMailDialogStage)
    {
        this.dashboardInboxReplyMailDialogStage = dashboardInboxReplyMailDialogStage;
    }

    public void initDialog()
    {
        messegeTextArea.setText("");
        fillToComboBox();
        subjectTextField.setText("پاسخ به : " + mail.getType());

        validationSupport.registerValidator(toComboBox,
                Validator.createEmptyValidator("نام گیرنده الزامی است"));
        validationSupport.registerValidator(subjectTextField,
                Validator.createEmptyValidator("موضوع الزامی است"));
        validationSupport.registerValidator(messegeTextArea,
                Validator.createEmptyValidator("متن الزامی است"));
    }

    private void fillToComboBox()
    {
        toComboBox.getItems().clear();
        if (!Login.loggedUser.getPerson().getJob().getId().equals(1))
        {
            toComboBox.setValue("آموزش" + " @ " + "admin");
            toComboBox.setDisable(true);
        } else
        {
            toComboBox.setValue(mail.getPersonBySenderPersonId().getFirstName()
                    + " " + mail.getPersonBySenderPersonId().getLastName());
            toComboBox.setDisable(true);
        }
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

    private void okHboxOnMouseExited(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));

    }

    private void okHboxOnMouseEntered(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonHover.png"));

    }

    private void cancelHboxOnMouseExited(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButton.png"));

    }

    private void cancelHboxOnMouseEntered(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonHover.png"));

    }

    @FXML
    private void okHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void okHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void cancelHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void cancelHBoxOnMouseEntered(MouseEvent event)
    {
    }

}

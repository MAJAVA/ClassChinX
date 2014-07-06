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
import java.util.List;
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
import jclasschin.entity.User;
import jclasschin.model.Login;
import jclasschin.model.MailManager;
import jclasschin.model.UserManager;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardInboxNewDialogController implements Initializable
{

    private ValidationSupport validationSupport = new ValidationSupport();
    private Stage dashboardIboxNewDialogStage;
    private Mail mail;

    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea messegeTextArea;
    @FXML
    private ComboBox<String> toComboBox;
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
    private void okHboxOnMouseClicked(MouseEvent event)
    {

        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));
        if (!validationSupport.isInvalid())
        {
            MailManager mailManager = new MailManager();
            String[] s = toComboBox.getValue().split(" @ ");
            if (mailManager.insert(s[1], subjectTextField.getText(), messegeTextArea.getText()))
            {
                MainLayoutController.statusProperty.setValue("نامه شما ارسال شد!");
            } else
            {
                MainLayoutController.statusProperty.setValue("ارسال نامه با شکست مواجه شد.");
            }
            dashboardIboxNewDialogStage.close();
        } else
        {
            MainLayoutController.statusProperty.setValue("فیلد های الزامی را تکمیل فرمایید.");
        }
    }

    @FXML
    private void cancelHboxOnMouseClicked(MouseEvent event)
    {

        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));

        MainLayoutController.statusProperty.setValue("ارسال جدید لغو شد.");
        dashboardIboxNewDialogStage.close();
    }

    /**
     * @return the dashboardIboxNewDialogStage
     */
    public Stage getDashboardIboxNewDialogStage()
    {
        return dashboardIboxNewDialogStage;
    }

    /**
     * @param dashboardIboxNewDialogStage the dashboardIboxNewDialogStage to set
     */
    public void setDashboardIboxNewDialogStage(Stage dashboardIboxNewDialogStage)
    {
        this.dashboardIboxNewDialogStage = dashboardIboxNewDialogStage;
    }

    public void initDialog()
    {
        subjectTextField.setText("");
        subjectTextField.setPromptText("موضوع را وارد نمایید.");

        messegeTextArea.setText("");
        messegeTextArea.setPromptText("متن نامه کمتر از یکصد حرف می باشد.");
        fillToComboBox();

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
            toComboBox.setPromptText("انتخاب نمایید . . .");
            UserManager um = new UserManager();
            List userList = um.selectAll();
            userList.stream().forEach((u) ->
            {
                toComboBox.getItems().add((((User) u).getPerson().getFirstName()) + " "
                        + (((User) u).getPerson().getLastName()) + " @ "
                        + (((User) u).getUsername()));
            });
        }
    }

    @FXML
    private void okHboxOnMouseExited(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));

    }

    @FXML
    private void okHboxOnMouseEntered(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonHover.png"));

    }

    @FXML
    private void cancelHboxOnMouseExited(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButton.png"));

    }

    @FXML
    private void cancelHboxOnMouseEntered(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonHover.png"));

    }
}

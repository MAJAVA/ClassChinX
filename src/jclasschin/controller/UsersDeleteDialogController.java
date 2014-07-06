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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.User;
import jclasschin.model.UserManager;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UsersDeleteDialogController implements Initializable
{

    private final ValidationSupport validationSupport = new ValidationSupport();
    private Stage usersEditDialogStage;
    private User editableUser;
    private UserManager userManager;

    @FXML
    private HBox yesHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox noHBox;
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
    private void yesHBoxOnMouseExited(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));

    }

    @FXML
    private void yesHBoxOnMouseEntered(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonHover.png"));

    }

    @FXML
    private void yesHBoxOnMouseClicked(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));

        userManager = new UserManager();
        if (userManager.delete(editableUser.getPerson().getId()))
        {
            MainLayoutController.statusProperty.setValue("حذف کاربر با موفقیت انجام شد.");
        } else
        {
            MainLayoutController.statusProperty.setValue("عملیات حذف کاربر با شکست مواجه شد.");
        }
        usersEditDialogStage.close();
    }

    @FXML
    private void noHBoxOnMouseExited(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButton.png"));

    }

    @FXML
    private void noHBoxOnMouseEntered(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonHover.png"));

    }

    @FXML
    private void noHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("عملیات حذف کاربر لغو شد.");
        usersEditDialogStage.close();
    }

    /**
     * @return the usersEditDialogStage
     */
    public Stage getUsersEditDialogStage()
    {
        return usersEditDialogStage;
    }

    /**
     * @param usersEditDialogStage the usersEditDialogStage to set
     */
    public void setUsersEditDialogStage(Stage usersEditDialogStage)
    {
        this.usersEditDialogStage = usersEditDialogStage;
    }

    /**
     * @return the editableUser
     */
    public User getEditableUser()
    {
        return editableUser;
    }

    /**
     * @param editableUser the editableUser to set
     */
    public void setEditableUser(User editableUser)
    {
        this.editableUser = editableUser;
    }

}

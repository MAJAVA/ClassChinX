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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Field;
import jclasschin.model.FieldManager;
import jclasschin.model.Login;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FieldsEditDialogController implements Initializable
{

    private ValidationSupport validationSupport = new ValidationSupport();
    private Stage fieldsEditDialogStage;
    private Field field;
    private FieldManager fieldManager;

    @FXML
    private HBox okHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox cancelHBox;
    @FXML
    private ImageView cancelImageView;
    @FXML
    private TextField fieldNameTextField;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("نام رشته را وارد نمایید.");
        } else
        {

            fieldManager = new FieldManager();
            if (fieldManager.update(field.getId(), fieldNameTextField.getText()))
            {
                MainLayoutController.statusProperty.setValue("رشته با موفقیت بروزرسانی شد.");
            } else
            {
                MainLayoutController.statusProperty.setValue("عملیات بروز رسانی رشته با شکست مواجه شد.");
            }
            fieldsEditDialogStage.close();
        }

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

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));

        MainLayoutController.statusProperty.setValue("عملیات بروز رسانی رشته لغو شد.");
        fieldsEditDialogStage.close();
    }

    /**
     * @return the fieldsEditDialogStage
     */
    public Stage getFieldsEditDialogStage()
    {
        return fieldsEditDialogStage;
    }

    /**
     * @param fieldsEditDialogStage the fieldsEditDialogStage to set
     */
    public void setFieldsEditDialogStage(Stage fieldsEditDialogStage)
    {
        this.fieldsEditDialogStage = fieldsEditDialogStage;
    }

    /**
     * @return the field
     */
    public Field getField()
    {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field)
    {
        this.field = field;
    }

    public void initDialog()
    {
        okHBox.setDisable(false);
        fieldNameTextField.setPromptText("مهندسی نرم افزار");
        fieldNameTextField.setText(this.field.getName());
        validationSupport.registerValidator(fieldNameTextField,
                Validator.createEmptyValidator("نام رشته الزامی است"));
        if (Login.loggedUserField == null ? field.getName() == null : Login.loggedUserField.equals(field.getName()))
        {
            MainLayoutController.statusProperty.setValue("قادر به ویرایش رشته جاری خود نیستید!");
            okHBox.setDisable(true);
        }
    }

}

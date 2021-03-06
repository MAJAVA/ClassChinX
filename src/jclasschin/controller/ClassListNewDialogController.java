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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.model.ClassManager;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassListNewDialogController implements Initializable
{

    private final ValidationSupport validationSupport = new ValidationSupport();
    private Stage classListNewDialogStage;
    private ClassManager classManager;

    @FXML
    private TextField floorTextField;
    @FXML
    private TextField capacityTextField;
    @FXML
    private TextField classNameTextField;
    @FXML
    private CheckBox videoProjectorCheckBox;
    @FXML
    private CheckBox whiteBoardCheckBox;
    @FXML
    private CheckBox blackBoardCheckBox;
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
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));
        if (capacityTextField.getText() == null || "".equals(capacityTextField.getText()))
        {
            capacityTextField.setText("0");
        }
        classManager = new ClassManager();
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("لطفا نام کلاس را وارد نمایید.");
        } else
        {
            if (classManager.insert(classNameTextField.getText(), floorTextField.getText(), Integer.parseInt(capacityTextField.getText()),
                    whiteBoardCheckBox.isSelected(), blackBoardCheckBox.isSelected(), videoProjectorCheckBox.isSelected()))
            {
                MainLayoutController.statusProperty.setValue("کلاس جدید با موفقیت ثبت شد.");
            } else
            {
                MainLayoutController.statusProperty.setValue("عملیاتت درج کلاس جدید با شکست مواجه شد.");
            }
            classListNewDialogStage.close();
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("عملیات ثبت کلاس جدید لغو شد.");
        classListNewDialogStage.close();
    }

    /**
     * @return the classListNewDialogStage
     */
    public Stage getClassListNewDialogStage()
    {
        return classListNewDialogStage;
    }

    /**
     * @param classListNewDialogStage the classListNewDialogStage to set
     */
    public void setClassListNewDialogStage(Stage classListNewDialogStage)
    {
        this.classListNewDialogStage = classListNewDialogStage;
    }

    void initDialog()
    {
        classNameTextField.setText("");
        floorTextField.setText("");
        capacityTextField.setText("");
        whiteBoardCheckBox.setSelected(true);
        blackBoardCheckBox.setSelected(false);
        videoProjectorCheckBox.setSelected(false);

        validationSupport.registerValidator(classNameTextField, Validator.createEmptyValidator("نام کلاس الزامی است."));

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

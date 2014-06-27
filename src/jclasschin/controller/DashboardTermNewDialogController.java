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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jclasschin.model.TermManager;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardTermNewDialogController implements Initializable
{

    private Stage dashboardTermNewDialogStage;
    private ValidationSupport validationSupport = new ValidationSupport();

    @FXML
    private TextField termNameTextField;
    @FXML
    private HBox okHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox cancelHBox;
    @FXML
    private ImageView cancelImageView;
//    private Label programMessageLable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        TermManager termManager;

        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("لطفا نام ترم را وارد کنید.");
        }
        else
        {
            termManager = new TermManager();
            if (termManager.insert(getTermNameTextField().getText()))
            {
                MainLayoutController.statusProperty.setValue("ترم جدید با موفقیت ثبت شد!");

            }
            else
            {
                MainLayoutController.statusProperty.setValue("خطا در افزودن ترم جدید- لطفا مجددا تلاش نمایید.");
            }
            dashboardTermNewDialogStage.close();
        }

    }

    @FXML
    private void cancelHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void cancelHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات افزودن ترم جدید لغو شد.");
        dashboardTermNewDialogStage.close();
    }

    /**
     * @return the dashboardTermNewDialogStage
     */
    public Stage getDashboardTermNewDialogStage()
    {
        return dashboardTermNewDialogStage;
    }

    /**
     * @param dashboardTermNewDialogStage the dashboardTermNewDialogStage to set
     */
    public void setDashboardTermNewDialogStage(Stage dashboardTermNewDialogStage)
    {
        this.dashboardTermNewDialogStage = dashboardTermNewDialogStage;
    }

    /**
     * @return the termNameTextField
     */
    public TextField getTermNameTextField()
    {
        return termNameTextField;
    }

    public void initDialog()
    {
        termNameTextField.setPromptText("برای مثال 90-1");
        termNameTextField.setText(null);
        validationSupport.registerValidator(termNameTextField,
                Validator.createEmptyValidator("نام ترم الزامی است"));
    }
}

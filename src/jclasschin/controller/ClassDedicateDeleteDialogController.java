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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Field;
import jclasschin.model.DedicationManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassDedicateDeleteDialogController implements Initializable
{

    private Stage classDedicateDeleteDialogStage;
    private DedicationManager dedicationManager;
    private Field editableField;

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

    }

    @FXML
    private void yesHBoxOnMouseEntered(MouseEvent event)
    {

    }

    @FXML
    private void yesHBoxOnMouseClicked(MouseEvent event)
    {
        dedicationManager = new DedicationManager();
        if (dedicationManager.deleteByFieldID(editableField.getId()))
        {
            MainLayoutController.statusProperty.setValue("حذف تخصیص با موفقیت صورت گرفت.");
        }
        else
        {
            MainLayoutController.statusProperty.setValue("عملیات حذف تخصیص با شکست مواجه شد.");
        }
        classDedicateDeleteDialogStage.close();
    }

    @FXML
    private void noHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void noHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void noHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات حذف تخصیص لغو شد.");
        classDedicateDeleteDialogStage.close();
    }

    /**
     * @return the classDedicateDeleteDialogStage
     */
    public Stage getClassDedicateDeleteDialogStage()
    {
        return classDedicateDeleteDialogStage;
    }

    /**
     * @param classDedicateDeleteDialogStage the classDedicateDeleteDialogStage
     * to set
     */
    public void setClassDedicateDeleteDialogStage(Stage classDedicateDeleteDialogStage)
    {
        this.classDedicateDeleteDialogStage = classDedicateDeleteDialogStage;
    }

    /**
     * @return the dedicationManager
     */
    public DedicationManager getDedicationManager()
    {
        return dedicationManager;
    }

    /**
     * @param dedicationManager the dedicationManager to set
     */
    public void setDedicationManager(DedicationManager dedicationManager)
    {
        this.dedicationManager = dedicationManager;
    }

    /**
     * @return the editableField
     */
    public Field getEditableField()
    {
        return editableField;
    }

    /**
     * @param editableField the editableField to set
     */
    public void setEditableField(Field editableField)
    {
        this.editableField = editableField;
    }

}

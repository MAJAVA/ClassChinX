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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Classroom;
import jclasschin.model.ClassManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassListDeleteDialogController implements Initializable
{

    private Stage classListDeleteDialogStage;
    private Classroom editableClass;
    private ClassManager classManager;

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
        classManager = new ClassManager();
        if (classManager.delete(editableClass.getId()))
        {
            MainLayoutController.statusProperty.setValue("کلاس با موفقیت حذف شد.");
        } else
        {
            MainLayoutController.statusProperty.setValue("عملیات حذف کلاس با شکست مواجه شد.");
        }
        classListDeleteDialogStage.close();
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
        MainLayoutController.statusProperty.setValue("عملیات حذف کلاس لغو شد.");
        classListDeleteDialogStage.close();
    }

    void initDialog()
    {

    }

    /**
     * @return the classListDeleteDialogStage
     */
    public Stage getClassListDeleteDialogStage()
    {
        return classListDeleteDialogStage;
    }

    /**
     * @param classListDeleteDialogStage the classListDeleteDialogStage to set
     */
    public void setClassListDeleteDialogStage(Stage classListDeleteDialogStage)
    {
        this.classListDeleteDialogStage = classListDeleteDialogStage;
    }

    /**
     * @return the editableClass
     */
    public Classroom getEditableClass()
    {
        return editableClass;
    }

    /**
     * @param editableClass the editableClass to set
     */
    public void setEditableClass(Classroom editableClass)
    {
        this.editableClass = editableClass;
    }

}

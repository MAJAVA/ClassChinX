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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Course;
import jclasschin.entity.Coursetype;
import jclasschin.model.CourseManager;
import jclasschin.model.CourseTypeManager;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class GroupsCoursesEditDialogController implements Initializable
{

    private final ValidationSupport validationSupport = new ValidationSupport();
    private Stage groupsCoursesEditDialogStage;
    private Course editableCourse;

    @FXML
    private HBox okHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox cancelHBox;
    @FXML
    private ImageView cancelImageView;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<String> typeComboBox;

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
        CourseManager cm = new CourseManager();
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("لطفا فیلدهای الزامی را پر نمایید.");
        } else
        {
            if (cm.update(editableCourse.getId(), typeComboBox.getValue(), nameTextField.getText()))
            {
                MainLayoutController.statusProperty.setValue("درس با موفقیت بروز رسانی شد.");
            } else
            {
                MainLayoutController.statusProperty.setValue("عملیات بروز رسانی درس با شکست مواجه شد.");
            }
            groupsCoursesEditDialogStage.close();
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("عملیات بروز رسانی درس لغو شد.");
        groupsCoursesEditDialogStage.close();
    }

    /**
     * @return the groupsCoursesEditDialogStage
     */
    public Stage getGroupsCoursesEditDialogStage()
    {
        return groupsCoursesEditDialogStage;
    }

    /**
     * @param groupsCoursesEditDialogStage the groupsCoursesEditDialogStage to
     * set
     */
    public void setGroupsCoursesEditDialogStage(Stage groupsCoursesEditDialogStage)
    {
        this.groupsCoursesEditDialogStage = groupsCoursesEditDialogStage;
    }

    /**
     * @return the editableCourse
     */
    public Course getEditableCourse()
    {
        return editableCourse;
    }

    /**
     * @param editableCourse the editableCourse to set
     */
    public void setEditableCourse(Course editableCourse)
    {
        this.editableCourse = editableCourse;
    }

    public void initDialog()
    {
        CourseTypeManager courseTypeManager = new CourseTypeManager();
        typeComboBox.getItems().clear();
        nameTextField.setText(editableCourse.getName());

        List courseTypeList = courseTypeManager.selectAll();
        courseTypeList.stream().forEach((ct) ->
        {
            typeComboBox.getItems().add(((Coursetype) ct).getType());
        });

        typeComboBox.setValue(editableCourse.getCoursetype().getType());

        validationSupport.registerValidator(nameTextField, Validator.createEmptyValidator("عنوان درس الزامی است"));
        validationSupport.registerValidator(typeComboBox, Validator.createEmptyValidator("نوع درس الزامی است"));

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

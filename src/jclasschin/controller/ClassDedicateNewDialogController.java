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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.JClassChin;
import jclasschin.entity.Classroom;
import jclasschin.entity.Dedication;
import jclasschin.entity.Field;
import jclasschin.model.ClassManager;
import jclasschin.model.DedicationManager;
import jclasschin.model.FieldManager;
import org.controlsfx.control.CheckListView;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassDedicateNewDialogController implements Initializable
{

    private final ValidationSupport validationSupport = new ValidationSupport();
    private Stage classDedicateNewDialogStage;
    private DedicationManager dedicationManager;
    private GridPane gridPane;

    ComboBox<String> fieldComboBox = new ComboBox<>();
    Label fieldLabel = new Label("رشته :");
    Label classLabel = new Label("کلاس ها :");
    CheckListView<String> checkListView;
    ObservableList<String> selectedClass;

    @FXML
    private AnchorPane classDedicateNewDialogAnchorPane;
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
        String css = JClassChin.class.getResource("gallery/css/CSS.css").toString();
        classDedicateNewDialogAnchorPane.getStylesheets().add(css);

    }

    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        if (validationSupport.isInvalid())
        {
            MainLayoutController.statusProperty.setValue("لطفا یک رشته را انتخاب نمایید.");
        }
        else if(selectedClass==null || selectedClass.isEmpty())
        {
            MainLayoutController.statusProperty.setValue("انتخاب حداقل یک کلاس الزامی است.");
        }
        else
        {
            dedicationManager = new DedicationManager();
            
            if(dedicationManager.insert(fieldComboBox.getValue(), (List) selectedClass))
            {
                MainLayoutController.statusProperty.setValue("تخصیص با موفقیت انجام شد.");
            }
            else
            {
                MainLayoutController.statusProperty.setValue("عملیات تخصیص با شکست مواجه شد.");
            }
            classDedicateNewDialogStage.close();
        }

    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات تخصیص لغو شد.");
        classDedicateNewDialogStage.close();
    }

    /**
     * @return the classDedicateNewDialogStage
     */
    public Stage getClassDedicateNewDialogStage()
    {
        return classDedicateNewDialogStage;
    }

    /**
     * @param classDedicateNewDialogStage the classDedicateNewDialogStage to set
     */
    public void setClassDedicateNewDialogStage(Stage classDedicateNewDialogStage)
    {
        this.classDedicateNewDialogStage = classDedicateNewDialogStage;
    }

    public void initDialog()
    {
        if (classDedicateNewDialogAnchorPane.getChildren().contains(gridPane))
        {
            classDedicateNewDialogAnchorPane.getChildren().remove(1);
        }
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setLayoutX(30);
        gridPane.setLayoutY(30);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        fillFieldComboBox();
        fillClassCheckListView();

        fieldComboBox.setMinWidth(200);
        fieldComboBox.setMaxWidth(200);

        checkListView.setMinWidth(200);
        checkListView.setMaxWidth(200);

        gridPane.add(fieldLabel, 0, 0);
        gridPane.add(fieldComboBox, 1, 0);
        gridPane.add(classLabel, 0, 1);
        gridPane.add(checkListView, 1, 1);
        classDedicateNewDialogAnchorPane.getChildren().add(gridPane);
        classDedicateNewDialogAnchorPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        validationSupport.registerValidator(fieldComboBox, Validator.createEmptyValidator("نام رشته الزامی است."));

    }

    private void fillFieldComboBox()
    {
        fieldComboBox.getItems().clear();
        fieldComboBox.setPromptText("انتخاب نمایید . . .");
        FieldManager fieldManager = new FieldManager();
        List fl = fieldManager.selectAllNotDedicatedField();
        fl.stream().forEach((f) ->
        {
            fieldComboBox.getItems().add(((Field) f).getName());
        });
    }

    private void fillClassCheckListView()
    {
        ObservableList<String> classList = FXCollections.observableArrayList();
        ClassManager classManager = new ClassManager();
        List cl = classManager.selectAll();
        Collections.sort(cl, (Classroom pi1, Classroom pi2) -> pi1.getName().compareTo(pi2.getName()));
        
        cl.stream().forEach((c) ->
        {
            classList.add(((Classroom) c).getName());
        });
        checkListView = new CheckListView<>(classList);
        checkListView.getCheckModel().clearSelection();
        checkListView.getCheckModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) ->
        {
            selectedClass = (ObservableList<String>) c.getList();
        });
    }

}

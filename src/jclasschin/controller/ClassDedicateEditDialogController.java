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
import java.util.ArrayList;
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
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassDedicateEditDialogController implements Initializable
{
    
    private Stage classDedicateEditDialogStage;
    private DedicationManager dedicationManager;
    private Field editableField;
    private GridPane gridPane;
    ComboBox<String> fieldComboBox = new ComboBox<>();
    Label fieldLabel = new Label("رشته :");
    Label classLabel = new Label("کلاس ها :");
    CheckListView<String> checkListView;
    ObservableList<String> selectedClass;
    ArrayList<String> oldClass = new ArrayList<>();
    
    @FXML
    private AnchorPane classDedicateEditDialogAnchorPane;
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
        classDedicateEditDialogAnchorPane.getStylesheets().add(css);
    }
    
    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        if (selectedClass == null || selectedClass.isEmpty())
        {
            MainLayoutController.statusProperty.setValue("انتخاب حداقل یک کلاس الزامی است.");
        }
        else
        {
            dedicationManager = new DedicationManager();
            if (dedicationManager.update(editableField.getId(), fieldComboBox.getValue(), (List) selectedClass))
            {
                MainLayoutController.statusProperty.setValue("بروزرسانی تخصیص با موفقیت انجام گرفت.");
            }
            else
            {
                MainLayoutController.statusProperty.setValue("بروز رسانی تخصیص با شکست مواجه شد.");
            }
            classDedicateEditDialogStage.close();
        }
    }
    
    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات بروزرسانی تخصیص لغو شد.");
        classDedicateEditDialogStage.close();
    }

    /**
     * @return the classDedicateNewDialogStage
     */
    public Stage getClassDedicateEditDialogStage()
    {
        return classDedicateEditDialogStage;
    }

    /**
     * @param classDedicateEditDialogStage the classDedicateNewDialogStage to
     * set
     */
    public void setClassDedicateEditDialogStage(Stage classDedicateEditDialogStage)
    {
        this.classDedicateEditDialogStage = classDedicateEditDialogStage;
    }

    /**
     * @return the deitableField
     */
    public Field getEditableField()
    {
        return editableField;
    }

    /**
     * @param eitableField the deitableField to set
     */
    public void setEditableField(Field eitableField)
    {
        this.editableField = eitableField;
    }
    
    void initDialog()
    {
        
        if (classDedicateEditDialogAnchorPane.getChildren().contains(gridPane))
        {
            classDedicateEditDialogAnchorPane.getChildren().remove(1);
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
        classDedicateEditDialogAnchorPane.getChildren().add(gridPane);
        classDedicateEditDialogAnchorPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
    }
    
    private void fillFieldComboBox()
    {
        fieldComboBox.setValue(editableField.getName());
        fieldComboBox.setDisable(true);
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
        
        editableField.getDedications().stream().forEach((d) ->
        {
            checkListView.getCheckModel().select(((Dedication) d).getClassroom().getName());
            oldClass.add(((Dedication) d).getClassroom().getName());
        });
        
        checkListView.getCheckModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) ->
        {
            selectedClass = (ObservableList<String>) c.getList();
        });
    }
}

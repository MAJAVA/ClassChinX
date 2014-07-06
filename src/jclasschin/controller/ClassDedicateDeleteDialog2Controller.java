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
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.JClassChin;
import jclasschin.entity.Dedication;
import jclasschin.entity.Field;
import jclasschin.model.DedicationManager;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ClassDedicateDeleteDialog2Controller implements Initializable
{

    private Stage classDedicateDeleteDialog2Stage;
    private DedicationManager dedicationManager;
    private Field editableField;
    private GridPane gridPane;
    ComboBox<String> fieldComboBox = new ComboBox<>();
    Label fieldLabel = new Label("رشته :");
    Label classLabel = new Label("کلاس ها :");
    CheckListView<String> checkListView;
    ObservableList<String> selectedClass;
    ArrayList<String> oldClass;

    @FXML
    private AnchorPane classDedicateDeleteDialog2AnchorPane;
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
        String css = JClassChin.class.getResource("gallery/css/CSS.css").toString();
        classDedicateDeleteDialog2AnchorPane.getStylesheets().add(css);
    }

    @FXML
    private void okHBoxOnMouseClicked(MouseEvent event)
    {
        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));
        if (selectedClass == null || selectedClass.isEmpty())
        {
            MainLayoutController.statusProperty.setValue("انتخاب حداقل یک کلاس الزامی است.");
        } else
        {
            dedicationManager = new DedicationManager();
            if (dedicationManager.deleteAListOfDedications(fieldComboBox.getValue(), (List) selectedClass))
            {
                MainLayoutController.statusProperty.setValue("حذف تخصیصات با موفقیت انجام گرفت.");
            } else
            {
                MainLayoutController.statusProperty.setValue("حذف تخصیصات با شکست مواجه شد.");
            }
            classDedicateDeleteDialog2Stage.close();
        }
    }

    @FXML
    private void cancelHBoxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/image/cancelButtonActive.png"));
        MainLayoutController.statusProperty.setValue("عملیات حذف تخصیصات لغو شد.");
        classDedicateDeleteDialog2Stage.close();
    }

    /**
     * @return the classDedicateNewDialogStage
     */
    public Stage getClassDedicateDeleteDialog2Stage()
    {
        return classDedicateDeleteDialog2Stage;
    }

    /**
     * @param classDedicateDeleteDialog2Stage the classDedicateNewDialogStage to
     * set
     */
    public void setClassDedicateDeleteDialog2Stage(Stage classDedicateDeleteDialog2Stage)
    {
        this.classDedicateDeleteDialog2Stage = classDedicateDeleteDialog2Stage;
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
        selectedClass = FXCollections.observableArrayList();

        if (classDedicateDeleteDialog2AnchorPane.getChildren().contains(gridPane))
        {
            classDedicateDeleteDialog2AnchorPane.getChildren().remove(1);
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
        classDedicateDeleteDialog2AnchorPane.getChildren().add(gridPane);
        classDedicateDeleteDialog2AnchorPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

    }

    private void fillFieldComboBox()
    {
        fieldComboBox.setValue(editableField.getName());
        fieldComboBox.setDisable(true);
    }

    private void fillClassCheckListView()
    {
        oldClass = new ArrayList<>();
        String[] oc = editableField.majava1String.split(" - ");
        oldClass.addAll(Arrays.asList(oc));
        Collections.sort(oldClass, (String pi1, String pi2) -> pi1.compareTo(pi2));
        ObservableList<String> classList = FXCollections.observableArrayList();

        oldClass.stream().forEach((c) ->
        {
            classList.add((String) c);
        });
        checkListView = new CheckListView<>(classList);

        checkListView.getCheckModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) ->
        {
            selectedClass = (ObservableList<String>) c.getList();
        });
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

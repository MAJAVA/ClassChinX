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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jclasschin.JClassChin;
import jclasschin.entity.Classroom;
import jclasschin.entity.Dedication;
import jclasschin.entity.Field;
import jclasschin.entity.Schedule;
import jclasschin.model.ClassManager;
import jclasschin.model.CtacssManager;
import jclasschin.model.FieldManager;
import jclasschin.model.ScheduleManager;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ClassLayoutController implements Initializable
{

    private final FXMLLoader classListNewDialogLoader, classListEditDialogLoader,
            classListDeleteDialogLoader, classDedicateNewDialogLoader, classDedicateEditDialogLoader,
            classDedicateDeleteDialogLoader, classDedicateDeleteDialog2Loader, classScheduleNewDialogLoader, classScheduleEditDialogLoader,
            classScheduleDeleteDialogLoader;

    private final AnchorPane classListNewDialogLayout, classListEditDialogLayout,
            classListDeleteDialogLayout, classDedicateNewDialogLayout, classDedicateEditDialogLayout,
            classDedicateDeleteDialogLayout, classDedicateDeleteDialog2Layout, classScheduleDeleteDialogLayout;

    private final ScrollPane classScheduleNewDialogLayout, classScheduleEditDialogLayout;

    private final Scene classListNewDialogScene, classListEditDialogScene,
            classListDeleteDialogScene, classDedicateNewDialogScene, classDedicateEditDialogScene,
            classDedicateDeleteDialogScene, classDedicateDeleteDialog2Scene, classScheduleNewDialogScene, classScheduleEditDialogScene,
            classScheduleDeleteDialogScene;

    private final Stage classListNewDialogStage, classListEditDialogStage,
            classListDeleteDialogStage, classDedicateNewDialogStage, classDedicateEditDialogStage,
            classDedicateDeleteDialogStage, classDedicateDeleteDialog2Stage, classScheduleNewDialogStage, classScheduleEditDialogStage,
            classScheduleDeleteDialogStage;

    private final ClassListNewDialogController classListNewDialogController;
    private final ClassListEditDialogController classListEditDialogController;
    private final ClassListDeleteDialogController classListDeleteDialogController;
    private final ClassDedicateNewDialogController classDedicateNewDialogController;
    private final ClassDedicateEditDialogController classDedicateEditDialogController;
    private final ClassDedicateDeleteDialogController classDedicateDeleteDialogController;
    private final ClassDedicateDeleteDialog2Controller classDedicateDeleteDialog2Controller;
    private final ClassScheduleNewDialogController classScheduleNewDialogController;
    private final ClassScheduleEditDialogController classScheduleEditDialogController;
    private final ClassScheduleDeleteDialogController classScheduleDeleteDialogController;

    @FXML
    private TableView<Classroom> classTableView;
    @FXML
    private TableColumn<Classroom, Integer> classIdTableColumn;
    @FXML
    private TableColumn<Classroom, String> classNameTableColumn;
    @FXML
    private TableColumn<Classroom, String> floorTableColumn;
    @FXML
    private TableColumn<Classroom, Integer> capacityTableColumn;
    @FXML
    private TableColumn<Classroom, Boolean> videoProjectTableColumn;
    @FXML
    private TableColumn<Classroom, Boolean> whiteBoardTableColumn;
    @FXML
    private TableColumn<Classroom, Boolean> blackBoardTableColumn;

    @FXML
    private TableView<Field> dedicationTableView;
    @FXML
    private TableColumn<Field, Integer> dIdTableColumn;
    @FXML
    private TableColumn<Field, String> dFieldTableColumn;
    @FXML
    private TableColumn<Field, Integer> dClassNumberTableColumn;
    @FXML
    private TableColumn<Field, String> dClassListTableColumn;

    @FXML
    private TableView<Schedule> scheduleTableView;
    @FXML
    private TableColumn<Schedule, Integer> schIdTableColumn;
    @FXML
    private TableColumn<Schedule, String> schNameTableColumn;
    @FXML
    private TableColumn<Schedule, Integer> schNumberOfPeriodTableColumn;
    @FXML
    private TableColumn<Schedule, String> schPeriodsTableColumn;
    @FXML
    private ComboBox<String> currentScheduleComboBox;
    @FXML
    private HBox refreshHBox;
    @FXML
    private ImageView classNewImageView;
    @FXML
    private ImageView classEditImageView;
    @FXML
    private ImageView classDeleteImageView;
    @FXML
    private ImageView dedicationNewImageView;
    @FXML
    private ImageView dedicationEditImageView;
    @FXML
    private ImageView dedicationDeleteImageView;
    @FXML
    private ImageView scheduleNewImageView;
    @FXML
    private ImageView scheduleEditImageView;
    @FXML
    private ImageView scheduleDeleteImageView;
    @FXML
    private ImageView scheduleRefreshImageView;

    public ClassLayoutController() throws IOException
    {

        /* Class New Dialog */
        classListNewDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassListNewDialog.fxml"));
        classListNewDialogLayout = (AnchorPane) classListNewDialogLoader.load();
        classListNewDialogScene = new Scene(classListNewDialogLayout);
        classListNewDialogStage = new Stage();
        classListNewDialogStage.setScene(classListNewDialogScene);
        classListNewDialogStage.setTitle("کلاس جدید");
        classListNewDialogStage.initModality(Modality.WINDOW_MODAL);
        classListNewDialogStage.initOwner(JClassChin.getMainStage());
        classListNewDialogStage.setResizable(false);
        classListNewDialogStage.initStyle(StageStyle.UTILITY);

        classListNewDialogController = classListNewDialogLoader.getController();

        /* Class Edit Dialog   */
        classListEditDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassListEditDialog.fxml"));
        classListEditDialogLayout = (AnchorPane) classListEditDialogLoader.load();
        classListEditDialogScene = new Scene(classListEditDialogLayout);
        classListEditDialogStage = new Stage();
        classListEditDialogStage.setScene(classListEditDialogScene);
        classListEditDialogStage.setTitle("ویرایش کلاس");
        classListEditDialogStage.initModality(Modality.WINDOW_MODAL);
        classListEditDialogStage.initOwner(JClassChin.getMainStage());
        classListEditDialogStage.setResizable(false);
        classListEditDialogStage.initStyle(StageStyle.UTILITY);

        classListEditDialogController = classListEditDialogLoader.getController();

        /* Class Delete Dialog   */
        classListDeleteDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassListDeleteDialog.fxml"));
        classListDeleteDialogLayout = (AnchorPane) classListDeleteDialogLoader.load();
        classListDeleteDialogScene = new Scene(classListDeleteDialogLayout);
        classListDeleteDialogStage = new Stage();
        classListDeleteDialogStage.setScene(classListDeleteDialogScene);
        classListDeleteDialogStage.setTitle("حذف کلاس");
        classListDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        classListDeleteDialogStage.initOwner(JClassChin.getMainStage());
        classListDeleteDialogStage.setResizable(false);
        classListDeleteDialogStage.initStyle(StageStyle.UTILITY);

        classListDeleteDialogController = classListDeleteDialogLoader.getController();

        /* Dedicate New Dialog */
        classDedicateNewDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassDedicateNewDialog.fxml"));
        classDedicateNewDialogLayout = (AnchorPane) classDedicateNewDialogLoader.load();
        classDedicateNewDialogScene = new Scene(classDedicateNewDialogLayout);
        classDedicateNewDialogStage = new Stage();
        classDedicateNewDialogStage.setScene(classDedicateNewDialogScene);
        classDedicateNewDialogStage.setTitle("تخصیص جدید");
        classDedicateNewDialogStage.initModality(Modality.WINDOW_MODAL);
        classDedicateNewDialogStage.initOwner(JClassChin.getMainStage());
        classDedicateNewDialogStage.setResizable(false);
        classDedicateNewDialogStage.initStyle(StageStyle.UTILITY);

        classDedicateNewDialogController = classDedicateNewDialogLoader.getController();

        /* Dedicate Edit Dialog */
        classDedicateEditDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassDedicateEditDialog.fxml"));
        classDedicateEditDialogLayout = (AnchorPane) classDedicateEditDialogLoader.load();
        classDedicateEditDialogScene = new Scene(classDedicateEditDialogLayout);
        classDedicateEditDialogStage = new Stage();
        classDedicateEditDialogStage.setScene(classDedicateEditDialogScene);
        classDedicateEditDialogStage.setTitle("ویرایش تخصیص");
        classDedicateEditDialogStage.initModality(Modality.WINDOW_MODAL);
        classDedicateEditDialogStage.initOwner(JClassChin.getMainStage());
        classDedicateEditDialogStage.setResizable(false);
        classDedicateEditDialogStage.initStyle(StageStyle.UTILITY);

        classDedicateEditDialogController = classDedicateEditDialogLoader.getController();

        /* Dedicate Delete Dialog */
        classDedicateDeleteDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassDedicateDeleteDialog.fxml"));
        classDedicateDeleteDialogLayout = (AnchorPane) classDedicateDeleteDialogLoader.load();
        classDedicateDeleteDialogScene = new Scene(classDedicateDeleteDialogLayout);
        classDedicateDeleteDialogStage = new Stage();
        classDedicateDeleteDialogStage.setScene(classDedicateDeleteDialogScene);
        classDedicateDeleteDialogStage.setTitle("حذف تخصیص");
        classDedicateDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        classDedicateDeleteDialogStage.initOwner(JClassChin.getMainStage());
        classDedicateDeleteDialogStage.setResizable(false);
        classDedicateDeleteDialogStage.initStyle(StageStyle.UTILITY);

        classDedicateDeleteDialogController = classDedicateDeleteDialogLoader.getController();

        /* Dedicate Delete Dialog 2 */
        classDedicateDeleteDialog2Loader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassDedicateDeleteDialog2.fxml"));
        classDedicateDeleteDialog2Layout = (AnchorPane) classDedicateDeleteDialog2Loader.load();
        classDedicateDeleteDialog2Scene = new Scene(classDedicateDeleteDialog2Layout);
        classDedicateDeleteDialog2Stage = new Stage();
        classDedicateDeleteDialog2Stage.setScene(classDedicateDeleteDialog2Scene);
        classDedicateDeleteDialog2Stage.setTitle("حذف تخصیص");
        classDedicateDeleteDialog2Stage.initModality(Modality.WINDOW_MODAL);
        classDedicateDeleteDialog2Stage.initOwner(JClassChin.getMainStage());
        classDedicateDeleteDialog2Stage.setResizable(false);
        classDedicateDeleteDialog2Stage.initStyle(StageStyle.UTILITY);

        classDedicateDeleteDialog2Controller = classDedicateDeleteDialog2Loader.getController();

        /* Schedule New Dialog */
        classScheduleNewDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassScheduleNewDialog.fxml"));
        classScheduleNewDialogLayout = (ScrollPane) classScheduleNewDialogLoader.load();
        classScheduleNewDialogScene = new Scene(classScheduleNewDialogLayout);
        classScheduleNewDialogStage = new Stage();
        classScheduleNewDialogStage.setScene(classScheduleNewDialogScene);
        classScheduleNewDialogStage.setTitle("زمان بندی جدید");
        classScheduleNewDialogStage.initModality(Modality.WINDOW_MODAL);
        classScheduleNewDialogStage.initOwner(JClassChin.getMainStage());
        classScheduleNewDialogStage.setResizable(false);
        classScheduleNewDialogStage.initStyle(StageStyle.UTILITY);

        classScheduleNewDialogController = classScheduleNewDialogLoader.getController();

        /* Schedule Edit Dialog */
        classScheduleEditDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassScheduleEditDialog.fxml"));
        classScheduleEditDialogLayout = (ScrollPane) classScheduleEditDialogLoader.load();
        classScheduleEditDialogScene = new Scene(classScheduleEditDialogLayout);
        classScheduleEditDialogStage = new Stage();
        classScheduleEditDialogStage.setScene(classScheduleEditDialogScene);
        classScheduleEditDialogStage.setTitle("ویرایش زمان بندی");
        classScheduleEditDialogStage.initModality(Modality.WINDOW_MODAL);
        classScheduleEditDialogStage.initOwner(JClassChin.getMainStage());
        classScheduleEditDialogStage.setResizable(false);
        classScheduleEditDialogStage.initStyle(StageStyle.UTILITY);

        classScheduleEditDialogController = classScheduleEditDialogLoader.getController();

        /* Schedule Delete Dialog */
        classScheduleDeleteDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/ClassScheduleDeleteDialog.fxml"));
        classScheduleDeleteDialogLayout = (AnchorPane) classScheduleDeleteDialogLoader.load();
        classScheduleDeleteDialogScene = new Scene(classScheduleDeleteDialogLayout);
        classScheduleDeleteDialogStage = new Stage();
        classScheduleDeleteDialogStage.setScene(classScheduleDeleteDialogScene);
        classScheduleDeleteDialogStage.setTitle("حذف زمان بندی");
        classScheduleDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        classScheduleDeleteDialogStage.initOwner(JClassChin.getMainStage());
        classScheduleDeleteDialogStage.setResizable(false);
        classScheduleDeleteDialogStage.initStyle(StageStyle.UTILITY);

        classScheduleDeleteDialogController = classScheduleDeleteDialogLoader.getController();

    }

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        classTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        dedicationTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        scheduleTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
    }

    @FXML
    private void newClassHBoxOnMouseClicked(MouseEvent event)
    {
        classNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));
        classListNewDialogController.setClassListNewDialogStage(classListNewDialogStage);
        classListNewDialogController.initDialog();
        classListNewDialogStage.showAndWait();

        updateClassListTableView();
    }

    @FXML
    private void editClassHBoxOnMouseClicked(MouseEvent event)
    {
        classEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));

        if (classTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Classroom c = classTableView.getSelectionModel().getSelectedItem();
            classListEditDialogController.setClassEditDialogStage(classListEditDialogStage);
            classListEditDialogController.setEditableClass(c);
            classListEditDialogController.initDialog();
            classListEditDialogStage.showAndWait();
            updateClassListTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک کلاس را انتخاب نمایید.");
        }

    }

    @FXML
    private void deleteClassHBoxOnMouseClicked(MouseEvent event)
    {
        classDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));

        if (classTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Classroom c = classTableView.getSelectionModel().getSelectedItem();
            classListDeleteDialogController.setClassListDeleteDialogStage(classListDeleteDialogStage);
            classListDeleteDialogController.setEditableClass(c);
            classListDeleteDialogStage.showAndWait();

            updateClassListTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک کلاس را انتخاب نمایید.");
        }

    }

    @FXML
    private void newDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        dedicationNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        classDedicateNewDialogController.setClassDedicateNewDialogStage(classDedicateNewDialogStage);
        classDedicateNewDialogController.initDialog();
        classDedicateNewDialogStage.showAndWait();
        updateDedicationTableView();
    }

    @FXML
    private void editDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        dedicationEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));

        if (dedicationTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Field f = dedicationTableView.getSelectionModel().getSelectedItem();
            classDedicateEditDialogController.setClassDedicateEditDialogStage(classDedicateEditDialogStage);
            classDedicateEditDialogController.setEditableField(f);
            classDedicateEditDialogController.initDialog();
            classDedicateEditDialogStage.showAndWait();
            updateDedicationTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("ابتدا یک تخصیص را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        dedicationDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));

        if (dedicationTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Field f = dedicationTableView.getSelectionModel().getSelectedItem();
            classDedicateDeleteDialog2Controller.setClassDedicateDeleteDialog2Stage(classDedicateDeleteDialog2Stage);
            classDedicateDeleteDialog2Controller.setEditableField(f);
            classDedicateDeleteDialog2Controller.initDialog();
            classDedicateDeleteDialog2Stage.showAndWait();
            updateDedicationTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("ابتدا یک تخصیص را انتخاب نمایید.");
        }
    }

    public void updateClassListTableView()
    {
        ClassManager cm = new ClassManager();
        ObservableList<Classroom> classList = FXCollections.observableArrayList();
        classIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        classNameTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getName()));
        floorTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getFloor()));
        capacityTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, Integer> c) -> new ReadOnlyObjectWrapper(c.getValue().getCapacity()));
        videoProjectTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, Boolean> c) -> new ReadOnlyObjectWrapper(c.getValue().getDataProjector() ? "دارد" : "ندارد"));
        whiteBoardTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, Boolean> c) -> new ReadOnlyObjectWrapper(c.getValue().getWhiteboard() ? "دارد" : "ندارد"));
        blackBoardTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Classroom, Boolean> c) -> new ReadOnlyObjectWrapper(c.getValue().getBlackboard() ? "دارد" : "ندارد"));
        List l = cm.selectAll();
        l.stream().forEach((c) ->
        {
            classList.add((Classroom) c);
        });
        classTableView.setItems(classList);
    }

    public void updateDedicationTableView()
    {
        FieldManager fieldManager = new FieldManager();

        List dField = fieldManager.selectAllDedicatedField();
        dField.stream().forEach((df) ->
        {
            ((Field) df).initMajava();
        });

        ObservableList<Field> fieldList = FXCollections.observableArrayList();

        dIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dFieldTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, String> f) -> new ReadOnlyObjectWrapper(f.getValue().getName()));
        dClassNumberTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, Integer> f)
                -> new ReadOnlyObjectWrapper(f.getValue().numberOfDedicationClass));
        dClassListTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, String> f)
                -> new ReadOnlyObjectWrapper(f.getValue().majava1String));

        dField.stream().forEach((df) ->
        {
            ArrayList<Dedication> al = new ArrayList<>(((Field) df).getDedications());
            if (!"".equals(((Field) df).majava1String))
            {
                fieldList.add((Field) df);
            }
        });
        // dedicationTableView.getItems().clear();
        dedicationTableView.setItems(fieldList);

    }

    @FXML
    private void newScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        scheduleNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        classScheduleNewDialogController.setClassScheduleNewDialogStage(classScheduleNewDialogStage);
        classScheduleNewDialogController.initDialog();
        classScheduleNewDialogStage.showAndWait();
        updateScheduleTableView();

    }

    @FXML
    private void editScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        scheduleEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));

        if (scheduleTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Schedule s = scheduleTableView.getSelectionModel().getSelectedItem();
            classScheduleEditDialogController.setClassScheduleEditDialogStage(classScheduleEditDialogStage);
            classScheduleEditDialogController.setSchedule(s);
            classScheduleEditDialogController.initDialog();
            classScheduleEditDialogStage.showAndWait();
            updateScheduleTableView();

        } else
        {
            MainLayoutController.statusProperty.setValue("ابتدا یک دوره زمانی را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        scheduleDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));

        if (scheduleTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Schedule s = scheduleTableView.getSelectionModel().getSelectedItem();
            classScheduleDeleteDialogController.setClassScheduleDeleteDialogStage(classScheduleDeleteDialogStage);
            classScheduleDeleteDialogController.setSchedule(s);
            //classScheduleDeleteDialogController.initDialog();
            classScheduleDeleteDialogStage.showAndWait();
            updateScheduleTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("ابتدا یک دوره زمانی را انتخاب نمایید.");
        }

    }

    public void updateScheduleTableView()
    {
        currentScheduleComboBox.getItems().clear();
        ScheduleManager scheduleManager = new ScheduleManager();
        List l = scheduleManager.selectAllSchedule();
        l.stream().forEach((p) ->
        {
            ((Schedule) p).initMajava();
        });

        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

        schIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        schNameTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Schedule, String> s) -> new ReadOnlyObjectWrapper(s.getValue().getName()));
        schNumberOfPeriodTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Schedule, Integer> s)
                -> new ReadOnlyObjectWrapper(s.getValue().getNumberOfPeriods()));
        schPeriodsTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Schedule, String> s)
                -> new ReadOnlyObjectWrapper(s.getValue().majava2String));

        l.stream().forEach((s) ->
        {
            scheduleList.add((Schedule) s);
            currentScheduleComboBox.getItems().add(((Schedule) s).getName());
        });
        scheduleTableView.setItems(scheduleList);
        new CtacssManager().initCurrentSchedule();
        currentScheduleComboBox.setValue(CtacssManager.currentSchedule.getName());
        MainLayoutController.currentScheduleProperty.setValue(currentScheduleComboBox.getValue());
    }

    @FXML
    private void classScheduleTabOnSelectionChanged(Event event)
    {
    }

    @FXML
    private void refreshHBoxMouseExited(MouseEvent event)
    {
        scheduleRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButton.png"));

    }

    @FXML
    private void refreshHBoxMouseEntered(MouseEvent event)
    {
        scheduleRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonHover.png"));

    }

    @FXML
    private void refreshHBoxOnMouseClicked(MouseEvent event)
    {
        scheduleRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonActive.png"));
        if (CtacssManager.currentSchedule.getName() == null ? currentScheduleComboBox.getValue() != null : !CtacssManager.currentSchedule.getName().equals(currentScheduleComboBox.getValue()))
        {
            CtacssManager cm = new CtacssManager();
            cm.updateCurrentSchedule(currentScheduleComboBox.getValue());
            MainLayoutController.statusProperty.setValue("برنامه جاری سیستم بروز شد.");
            MainLayoutController.currentScheduleProperty.setValue(currentScheduleComboBox.getValue());
        }
    }

    @FXML
    private void newClassHBoxOnMouseExited(MouseEvent event)
    {
        classNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));

    }

    @FXML
    private void newClassHBoxOnMouseEntered(MouseEvent event)
    {
        classNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void editClassHBoxOnMouseExited(MouseEvent event)
    {
        classEditImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void editClassHBoxOnMouseEntered(MouseEvent event)
    {
        classEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void deleteClassHBoxOnMouseExited(MouseEvent event)
    {
        classDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteClassHBoxOnMouseEntered(MouseEvent event)
    {
        classDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }

    @FXML
    private void newDedicateHBoxOnMouseExited(MouseEvent event)
    {
        dedicationNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));

    }

    @FXML
    private void newDedicateHBoxOnMouseEntered(MouseEvent event)
    {
        dedicationNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void editDedicateHBoxOnMouseExited(MouseEvent event)
    {
        dedicationEditImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void editDedicateHBoxOnMouseEntered(MouseEvent event)
    {
        dedicationEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void deleteDedicateHBoxOnMouseExited(MouseEvent event)
    {
        dedicationDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteDedicateHBoxOnMouseEntered(MouseEvent event)
    {
        dedicationDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }

    @FXML
    private void newScheduleHBoxOnMouseExited(MouseEvent event)
    {
        scheduleNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));

    }

    @FXML
    private void newScheduleHBoxOnMouseEntered(MouseEvent event)
    {
        scheduleNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void editScheduleHBoxOnMouseExited(MouseEvent event)
    {
        scheduleEditImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void editScheduleHBoxOnMouseEntered(MouseEvent event)
    {
        scheduleEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void deleteScheduleHBoxOnMouseExited(MouseEvent event)
    {
        scheduleDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteScheduleHBoxOnMouseEntered(MouseEvent event)
    {
        scheduleDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }
}

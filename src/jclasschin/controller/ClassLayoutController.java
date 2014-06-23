package jclasschin.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jclasschin.JClassChin;
import jclasschin.entity.Classroom;
import jclasschin.entity.Course;
import jclasschin.entity.Dedication;
import jclasschin.entity.Field;
import jclasschin.model.ClassManager;
import jclasschin.model.CourseManager;
import jclasschin.model.FieldManager;
import jclasschin.model.Login;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ClassLayoutController implements Initializable
{

    private final FXMLLoader classListNewDialogLoader, classListEditDialogLoader,
            classListDeleteDialogLoader, classDedicateNewDialogLoader, classDedicateEditDialogLoader,
            classDedicateDeleteDialogLoader, classScheduleNewDialogLoader, classScheduleEditDialogLoader,
            classScheduleDeleteDialogLoader;

    private final AnchorPane classListNewDialogLayout, classListEditDialogLayout,
            classListDeleteDialogLayout, classDedicateNewDialogLayout, classDedicateEditDialogLayout,
            classDedicateDeleteDialogLayout, classScheduleDeleteDialogLayout;

    private final ScrollPane classScheduleNewDialogLayout, classScheduleEditDialogLayout;

    private final Scene classListNewDialogScene, classListEditDialogScene,
            classListDeleteDialogScene, classDedicateNewDialogScene, classDedicateEditDialogScene,
            classDedicateDeleteDialogScene, classScheduleNewDialogScene, classScheduleEditDialogScene,
            classScheduleDeleteDialogScene;

    private final Stage classListNewDialogStage, classListEditDialogStage,
            classListDeleteDialogStage, classDedicateNewDialogStage, classDedicateEditDialogStage,
            classDedicateDeleteDialogStage, classScheduleNewDialogStage, classScheduleEditDialogStage,
            classScheduleDeleteDialogStage;

    private final ClassListNewDialogController classListNewDialogController;
    private final ClassListEditDialogController classListEditDialogController;
    private final ClassListDeleteDialogController classListDeleteDialogController;
    private final ClassDedicateNewDialogController classDedicateNewDialogController;
    private final ClassDedicateEditDialogController classDedicateEditDialogController;
    private final ClassDedicateDeleteDialogController classDedicateDeleteDialogController;
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
    private TableColumn<Field, String[]> dClassListTableColumn;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void newClassHBoxOnMouseClicked(MouseEvent event)
    {
        classListNewDialogController.setClassListNewDialogStage(classListNewDialogStage);
        classListNewDialogController.initDialog();
        classListNewDialogStage.showAndWait();

        updateClassListTableView();
    }

    @FXML
    private void editClassHBoxOnMouseClicked(MouseEvent event)
    {

        if (classTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Classroom c = classTableView.getSelectionModel().getSelectedItem();
            classListEditDialogController.setClassEditDialogStage(classListEditDialogStage);
            classListEditDialogController.setEditableClass(c);
            classListEditDialogController.initDialog();
            classListEditDialogStage.showAndWait();
            updateClassListTableView();
        }

    }

    @FXML
    private void deleteClassHBoxOnMouseClicked(MouseEvent event)
    {

        if (classTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Classroom c = classTableView.getSelectionModel().getSelectedItem();
            classListDeleteDialogController.setClassListDeleteDialogStage(classListDeleteDialogStage);
            classListDeleteDialogController.setEditableClass(c);
            classListDeleteDialogStage.showAndWait();

            updateClassListTableView();
        }
    }

    @FXML
    private void newDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        classDedicateNewDialogController.setClassDedicateNewDialogStage(classDedicateNewDialogStage);
        classDedicateNewDialogController.initDialog();
        classDedicateNewDialogStage.showAndWait();
        updateDedicationTableView();
    }

    @FXML
    private void editDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        if (dedicationTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Field f = dedicationTableView.getSelectionModel().getSelectedItem();
            classDedicateEditDialogController.setClassDedicateEditDialogStage(classDedicateEditDialogStage);
            classDedicateEditDialogController.setEditableField(f);
            classDedicateEditDialogController.initDialog();
            classDedicateEditDialogStage.showAndWait();
            updateDedicationTableView();
        }
    }

    @FXML
    private void deleteDedicateHBoxOnMouseClicked(MouseEvent event)
    {
        if (dedicationTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Field f = dedicationTableView.getSelectionModel().getSelectedItem();
            classDedicateDeleteDialogController.setClassDedicateDeleteDialogStage(classDedicateDeleteDialogStage);
            classDedicateDeleteDialogController.setEditableField(f);
            classDedicateDeleteDialogStage.showAndWait();
            updateDedicationTableView();
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
        ArrayList<Dedication> al = new ArrayList();
        ArrayList<String> al2 = new ArrayList();
        ArrayList<String> al3 = new ArrayList();
        al2.clear();
        al3.clear();
        al3.add(0, " ");

        dField.stream().forEach((f) ->
        {
            al.addAll(((Field) f).getDedications());
        });

        dField.stream().forEach((f) ->
        {
            al.stream().forEach((d) ->
            {
                al2.add(d.getClassroom().getName());
            });
        });

        String string = "";
        for (String al21 : al2)
        {
            string += al21;
            string += " , ";
        }
        al3.set(0, string);
        System.out.println(string);

        ObservableList<Field> fieldList = FXCollections.observableArrayList();

        dIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dFieldTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, String> f) -> new ReadOnlyObjectWrapper(f.getValue().getName()));
        dClassNumberTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, Integer> f)
                -> new ReadOnlyObjectWrapper(f.getValue().getDedications().size()));
        dClassListTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Field, String[]> f)
                -> new ReadOnlyObjectWrapper(f.getValue().getDedications()));

        dField.stream().forEach((f) ->
        {
            fieldList.add((Field) f);
        });
        dedicationTableView.setItems(fieldList);

    }

@FXML
    private void newScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        classScheduleNewDialogController.initDialog();
        classScheduleNewDialogStage.showAndWait();

    }

    @FXML
    private void editScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        classScheduleEditDialogController.initDialog();
        classScheduleEditDialogStage.showAndWait();
    }

    @FXML
    private void deleteScheduleHBoxOnMouseClicked(MouseEvent event)
    {
        classScheduleDeleteDialogStage.showAndWait();

    }


}

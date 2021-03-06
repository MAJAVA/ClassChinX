/*
 * Last edit by Morteza
 */
package jclasschin.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
import jclasschin.entity.Course;
import jclasschin.entity.Person;
import jclasschin.entity.User;
import jclasschin.model.CourseManager;
import jclasschin.model.Login;
import jclasschin.model.PersonManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class GroupsLayoutController implements Initializable
{

    private final FXMLLoader profNewDialogLoader, profEditDialogLoader,
            profDeleteDialogLoader, courseNewDialogLoader,
            courseEditDialogLoader, courseDeleteDialogLoader;
    private final AnchorPane profNewDialogLayout, profEditDialogLayout,
            profDeleteDialogLayout, courseNewDialogLayout,
            courseEditDialogLayout, courseDeleteDialogLayout;
    private final Scene profNewDialogScene, profEditDialogScene,
            profDeleteDialogScene, courseNewDialogScene,
            courseEditDialogScene, courseDeleteDialogScene;
    private final Stage profNewDialogStage, profEditDialogStage,
            profDeleteDialogStage, courseNewDialogStage,
            courseEditDialogStage, courseDeleteDialogStage;

    private GroupsProferssorsNewDialogController profNewDialogController;
    private GroupsProferssorsEditDialogController profEditDialogController;
    private GroupsProferssorsDeleteDialogController profDeleteDialogController;

    private GroupsCoursesNewDialogController courseNewDialogController;
    private GroupsCoursesEditDialogController courseEditDialogController;
    private GroupsCoursesDeleteDialogController courseDeleteDialogController;

    @FXML
    private HBox profNewHBox;
    @FXML
    private TableView<Person> profTableView;
    @FXML
    private TableColumn<Person, String> profIdTableColumn;
    @FXML
    private TableColumn<Person, String> profFirstAndLastNameTableColumn;
    @FXML
    private TableColumn<Person, String> profFieldTableColumn;
    @FXML
    private TableColumn<Person, String> profPhoneTableColumn;

    @FXML
    private TableView<Course> courseTableView;
    @FXML
    private TableColumn<Course, String> idTableColumn;
    @FXML
    private TableColumn<Course, String> nameTableColumn;
    @FXML
    private TableColumn<Course, String> typeTableColumn;
    @FXML
    private TableColumn<Course, String> fieldTableColumn;
    @FXML
    private ImageView newImageView;
    @FXML
    private ImageView editImageView;
    @FXML
    private ImageView deleteImageView;
    @FXML
    private ImageView profNewImageView;
    @FXML
    private ImageView profEditImageView;
    @FXML
    private ImageView profDeleteImageView;

    public GroupsLayoutController() throws IOException
    {

        /* Professor New Dialog */
        profNewDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsProferssorsNewDialog.fxml"));
        profNewDialogLayout = (AnchorPane) profNewDialogLoader.load();
        profNewDialogScene = new Scene(profNewDialogLayout);
        profNewDialogStage = new Stage();
        profNewDialogStage.setScene(profNewDialogScene);
        profNewDialogStage.setTitle("استاد جدید");
        profNewDialogStage.initModality(Modality.WINDOW_MODAL);
        profNewDialogStage.initOwner(JClassChin.getMainStage());
        profNewDialogStage.setResizable(false);
        profNewDialogStage.initStyle(StageStyle.UTILITY);

        /* Professor Edit Dialog */
        profEditDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsProferssorsEditDialog.fxml"));
        profEditDialogLayout = (AnchorPane) profEditDialogLoader.load();
        profEditDialogScene = new Scene(profEditDialogLayout);
        profEditDialogStage = new Stage();
        profEditDialogStage.setScene(profEditDialogScene);
        profEditDialogStage.setTitle("ویرایش استاد");
        profEditDialogStage.initModality(Modality.WINDOW_MODAL);
        profEditDialogStage.initOwner(JClassChin.getMainStage());
        profEditDialogStage.setResizable(false);
        profEditDialogStage.initStyle(StageStyle.UTILITY);

        /* Professor Delete Dialog */
        profDeleteDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsProferssorsDeleteDialog.fxml"));
        profDeleteDialogLayout = (AnchorPane) profDeleteDialogLoader.load();
        profDeleteDialogScene = new Scene(profDeleteDialogLayout);
        profDeleteDialogStage = new Stage();
        profDeleteDialogStage.setScene(profDeleteDialogScene);
        profDeleteDialogStage.setTitle("حذف استاد");
        profDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        profDeleteDialogStage.initOwner(JClassChin.getMainStage());
        profDeleteDialogStage.setResizable(false);
        profDeleteDialogStage.initStyle(StageStyle.UTILITY);

        /* Courses New Dialog */
        courseNewDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsCoursesNewDialog.fxml"));
        courseNewDialogLayout = (AnchorPane) courseNewDialogLoader.load();
        courseNewDialogScene = new Scene(courseNewDialogLayout);
        courseNewDialogStage = new Stage();
        courseNewDialogStage.setScene(courseNewDialogScene);
        courseNewDialogStage.setTitle("واحد درسی جدید");
        courseNewDialogStage.initModality(Modality.WINDOW_MODAL);
        courseNewDialogStage.initOwner(JClassChin.getMainStage());
        courseNewDialogStage.setResizable(false);
        courseNewDialogStage.initStyle(StageStyle.UTILITY);

        /* Courses Edit Dialog */
        courseEditDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsCoursesEditDialog.fxml"));
        courseEditDialogLayout = (AnchorPane) courseEditDialogLoader.load();
        courseEditDialogScene = new Scene(courseEditDialogLayout);
        courseEditDialogStage = new Stage();
        courseEditDialogStage.setScene(courseEditDialogScene);
        courseEditDialogStage.setTitle("ویرایش واحد درسی");
        courseEditDialogStage.initModality(Modality.WINDOW_MODAL);
        courseEditDialogStage.initOwner(JClassChin.getMainStage());
        courseEditDialogStage.setResizable(false);
        courseEditDialogStage.initStyle(StageStyle.UTILITY);

        /* Courses Delete Dialog */
        courseDeleteDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/GroupsCoursesDeleteDialog.fxml"));
        courseDeleteDialogLayout = (AnchorPane) courseDeleteDialogLoader.load();
        courseDeleteDialogScene = new Scene(courseDeleteDialogLayout);
        courseDeleteDialogStage = new Stage();
        courseDeleteDialogStage.setScene(courseDeleteDialogScene);
        courseDeleteDialogStage.setTitle("حذف واحد درسی");
        courseDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        courseDeleteDialogStage.initOwner(JClassChin.getMainStage());
        courseDeleteDialogStage.setResizable(false);
        courseDeleteDialogStage.initStyle(StageStyle.UTILITY);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        profTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        courseTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));

    }

    @FXML
    private void newHBoxOnMouseClicked(MouseEvent event)
    {
        newImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));
        courseNewDialogController = courseNewDialogLoader.getController();
        courseNewDialogController.setGroupsCoursesNewDialog(courseNewDialogStage);
        courseNewDialogController.initDialog();
        courseNewDialogStage.showAndWait();
        updateCourseTableView();

    }

    @FXML
    private void editHBoxOnMouseClicked(MouseEvent event)
    {
        editImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));
        if (courseTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Course c = courseTableView.getSelectionModel().getSelectedItem();
            courseEditDialogController = courseEditDialogLoader.getController();
            courseEditDialogController.initialize(null, null);
            courseEditDialogController.setGroupsCoursesEditDialogStage(courseEditDialogStage);
            courseEditDialogController.setEditableCourse(c);
            courseEditDialogController.initDialog();
            courseEditDialogStage.showAndWait();
            updateCourseTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک درس را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteHBoxOnMouseClicked(MouseEvent event)
    {
        deleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (courseTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Course c = courseTableView.getSelectionModel().getSelectedItem();
            courseDeleteDialogController = courseDeleteDialogLoader.getController();
            courseDeleteDialogController.initialize(null, null);
            courseDeleteDialogController.setGroupsCoursesDeleteDialogStage(courseDeleteDialogStage);
            courseDeleteDialogController.setEditableCourse(c);
            courseDeleteDialogStage.showAndWait();
            updateCourseTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک درس را انتخاب نمایید.");
        }
    }

    @FXML
    private void profNewHBoxOnMouseClicked(MouseEvent event)
    {
        profNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        profNewDialogController = profNewDialogLoader.getController();
        profNewDialogController.setGroupProferssorsNewDialogStage(profNewDialogStage);
        profNewDialogController.initDialog();
        profNewDialogStage.showAndWait();
        updateProfTableView();
    }

    @FXML
    private void profEditHBoxOnMouseClicked(MouseEvent event)
    {
        profEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));
        if (profTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Person person = profTableView.getSelectionModel().getSelectedItem();
            profEditDialogController = profEditDialogLoader.getController();
            profEditDialogController.setGroupProferssorsEditDialogStage(profEditDialogStage);
            profEditDialogController.setEditablePerson(person);
            profEditDialogController.initDialog();
            profEditDialogStage.showAndWait();
            updateProfTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک استاد را انتخاب نمایید.");
        }
    }

    @FXML
    private void profDeleteHBoxOnMouseClicked(MouseEvent event)
    {
        profDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (profTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Person person = profTableView.getSelectionModel().getSelectedItem();
            profDeleteDialogController = profDeleteDialogLoader.getController();
            profDeleteDialogController.setGroupProferssorsDeleteDialogStage(profDeleteDialogStage);
            profDeleteDialogController.setEditablePerson(person);
            profDeleteDialogStage.showAndWait();
            updateProfTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک استاد را انتخاب نمایید.");
        }
    }

    public void updateProfTableView()
    {
        PersonManager pm = new PersonManager();

        ObservableList<Person> personList = FXCollections.observableArrayList();
        profIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        profFirstAndLastNameTableColumn.setCellValueFactory((CellDataFeatures<Person, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getFirstName() + " " + p.getValue().getLastName()));
        profFieldTableColumn.setCellValueFactory((CellDataFeatures<Person, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getField().getName()));
        profPhoneTableColumn.setCellValueFactory((CellDataFeatures<Person, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getPhone()));
        List l = pm.selectAllByFieldName(Login.loggedUserField);
        l.stream().forEach((p) ->
        {
            personList.add((Person) p);
        });
        profTableView.setItems(personList);
    }

    public void updateCourseTableView()
    {

        CourseManager cm = new CourseManager();
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory((CellDataFeatures<Course, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getName()));
        typeTableColumn.setCellValueFactory((CellDataFeatures<Course, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getCoursetype().getType()));
        fieldTableColumn.setCellValueFactory((CellDataFeatures<Course, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getField().getName()));
        List l = cm.selectAllByFieldName(Login.loggedUserField);
        l.stream().forEach((c) ->
        {
            courseList.add((Course) c);
        });
        courseTableView.setItems(courseList);
    }

    @FXML
    private void profNewHBoxOnMouseExited(MouseEvent event)
    {
        profNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));

    }

    @FXML
    private void profNewHBoxOnMouseEntered(MouseEvent event)
    {
        profNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void profEditHBoxOnMouseExited(MouseEvent event)
    {
        profEditImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void profEditHBoxOnMouseEntered(MouseEvent event)
    {
        profEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void profDeleteHBoxOnMouseExited(MouseEvent event)
    {
        profDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void profDeleteHBoxOnMouseEntered(MouseEvent event)
    {
        profDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }

    @FXML
    private void newHBoxOnMouseExited(MouseEvent event)
    {
        newImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));

    }

    @FXML
    private void newHBoxOnMouseEntered(MouseEvent event)
    {
        newImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void editHBoxOnMouseExited(MouseEvent event)
    {
        editImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void editHBoxOnMouseEntered(MouseEvent event)
    {
        editImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void deleteHBoxOnMouseExited(MouseEvent event)
    {
        deleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteHBoxOnMouseEntered(MouseEvent event)
    {
        deleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }

}

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
import jclasschin.entity.User;
import jclasschin.model.UserManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class UsersLayoutController implements Initializable
{

    private final FXMLLoader usersNewDialogLoader, usersEditDialogLoader, usersDeleteDialogLoader;
    private final AnchorPane usersNewDialogLayout, usersEditDialogLayout, usersDeleteDialogLayout;
    private final Scene usersNewDialogScene, usersEditDialogScene, usersDeleteDialogScene;
    private final Stage usersNewDialogStage, usersEditDialogStage, usersDeleteDialogStage;

    private UsersNewDialogController usersNewDialogController;
    private UsersEditDialogController usersEditDialogController;
    private UsersDeleteDialogController usersDeleteDialogController;

    @FXML
    private HBox newHBox;
    @FXML
    private HBox editHBox;
    @FXML
    private HBox deleteHBox;
    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<User, String> idTableColumn;
    @FXML
    private TableColumn<User, String> nameTableColumn;
    @FXML
    private TableColumn<User, String> fieldTableColumn;
    @FXML
    private TableColumn<User, String> jobTableColumn;
    @FXML
    private TableColumn<User, String> stateTableColumn;
    @FXML
    private ImageView newImageView;
    @FXML
    private ImageView editImageView;
    @FXML
    private ImageView deleteImageView;

    public UsersLayoutController() throws IOException
    {

        /*  New User */
        usersNewDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/UsersNewDialog.fxml"));
        usersNewDialogLayout = (AnchorPane) usersNewDialogLoader.load();
        usersNewDialogScene = new Scene(usersNewDialogLayout);
        usersNewDialogStage = new Stage();
        usersNewDialogStage.setScene(usersNewDialogScene);
        usersNewDialogStage.setTitle(" کاربر جدید");
        usersNewDialogStage.initModality(Modality.WINDOW_MODAL);
        usersNewDialogStage.initOwner(JClassChin.getMainStage());
        usersNewDialogStage.setResizable(false);
        usersNewDialogStage.initStyle(StageStyle.UTILITY);

        /* Edit User */
        usersEditDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/UsersEditDialog.fxml"));
        usersEditDialogLayout = (AnchorPane) usersEditDialogLoader.load();
        usersEditDialogScene = new Scene(usersEditDialogLayout);
        usersEditDialogStage = new Stage();
        usersEditDialogStage.setScene(usersEditDialogScene);
        usersEditDialogStage.setTitle("ویرایش کاربر");
        usersEditDialogStage.initModality(Modality.WINDOW_MODAL);
        usersEditDialogStage.initOwner(JClassChin.getMainStage());
        usersEditDialogStage.setResizable(false);
        usersEditDialogStage.initStyle(StageStyle.UTILITY);

        /*  Delete User */
        usersDeleteDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/UsersDeleteDialog.fxml"));
        usersDeleteDialogLayout = (AnchorPane) usersDeleteDialogLoader.load();
        usersDeleteDialogScene = new Scene(usersDeleteDialogLayout);
        usersDeleteDialogStage = new Stage();
        usersDeleteDialogStage.setScene(usersDeleteDialogScene);
        usersDeleteDialogStage.setTitle("حذف کاربر");
        usersDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        usersDeleteDialogStage.initOwner(JClassChin.getMainStage());
        usersDeleteDialogStage.setResizable(false);
        usersDeleteDialogStage.initStyle(StageStyle.UTILITY);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        usersTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
    }

    @FXML
    private void newHBoxOnMouseClicked(MouseEvent event)
    {
        newImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));
        usersNewDialogController = usersNewDialogLoader.getController();
        usersNewDialogController.setUsersNewDialogStage(usersNewDialogStage);
        usersNewDialogController.initDialog();
        usersNewDialogStage.showAndWait();

        updateUsersTableView();
    }

    @FXML
    private void editHBoxOnMouseClicked(MouseEvent event)
    {
        editImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));
        if (usersTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            User u = usersTableView.getSelectionModel().getSelectedItem();

            usersEditDialogController = usersEditDialogLoader.getController();
            usersEditDialogController.initialize(null, null);
            usersEditDialogController.setUsersEditDialogStage(usersEditDialogStage);
            usersEditDialogController.setEditableUser(u);
            usersEditDialogController.initDialog();
            usersEditDialogStage.showAndWait();

            updateUsersTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک کاربر را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteHBoxOnMouseClicked(MouseEvent event)
    {
        deleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (usersTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            User u = usersTableView.getSelectionModel().getSelectedItem();

            usersDeleteDialogController = usersDeleteDialogLoader.getController();
            usersDeleteDialogController.initialize(null, null);
            usersDeleteDialogController.setUsersEditDialogStage(usersDeleteDialogStage);
            usersDeleteDialogController.setEditableUser(u);

            usersDeleteDialogStage.showAndWait();

            updateUsersTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک کاربر را انتخاب نمایید.");
        }
    }

    public void updateUsersTableView()
    {

        UserManager um = new UserManager();
        ObservableList<User> userList = FXCollections.observableArrayList();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory((CellDataFeatures<User, String> u) -> new ReadOnlyObjectWrapper(u.getValue().getPerson().getFirstName() + " " + u.getValue().getPerson().getLastName()));
        fieldTableColumn.setCellValueFactory((CellDataFeatures<User, String> u) -> new ReadOnlyObjectWrapper(u.getValue().getPerson().getField().getName()));
        jobTableColumn.setCellValueFactory((CellDataFeatures<User, String> u) -> new ReadOnlyObjectWrapper(u.getValue().getPerson().getJob().getTitle()));
        stateTableColumn.setCellValueFactory((CellDataFeatures<User, String> u) -> new ReadOnlyObjectWrapper(u.getValue().isState() ? "فعال" : "غیر فعال"));

        //fieldTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        List l = um.selectAll();
        l.stream().forEach((u) ->
        {
            userList.add((User) u);

        });

        usersTableView.setItems(userList);
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

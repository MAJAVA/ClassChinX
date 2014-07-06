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
/*
 * Complete fxids  and event functions on 1393-02-28 by Morteza!
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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
import jclasschin.entity.Mail;
import jclasschin.entity.Status;
import jclasschin.entity.Term;
import jclasschin.model.CtacssManager;
import jclasschin.model.Login;
import jclasschin.model.MailManager;
import jclasschin.model.StatusManager;
import jclasschin.model.TermManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class DashboardLayoutController implements Initializable
{

    private final FXMLLoader dashboardInboxReadMailDialogLoader, dashboardOutboxReadMailDialogLoader, dashboardInboxNewMailDialogLoader, dashboardInboxReplyMailDialogLoader,
            dashboardInboxDeleteMailDialogLoader, dashboardOutboxDeleteMailDialogLoader, dashboardTermNewDailogLoader,
            dashboardTermEditDailogLoader, dashboardTermDeleteDailogLoader;
    private final AnchorPane dashboardInboxReadMailDialogLayout, dashboardOutboxReadMailDialogLayout, dashboardInboxNewMailDialogLayout, dashboardInboxReplyMailDialogLayout,
            dashboardInboxDeleteMailDialogLayout, dashboardOutboxDeleteMailDialogLayout, dashboardTermNewDailogLayout,
            dashboardTermEditDailogLayout, dashboardTermDeleteDailogLayout;
    private final Scene dashboardInboxReadMailDialogScene, dashboardOutboxReadMailDialogScene, dashboardInboxNewMailDialogScene, dashboardInboxReplyMailDialogScene,
            dashboardInboxDeleteMailDialogScene, dashboardOutboxDeleteMailDialogScene, dashboardTermNewDailogScene,
            dashboardTermEditDailogScene, dashboardTermDeleteDailogScene;
    private final Stage dashboardInboxReadMailDialogStage, dashboardOutboxReadMailDialogStage, dashboardInboxNewMailDialogStage, dashboardInboxReplyMailDialogStage,
            dashboardInboxDeleteMailDialogStage, dashboardOutboxDeleteMailDialogStage, dashboardTermNewDailogStage,
            dashboardTermEditDailogStage, dashboardTermDeleteDailogStage;


    /* Mail Inbox */
    private DashboardInboxNewDialogController dashboardInboxNewMailDialogController;
    private DashboardInboxReplyDialogController dashboardInboxReplyMailDialogController;
    private DashboardInboxDeleteDialogController dashboardInboxDeleteMailDialogController;

    private DashboardInboxReadDialogController dashboardInboxReadMailDialogController;
    private DashboardOutboxReadDialogController dashboardOutboxReadMailDialogController;

    /* Mail Outbox */
    private DashboardOutboxDeleteDialogController dashboardOutboxDeleteMailDialogController;


    /* Status */
    /* Term */
    private DashboardTermNewDialogController dashboardTermNewDialogController;
    private DashboardTermEditDialogController dashboardTermEditDialogController;
    private DashboardTermDeleteDialogController dashboardTermDeleteDialogController;

    @FXML
    private AnchorPane dashboardAnchorPane;
    @FXML
    private HBox newHBox;
    @FXML
    private HBox replyHBox;
    @FXML
    private HBox deleteHBox;
    @FXML
    private HBox refreshHBox;
    @FXML
    private HBox new2HBox;
    @FXML
    private HBox delete2HBox;
    @FXML
    private HBox refresh2HBox;
    @FXML
    private HBox newTermHBox;
    @FXML
    private HBox editTermHBox;
    @FXML
    private HBox deleteTermHBox;
    @FXML
    private ComboBox<String> currentTermComboBox;
    @FXML
    private TableView<Mail> inboxTableView;
    @FXML
    private TableView<Mail> outboxTableView;
    @FXML
    private TableColumn<Mail, String> subjectTableColumn;
    @FXML
    private TableColumn<Mail, String> messegeTableColumn;
    @FXML
    private TableView<Term> termTableView;
    @FXML
    private TableColumn<Term, String> termIdTableColumn;
    @FXML
    private TableColumn<Term, String> termNameTableColumn;

    @FXML
    private TableColumn<Mail, Integer> idTableColumn;
    @FXML
    private TableColumn<Mail, String> senderTableColumn;
    @FXML
    private TableColumn<Mail, String> dateTableColumn;
    @FXML
    private TableColumn<Mail, Integer> outboxIdTableColumn;
    @FXML
    private TableColumn<Mail, String> outboxReceiverTableColumn;
    @FXML
    private TableColumn<Mail, String> outboxSubjectTableColumn;
    @FXML
    private TableColumn<Mail, String> outboxDateTableColumn;
    @FXML
    private TableColumn<Mail, String> outboxMessegeTableColumn;

    @FXML
    private TableView<Status> statusTableView;
    @FXML
    private TableColumn<Status, Integer> statusIdTableColumn;
    @FXML
    private TableColumn<Status, String> statusFieldTableColumn;
    @FXML
    private TableColumn<Status, String> statusLastUpdateTableColumn;
    @FXML
    private TableColumn<Status, Boolean> statusStateTableColumn;
    @FXML
    private Tab inboxTab;
    @FXML
    private Tab outboxTab;
    @FXML
    private Tab statusTab;
    @FXML
    private Tab termTab;
    @FXML
    private HBox refresh3HBox;
    @FXML
    private HBox refresh4HBox;
    @FXML
    private HBox readHBox;
    @FXML
    private HBox readHBox2;
    @FXML
    private ImageView inboxReadMailImageView;
    @FXML
    private ImageView inboxNewImageView;
    @FXML
    private ImageView inboxReplyImageView;
    @FXML
    private ImageView inboxDeleteImageView;
    @FXML
    private ImageView inboxRefreshImageView;
    @FXML
    private ImageView outboxReadMailImageView;
    @FXML
    private ImageView outboxNewImageView;
    @FXML
    private ImageView outboxDeleteImageView;
    @FXML
    private ImageView outboxRefreshImageView;
    @FXML
    private ImageView statusRefreshImageView;
    @FXML
    private ImageView termNewImageView;
    @FXML
    private ImageView termEditImageView;
    @FXML
    private ImageView termDeleteImageView;
    @FXML
    private ImageView termRefreshImageView;

    public DashboardLayoutController() throws IOException
    {
        /* Inbox Read Dialog   */
        dashboardInboxReadMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardInboxReadDialog.fxml"));
        dashboardInboxReadMailDialogLayout = (AnchorPane) dashboardInboxReadMailDialogLoader.load();
        dashboardInboxReadMailDialogScene = new Scene(dashboardInboxReadMailDialogLayout);
        dashboardInboxReadMailDialogStage = new Stage();
        dashboardInboxReadMailDialogStage.setScene(dashboardInboxReadMailDialogScene);
        dashboardInboxReadMailDialogStage.setTitle("خواندن نامه");
        dashboardInboxReadMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardInboxReadMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardInboxReadMailDialogStage.setResizable(false);
        dashboardInboxReadMailDialogStage.initStyle(StageStyle.UTILITY);
        dashboardInboxReadMailDialogController = dashboardInboxReadMailDialogLoader.getController();

        /* Inbox New Dialog   */
        dashboardInboxNewMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardInboxNewDialog.fxml"));
        dashboardInboxNewMailDialogLayout = (AnchorPane) dashboardInboxNewMailDialogLoader.load();
        dashboardInboxNewMailDialogScene = new Scene(dashboardInboxNewMailDialogLayout);
        dashboardInboxNewMailDialogStage = new Stage();
        dashboardInboxNewMailDialogStage.setScene(dashboardInboxNewMailDialogScene);
        dashboardInboxNewMailDialogStage.setTitle("نامه جدید");
        dashboardInboxNewMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardInboxNewMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardInboxNewMailDialogStage.setResizable(false);
        dashboardInboxNewMailDialogStage.initStyle(StageStyle.UTILITY);


        /* Inbox Reply Dialog */
        dashboardInboxReplyMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardInboxReplyDialog.fxml"));
        dashboardInboxReplyMailDialogLayout = (AnchorPane) dashboardInboxReplyMailDialogLoader.load();
        dashboardInboxReplyMailDialogScene = new Scene(dashboardInboxReplyMailDialogLayout);
        dashboardInboxReplyMailDialogStage = new Stage();
        dashboardInboxReplyMailDialogStage.setScene(dashboardInboxReplyMailDialogScene);
        dashboardInboxReplyMailDialogStage.setTitle("پاسخ به نامه");
        dashboardInboxReplyMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardInboxReplyMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardInboxReplyMailDialogStage.setResizable(false);
        dashboardInboxReplyMailDialogStage.initStyle(StageStyle.UTILITY);


        /* Inbox Delete Dialog */
        dashboardInboxDeleteMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardInboxDeleteDialog.fxml"));
        dashboardInboxDeleteMailDialogLayout = (AnchorPane) dashboardInboxDeleteMailDialogLoader.load();
        dashboardInboxDeleteMailDialogScene = new Scene(dashboardInboxDeleteMailDialogLayout);
        dashboardInboxDeleteMailDialogStage = new Stage();
        dashboardInboxDeleteMailDialogStage.setScene(dashboardInboxDeleteMailDialogScene);
        dashboardInboxDeleteMailDialogStage.setTitle("حذف نامه دریافتی");
        dashboardInboxDeleteMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardInboxDeleteMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardInboxDeleteMailDialogStage.setResizable(false);
        dashboardInboxDeleteMailDialogStage.initStyle(StageStyle.UTILITY);

        /* Outbox Read Dialog   */
        dashboardOutboxReadMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardOutboxReadDialog.fxml"));
        dashboardOutboxReadMailDialogLayout = (AnchorPane) dashboardOutboxReadMailDialogLoader.load();
        dashboardOutboxReadMailDialogScene = new Scene(dashboardOutboxReadMailDialogLayout);
        dashboardOutboxReadMailDialogStage = new Stage();
        dashboardOutboxReadMailDialogStage.setScene(dashboardOutboxReadMailDialogScene);
        dashboardOutboxReadMailDialogStage.setTitle("خواندن نامه");
        dashboardOutboxReadMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardOutboxReadMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardOutboxReadMailDialogStage.setResizable(false);
        dashboardOutboxReadMailDialogStage.initStyle(StageStyle.UTILITY);
        dashboardOutboxReadMailDialogController = dashboardOutboxReadMailDialogLoader.getController();

        /* Outbox Delete Dialog */
        dashboardOutboxDeleteMailDialogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardOutboxDeleteDialog.fxml"));
        dashboardOutboxDeleteMailDialogLayout = (AnchorPane) dashboardOutboxDeleteMailDialogLoader.load();
        dashboardOutboxDeleteMailDialogScene = new Scene(dashboardOutboxDeleteMailDialogLayout);
        dashboardOutboxDeleteMailDialogStage = new Stage();
        dashboardOutboxDeleteMailDialogStage.setScene(dashboardOutboxDeleteMailDialogScene);
        dashboardOutboxDeleteMailDialogStage.setTitle("حذف نامه ارسالی");
        dashboardOutboxDeleteMailDialogStage.initModality(Modality.WINDOW_MODAL);
        dashboardOutboxDeleteMailDialogStage.initOwner(JClassChin.getMainStage());
        dashboardOutboxDeleteMailDialogStage.setResizable(false);
        dashboardOutboxDeleteMailDialogStage.initStyle(StageStyle.UTILITY);


        /* Term New Dialog */
        dashboardTermNewDailogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardTermNewDialog.fxml"));
        dashboardTermNewDailogLayout = (AnchorPane) dashboardTermNewDailogLoader.load();
        dashboardTermNewDailogScene = new Scene(dashboardTermNewDailogLayout);
        dashboardTermNewDailogStage = new Stage();
        dashboardTermNewDailogStage.setScene(dashboardTermNewDailogScene);
        dashboardTermNewDailogStage.setTitle("ترم جدید");
        dashboardTermNewDailogStage.initModality(Modality.WINDOW_MODAL);
        dashboardTermNewDailogStage.initOwner(JClassChin.getMainStage());
        dashboardTermNewDailogStage.setResizable(false);
        dashboardTermNewDailogStage.initStyle(StageStyle.UTILITY);


        /* Term Edit Dialog */
        dashboardTermEditDailogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardTermEditDialog.fxml"));
        dashboardTermEditDailogLayout = (AnchorPane) dashboardTermEditDailogLoader.load();
        dashboardTermEditDailogScene = new Scene(dashboardTermEditDailogLayout);
        dashboardTermEditDailogStage = new Stage();
        dashboardTermEditDailogStage.setScene(dashboardTermEditDailogScene);
        dashboardTermEditDailogStage.setTitle("ویرایش ترم");
        dashboardTermEditDailogStage.initModality(Modality.WINDOW_MODAL);
        dashboardTermEditDailogStage.initOwner(JClassChin.getMainStage());
        dashboardTermEditDailogStage.setResizable(false);
        dashboardTermEditDailogStage.initStyle(StageStyle.UTILITY);


        /* Term Delete Dialog */
        dashboardTermDeleteDailogLoader
                = new FXMLLoader(JClassChin.class.getResource("view/DashboardTermDeleteDialog.fxml"));
        dashboardTermDeleteDailogLayout = (AnchorPane) dashboardTermDeleteDailogLoader.load();
        dashboardTermDeleteDailogScene = new Scene(dashboardTermDeleteDailogLayout);
        dashboardTermDeleteDailogStage = new Stage();
        dashboardTermDeleteDailogStage.setScene(dashboardTermDeleteDailogScene);
        dashboardTermDeleteDailogStage.setTitle("حذف ترم");
        dashboardTermDeleteDailogStage.initModality(Modality.WINDOW_MODAL);
        dashboardTermDeleteDailogStage.initOwner(JClassChin.getMainStage());
        dashboardTermDeleteDailogStage.setResizable(false);
        dashboardTermDeleteDailogStage.initStyle(StageStyle.UTILITY);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inboxTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        outboxTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        termTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
        statusTableView.setPlaceholder(new Label("تا کنون داده اي ثبت نشده است"));
    }

    //__________________________________________________________________________
    // INBOX
    //__________________________________________________________________________
    @FXML
    private void readHBoxOnMouseExited(MouseEvent event)
    {
        inboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButton.png"));

    }

    @FXML
    private void readHBoxOnMouseEntered(MouseEvent event)
    {
        inboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButtonHover.png"));
    }

    @FXML
    private void readHBoxOnMouseClicked(MouseEvent event)
    {
        inboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButtonActive.png"));
        if (inboxTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Mail m = inboxTableView.getSelectionModel().getSelectedItem();
            dashboardInboxReadMailDialogController.setDashboardInboxReadDialogStage(dashboardInboxReadMailDialogStage);
            dashboardInboxReadMailDialogController.setMail(m);
            dashboardInboxReadMailDialogController.initDialog();
            dashboardInboxReadMailDialogStage.showAndWait();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک نامه را انتخاب نمایید.");
        }
    }

    @FXML
    private void newHBoxOnMouseExited(MouseEvent event)
    {
        inboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));
    }

    @FXML
    private void newHBoxOnMouseEntered(MouseEvent event)
    {
        inboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));
    }

    @FXML
    private void newHBoxOnMouseClicked(MouseEvent event)
    {
        inboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        dashboardInboxNewMailDialogController = dashboardInboxNewMailDialogLoader.getController();
        dashboardInboxNewMailDialogController.initialize(null, null);
        dashboardInboxNewMailDialogController.setDashboardIboxNewDialogStage(dashboardInboxNewMailDialogStage);
        dashboardInboxNewMailDialogController.initDialog();
        dashboardInboxNewMailDialogStage.showAndWait();

        updateInboxTableView();
        updateOutboxTableView();
    }

    @FXML
    private void replyHBoxOnMouseExited(MouseEvent event)
    {
        inboxReplyImageView.setImage(new Image("jclasschin/gallery/image/replyButton.png"));

    }

    @FXML
    private void replyHBoxOnMouseEntered(MouseEvent event)
    {
        inboxReplyImageView.setImage(new Image("jclasschin/gallery/image/replyButtonHover.png"));
    }

    @FXML
    private void replyHBoxOnMouseClicked(MouseEvent event)
    {
        inboxReplyImageView.setImage(new Image("jclasschin/gallery/image/replyButtonActive.png"));

        if (inboxTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Mail m = inboxTableView.getSelectionModel().getSelectedItem();
            dashboardInboxReplyMailDialogController = dashboardInboxReplyMailDialogLoader.getController();
            dashboardInboxReplyMailDialogController.setDashboardInboxReplyMailDialogStage(dashboardInboxReplyMailDialogStage);
            dashboardInboxReplyMailDialogController.setMail(m);
            dashboardInboxReplyMailDialogController.initDialog();
            dashboardInboxReplyMailDialogController.initialize(null, null);
            dashboardInboxReplyMailDialogStage.showAndWait();

            updateInboxTableView();
            updateOutboxTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک نامه را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteHBoxOnMouseExited(MouseEvent event)
    {
        inboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteHBoxOnMouseEntered(MouseEvent event)
    {
        inboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));
    }

    @FXML
    private void deleteHBoxOnMouseClicked(MouseEvent event)
    {

        inboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (inboxTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Mail m = inboxTableView.getSelectionModel().getSelectedItem();
            dashboardInboxDeleteMailDialogController = dashboardInboxDeleteMailDialogLoader.getController();
            dashboardInboxDeleteMailDialogController.initialize(null, null);
            dashboardInboxDeleteMailDialogController.setDashboardInboxDeleteDialogStage(dashboardInboxDeleteMailDialogStage);
            dashboardInboxDeleteMailDialogController.setMail(m);
            dashboardInboxDeleteMailDialogStage.showAndWait();

            updateInboxTableView();
            updateOutboxTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک نامه را انتخاب نمایید.");
        }
    }

    @FXML
    private void refreshHBoxMouseExited(MouseEvent event)
    {
        inboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButton.png"));
    }

    @FXML
    private void refreshHBoxMouseEntered(MouseEvent event)
    {
        inboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonHover.png"));
    }

    @FXML
    private void refreshHBoxOnMouseClicked(MouseEvent event)
    {
        inboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonActive.png"));
        /* Refersh mail inbox table */
        updateInboxTableView();
        updateOutboxTableView();
        updateStatusTableView();
        MainLayoutController.statusProperty.setValue("صندوق نامه های دریافتی بروز شد.");
    }

    public void updateInboxTableView()
    {
        MailManager um = new MailManager();
        List l = um.selectForInbox();
        ObservableList<Mail> mailList = FXCollections.observableArrayList();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        senderTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getPersonBySenderPersonId().getFirstName() + " " + m.getValue().getPersonBySenderPersonId().getLastName()));
        dateTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getDate()));
        subjectTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getType()));
        messegeTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getText()));

        l.stream().forEach((m) ->
        {
            mailList.add((Mail) m);

        });

        inboxTableView.setItems(mailList);
    }

    //__________________________________________________________________________
    // OUTBOX
    //__________________________________________________________________________
    @FXML
    private void readHBox2OnMouseExited(MouseEvent event)
    {
        outboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButton.png"));

    }

    @FXML
    private void readHBox2OnMouseEntered(MouseEvent event)
    {
        outboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButtonHover.png"));
    }

    @FXML
    private void readHBox2OnMouseClicked(MouseEvent event)
    {
        outboxReadMailImageView.setImage(new Image("jclasschin/gallery/image/readMailButtonActive.png"));
        if (outboxTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Mail m = outboxTableView.getSelectionModel().getSelectedItem();
            dashboardOutboxReadMailDialogController.setDashboardOutboxReadDialogStage(dashboardOutboxReadMailDialogStage);
            dashboardOutboxReadMailDialogController.setMail(m);
            dashboardOutboxReadMailDialogController.initDialog();
            dashboardOutboxReadMailDialogStage.showAndWait();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک نامه را انتخاب نمایید.");
        }
    }

    @FXML
    private void new2HBoxOnMouseExited(MouseEvent event)
    {
        outboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));
    }

    @FXML
    private void new2HBoxOnMouseEntered(MouseEvent event)
    {
        outboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void new2HBoxOnMouseClicked(MouseEvent event)
    {
        outboxNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        dashboardInboxNewMailDialogController = dashboardInboxNewMailDialogLoader.getController();
        dashboardInboxNewMailDialogController.initialize(null, null);
        dashboardInboxNewMailDialogController.setDashboardIboxNewDialogStage(dashboardInboxNewMailDialogStage);
        dashboardInboxNewMailDialogController.initDialog();
        dashboardInboxNewMailDialogStage.showAndWait();

        updateInboxTableView();
        updateOutboxTableView();
    }

    @FXML
    private void delete2HBoxOnMouseExited(MouseEvent event)
    {
        outboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));
    }

    @FXML
    private void delete2HBoxOnMouseEntered(MouseEvent event)
    {
        outboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));
    }

    @FXML
    private void delete2HBoxOnMouseClicked(MouseEvent event)
    {
        outboxDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (outboxTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Mail m = outboxTableView.getSelectionModel().getSelectedItem();
            dashboardOutboxDeleteMailDialogController = dashboardOutboxDeleteMailDialogLoader.getController();
            dashboardOutboxDeleteMailDialogController.initialize(null, null);
            dashboardOutboxDeleteMailDialogController.setDashboardOutboxDeleteDialogStage(dashboardOutboxDeleteMailDialogStage);
            dashboardOutboxDeleteMailDialogController.setMail(m);
            dashboardOutboxDeleteMailDialogStage.showAndWait();

            updateInboxTableView();
            updateOutboxTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک نامه را انتخاب نمایید.");
        }
    }

    @FXML
    private void refresh3HBoxMouseExited(MouseEvent event)
    {
        outboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButton.png"));
    }

    @FXML
    private void refresh3HBoxMouseEntered(MouseEvent event)
    {
        outboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonHover.png"));

    }

    @FXML
    private void refresh3HBoxOnMouseClicked(MouseEvent event)
    {
        outboxRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonActive.png"));

        /* Refersh mail outbox table */
        updateOutboxTableView();
        updateInboxTableView();
        updateStatusTableView();
        MainLayoutController.statusProperty.setValue("صندوق نامه های ارسالی بروز شد.");
    }

    public void updateOutboxTableView()
    {
        MailManager um = new MailManager();
        List l = um.selectForOutbox();
        ObservableList<Mail> mailList = FXCollections.observableArrayList();
        outboxIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        outboxReceiverTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getPersonByReceiverPersonId().getFirstName() + " " + m.getValue().getPersonByReceiverPersonId().getLastName()));
        outboxDateTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getDate()));
        outboxSubjectTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getType()));
        outboxMessegeTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Mail, String> m) -> new ReadOnlyObjectWrapper(m.getValue().getText()));

        l.stream().forEach((m) ->
        {
            mailList.add((Mail) m);

        });
        outboxTableView.setItems(mailList);
    }

    //__________________________________________________________________________
    // STATUS
    //__________________________________________________________________________
    @FXML
    private void refresh2HBoxOnMouseExited(MouseEvent event)
    {
        statusRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButton.png"));

    }

    @FXML
    private void refresh2HBoxOnMouseEntered(MouseEvent event)
    {
        statusRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonHover.png"));

    }

    @FXML
    private void refresh2HBoxOnMouseClicked(MouseEvent event)
    {
        statusRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonActive.png"));

        /* Refersh status table */
        updateStatusTableView();
        updateInboxTableView();
        updateOutboxTableView();
        MainLayoutController.statusProperty.setValue("جدول وضعیت بروز رسانی شد.");

    }

    public void updateStatusTableView()
    {
        StatusManager statusManager = new StatusManager();
        List l = statusManager.selectAllByTerm(CtacssManager.currentTerm.getName());

        ObservableList<Status> statusList = FXCollections.observableArrayList();
        statusIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statusFieldTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Status, String> s) -> new ReadOnlyObjectWrapper(s.getValue().getField().getName()));
        //statusTermTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Status, String> s) -> new ReadOnlyObjectWrapper(s.getValue().getTerm().getName()));
        statusLastUpdateTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Status, String> s) -> new ReadOnlyObjectWrapper(s.getValue().getLastUpdate()));
        statusStateTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Status, Boolean> s) ->
        {
            return new ReadOnlyObjectWrapper(s.getValue().getState() ? "تایید شده" : "تایید نشده");
        });

        l.stream().forEach((s) ->
        {
            statusList.add((Status) s);

        });
        statusTableView.setItems(statusList);
    }

    //__________________________________________________________________________
    // TERM
    //__________________________________________________________________________
    @FXML
    private void newTermHBoxOnMouseExited(MouseEvent event)
    {
        termNewImageView.setImage(new Image("jclasschin/gallery/image/addButton.png"));
    }

    @FXML
    private void newTermHBoxOnMouseEntered(MouseEvent event)
    {
        termNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonHover.png"));

    }

    @FXML
    private void newTermHBoxOnMouseClicked(MouseEvent event)
    {
        termNewImageView.setImage(new Image("jclasschin/gallery/image/addButtonActive.png"));

        dashboardTermNewDialogController = dashboardTermNewDailogLoader.getController();
        dashboardTermNewDialogController.initialize(null, null);
        dashboardTermNewDialogController.setDashboardTermNewDialogStage(dashboardTermNewDailogStage);
        dashboardTermNewDialogController.initDialog();
        dashboardTermNewDailogStage.showAndWait();

        updateTermTableView();
    }

    @FXML
    private void editTermHBoxOnMouseExited(MouseEvent event)
    {
        termEditImageView.setImage(new Image("jclasschin/gallery/image/editButton.png"));

    }

    @FXML
    private void editTermHBoxOnMouseEntered(MouseEvent event)
    {
        termEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonHover.png"));

    }

    @FXML
    private void editTermHBoxOnMouseClicked(MouseEvent event)
    {
        termEditImageView.setImage(new Image("jclasschin/gallery/image/editButtonActive.png"));
        if (termTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Term t = termTableView.getSelectionModel().getSelectedItem();

            dashboardTermEditDialogController = dashboardTermEditDailogLoader.getController();
            dashboardTermEditDialogController.initialize(null, null);
            dashboardTermEditDialogController.setDashboardTermEditDialogStage(dashboardTermEditDailogStage);
            dashboardTermEditDialogController.setEditableTerm(t);
            dashboardTermEditDialogController.initDialog();
            dashboardTermEditDailogStage.showAndWait();

            updateTermTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک ترم را انتخاب نمایید.");
        }
    }

    @FXML
    private void deleteTermHBoxOnMouseExited(MouseEvent event)
    {
        termDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButton.png"));

    }

    @FXML
    private void deleteTermHBoxOnMouseEntered(MouseEvent event)
    {
        termDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonHover.png"));

    }

    @FXML
    private void deleteTermHBoxOnMouseClicked(MouseEvent event)
    {
        termDeleteImageView.setImage(new Image("jclasschin/gallery/image/deleteButtonActive.png"));
        if (termTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Term t = termTableView.getSelectionModel().getSelectedItem();
            dashboardTermDeleteDialogController = dashboardTermDeleteDailogLoader.getController();
            dashboardTermDeleteDialogController.initialize(null, null);
            dashboardTermDeleteDialogController.setDashboardTermDeleteDailogStage(dashboardTermDeleteDailogStage);
            dashboardTermDeleteDialogController.setEditableTerm(t);
            dashboardTermDeleteDailogStage.showAndWait();

            updateTermTableView();
        } else
        {
            MainLayoutController.statusProperty.setValue("یک ترم را انتخاب نمایید.");
        }
    }

    @FXML
    private void refresh4HBoxMouseExited(MouseEvent event)
    {
        termRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButton.png"));

    }

    @FXML
    private void refresh4HBoxMouseEntered(MouseEvent event)
    {
        termRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonHover.png"));

    }

    @FXML
    private void refresh4HBoxOnMouseClicked(MouseEvent event)
    {
        termRefreshImageView.setImage(new Image("jclasschin/gallery/image/refreshButtonActive.png"));
        CtacssManager cm = new CtacssManager();
        if (currentTermComboBox.getValue() == null ? CtacssManager.currentTerm.getName() != null : !currentTermComboBox.getValue().equals(CtacssManager.currentTerm.getName()))
        {
            cm.updateCurrentTerm(currentTermComboBox.getValue());
            System.out.println(CtacssManager.currentTerm.getName());
            MainLayoutController.statusProperty.setValue("ترم جاری سیستم بروز شد.");
            MainLayoutController.currentTermProperty.setValue(currentTermComboBox.getValue());

            updateInboxTableView();
            updateOutboxTableView();
            updateStatusTableView();
            //updateTermTableView();
        }
    }

    public void updateTermTableView()
    {
        if (Login.loggedUser.getPerson().getJob().getId() != 1)
        {
            statusTab.setDisable(true);
            termTab.setDisable(true);
        }

        currentTermComboBox.getItems().clear();
        TermManager tm = new TermManager();
        List l = tm.selectAll();
        ObservableList<Term> termList = FXCollections.observableArrayList();

        termIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        termNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        l.stream().forEach((t) ->
        {
            termList.add((Term) t);
            currentTermComboBox.getItems().add(((Term) t).getName());
        });

        termTableView.setItems(termList);
        new CtacssManager().initCurrentTerm();
        currentTermComboBox.setValue(CtacssManager.currentTerm.getName());
        MainLayoutController.currentTermProperty.setValue(currentTermComboBox.getValue());
    }

}

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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
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

    private final FXMLLoader dashboardInboxNewMailDialogLoader, dashboardInboxReplyMailDialogLoader,
            dashboardInboxDeleteMailDialogLoader, dashboardOutboxDeleteMailDialogLoader, dashboardTermNewDailogLoader,
            dashboardTermEditDailogLoader, dashboardTermDeleteDailogLoader;
    private final AnchorPane dashboardInboxNewMailDialogLayout, dashboardInboxReplyMailDialogLayout,
            dashboardInboxDeleteMailDialogLayout, dashboardOutboxDeleteMailDialogLayout, dashboardTermNewDailogLayout,
            dashboardTermEditDailogLayout, dashboardTermDeleteDailogLayout;
    private final Scene dashboardInboxNewMailDialogScene, dashboardInboxReplyMailDialogScene,
            dashboardInboxDeleteMailDialogScene, dashboardOutboxDeleteMailDialogScene, dashboardTermNewDailogScene,
            dashboardTermEditDailogScene, dashboardTermDeleteDailogScene;
    private final Stage dashboardInboxNewMailDialogStage, dashboardInboxReplyMailDialogStage,
            dashboardInboxDeleteMailDialogStage, dashboardOutboxDeleteMailDialogStage, dashboardTermNewDailogStage,
            dashboardTermEditDailogStage, dashboardTermDeleteDailogStage;

    /* Mail Inbox */
    private DashboardInboxNewDialogController dashboardInboxNewMailDialogController;
    private DashboardInboxReplyDialogController dashboardInboxReplyMailDialogController;
    private DashboardInboxDeleteDialogController dashboardInboxDeleteMailDialogController;

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

    public DashboardLayoutController() throws IOException
    {
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
    }

    @FXML
    private void newHBoxOnMouseExited(MouseEvent event)
    {

    }

    @FXML
    private void newHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void newHBoxOnMouseClicked(MouseEvent event)
    {
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
    }

    @FXML
    private void replyHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void replyHBoxOnMouseClicked(MouseEvent event)
    {
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
        }
    }

    @FXML
    private void deleteHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void deleteHBoxOnMouseClicked(MouseEvent event)
    {
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
        }
    }

    @FXML
    private void refreshHBoxMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void refreshHBoxMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void refreshHBoxOnMouseClicked(MouseEvent event)
    {
        /* Refersh mail inbox table */
        updateInboxTableView();
    }

    @FXML
    private void new2HBoxOnMouseExited(MouseEvent event)
    {

    }

    @FXML
    private void new2HBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void new2HBoxOnMouseClicked(MouseEvent event)
    {
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

    }

    @FXML
    private void delete2HBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void delete2HBoxOnMouseClicked(MouseEvent event)
    {
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
        }
    }

    @FXML
    private void refresh2HBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void refresh2HBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void refresh2HBoxOnMouseClicked(MouseEvent event)
    {
        /* Refersh status table */
        updateStatusTableView();
        MainLayoutController.statusProperty.setValue("جدول وضعیت بروز رسانی شد.");

    }

    @FXML
    private void newTermHBoxOnMouseClicked(MouseEvent event)
    {
        dashboardTermNewDialogController = dashboardTermNewDailogLoader.getController();
        dashboardTermNewDialogController.initialize(null, null);
        dashboardTermNewDialogController.setDashboardTermNewDialogStage(dashboardTermNewDailogStage);
        dashboardTermNewDialogController.initDialog();
        dashboardTermNewDailogStage.showAndWait();

        updateTermTableView();
    }

    @FXML
    private void editTermHBoxOnMouseClicked(MouseEvent event)
    {
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
        }
    }

    @FXML
    private void deleteTermHBoxOnMouseClicked(MouseEvent event)
    {
        if (termTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Term t = termTableView.getSelectionModel().getSelectedItem();
            //dashboardTermDeleteDialogController = new DashboardTermDeleteDialogController();
            dashboardTermDeleteDialogController = dashboardTermDeleteDailogLoader.getController();
            dashboardTermDeleteDialogController.initialize(null, null);
            dashboardTermDeleteDialogController.setDashboardTermDeleteDailogStage(dashboardTermDeleteDailogStage);
            dashboardTermDeleteDialogController.setEditableTerm(t);
            dashboardTermDeleteDailogStage.showAndWait();

            updateTermTableView();
        }
    }

    public void updateTermTableView()
    {
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

    @FXML
    private void currentTermComboBoxOnInputMethodTextChanged(InputMethodEvent event)
    {

    }

    @FXML
    private void currentTermComboBoxOnAction(ActionEvent event)
    {
    }

    @FXML
    private void currentTermComboBoxOnContextMenuRequested(ContextMenuEvent event)
    {
    }

    @FXML
    private void termTabOnClose(Event event)
    {
    }

    @FXML
    private void termTabOnSelectionChanged(Event event)
    {
        CtacssManager cm = new CtacssManager();
        if (currentTermComboBox.getValue() == null ? CtacssManager.currentTerm.getName() != null : !currentTermComboBox.getValue().equals(CtacssManager.currentTerm.getName()))
        {
            cm.updateCurrentTerm(currentTermComboBox.getValue());
            System.out.println(CtacssManager.currentTerm.getName());
            MainLayoutController.statusProperty.setValue("ترم جاری سیستم بروز شد.");
        }
    }

}

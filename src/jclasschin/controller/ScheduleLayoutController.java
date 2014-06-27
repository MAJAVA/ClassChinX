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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jclasschin.JClassChin;
import jclasschin.entity.Cctm;
import jclasschin.model.CCManager;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class ScheduleLayoutController implements Initializable
{
    
    private final FXMLLoader scheduleNewDialogLoader, scheduleEditDialogLoader, scheduleDeleteDialogLoader;
    private final AnchorPane scheduleNewDialogLayout, scheduleEditDialogLayout, scheduleDeleteDialogLayout;
    private final Scene scheduleNewDialogScene, scheduleEditDialogScene, scheduleDeleteDialogScene;
    private final Stage scheduleNewDialogStage, scheduleEditDialogStage, scheduleDeleteDialogStage;
    
    private final ScheduleNewDialogController scheduleNewDialogController;
    private final ScheduleEditDialogController scheduleEditDialogController;
    private final ScheduleDeleteDialogController scheduleDeleteDialogController;
    
    @FXML
    private HBox newHBox;
    @FXML
    private HBox editHBox;
    @FXML
    private HBox deleteHBox;
    
    @FXML
    private TableView<Cctm> scheduleTableView;
    @FXML
    private TableColumn<Cctm, Integer> idTableColumn;
    @FXML
    private TableColumn<Cctm, String> dayTableColumn;
    @FXML
    private TableColumn<Cctm, String> classTableColumn;
    @FXML
    private TableColumn<Cctm, String> timeTableColumn;
    @FXML
    private TableColumn<Cctm, String> courseTableColumn;
    @FXML
    private TableColumn<Cctm, String> professorTableColumn;
    @FXML
    private HBox printHBox;
    
    public ScheduleLayoutController() throws IOException
    {

        /*  New Schedule */
        scheduleNewDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/ScheduleNewDialog.fxml"));
        scheduleNewDialogLayout = (AnchorPane) scheduleNewDialogLoader.load();
        scheduleNewDialogScene = new Scene(scheduleNewDialogLayout);
        scheduleNewDialogStage = new Stage();
        scheduleNewDialogStage.setScene(scheduleNewDialogScene);
        scheduleNewDialogStage.setTitle("برنامه ریزی جدید");
        scheduleNewDialogStage.initModality(Modality.WINDOW_MODAL);
        scheduleNewDialogStage.initOwner(JClassChin.getMainStage());
        scheduleNewDialogStage.setResizable(false);
        scheduleNewDialogStage.initStyle(StageStyle.UTILITY);
        
        scheduleNewDialogController = scheduleNewDialogLoader.getController();

        /* Edit Schedule */
        scheduleEditDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/ScheduleEditDialog.fxml"));
        scheduleEditDialogLayout = (AnchorPane) scheduleEditDialogLoader.load();
        scheduleEditDialogScene = new Scene(scheduleEditDialogLayout);
        scheduleEditDialogStage = new Stage();
        scheduleEditDialogStage.setScene(scheduleEditDialogScene);
        scheduleEditDialogStage.setTitle("ویرایش برنامه ریزی");
        scheduleEditDialogStage.initModality(Modality.WINDOW_MODAL);
        scheduleEditDialogStage.initOwner(JClassChin.getMainStage());
        scheduleEditDialogStage.setResizable(false);
        scheduleEditDialogStage.initStyle(StageStyle.UTILITY);
        
        scheduleEditDialogController = scheduleEditDialogLoader.getController();

        /*  Delete Schedule */
        scheduleDeleteDialogLoader = new FXMLLoader(JClassChin.class.getResource("view/ScheduleDeleteDialog.fxml"));
        scheduleDeleteDialogLayout = (AnchorPane) scheduleDeleteDialogLoader.load();
        scheduleDeleteDialogScene = new Scene(scheduleDeleteDialogLayout);
        scheduleDeleteDialogStage = new Stage();
        scheduleDeleteDialogStage.setScene(scheduleDeleteDialogScene);
        scheduleDeleteDialogStage.setTitle("حذف برنامه ریزی");
        scheduleDeleteDialogStage.initModality(Modality.WINDOW_MODAL);
        scheduleDeleteDialogStage.initOwner(JClassChin.getMainStage());
        scheduleDeleteDialogStage.setResizable(false);
        scheduleDeleteDialogStage.initStyle(StageStyle.UTILITY);
        
        scheduleDeleteDialogController = scheduleDeleteDialogLoader.getController();
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
        scheduleNewDialogController.setScheduleNewDialogStage(scheduleNewDialogStage);
        scheduleNewDialogController.initDialog();
        scheduleNewDialogStage.showAndWait();
        updateScheduleTableView();
    }
    
    @FXML
    private void editHBoxOnMouseClicked(MouseEvent event)
    {
        if (scheduleTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Cctm cctm = scheduleTableView.getSelectionModel().getSelectedItem();
            scheduleEditDialogController.setScheduleEditDialogStage(scheduleEditDialogStage);
            scheduleEditDialogController.setEditableCctm(cctm);
            scheduleEditDialogController.initDialog();
            scheduleEditDialogStage.showAndWait();
            updateScheduleTableView();
        }
    }
    
    @FXML
    private void deleteHBoxOnMouseClicked(MouseEvent event)
    {
        if (scheduleTableView.getSelectionModel().getSelectedIndex() != -1)
        {
            Cctm cctm = scheduleTableView.getSelectionModel().getSelectedItem();
            scheduleDeleteDialogController.setScheduleDeleteDialogStage(scheduleDeleteDialogStage);
            scheduleDeleteDialogController.setEditableCctm(cctm);
            scheduleDeleteDialogController.initDialog();
            scheduleDeleteDialogStage.showAndWait();
            updateScheduleTableView();
        }
        
    }
    
    void updateScheduleTableView()
    {
        CCManager ccm = new CCManager();
        List l = ccm.selectAll();
        
        ObservableList<Cctm> cctmList = FXCollections.observableArrayList();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dayTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Cctm, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getWeekday().getDayName()));
        classTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Cctm, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getDedication().getClassroom().getName()));
        timeTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Cctm, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getPeriod().getEnd() + " - " + c.getValue().getPeriod().getStart()));
        courseTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Cctm, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getCourse().getName()));
        professorTableColumn.setCellValueFactory((TableColumn.CellDataFeatures<Cctm, String> c) -> new ReadOnlyObjectWrapper(c.getValue().getPerson().getFirstName() + " " + c.getValue().getPerson().getLastName()));
        
        l.stream().forEach((c) ->
        {
            cctmList.add((Cctm) c);
        });
        scheduleTableView.setItems(cctmList);
        
    }
    
    @FXML
    private void printHBoxOnMouseClicked(MouseEvent event)
    {
        /*  ali's code!!!  */
    }
    
}

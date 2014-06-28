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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jclasschin.entity.Cctm;
import jclasschin.model.CCManager;
import jclasschin.model.Login;
import jclasschin.model.StatusManager;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ScheduleDeleteDialogController implements Initializable
{

    private Stage scheduleDeleteDialogStage;
    private Cctm editableCctm;

    @FXML
    private HBox yesHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox noHBox;
    @FXML
    private ImageView cancelImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void yesHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void yesHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void yesHBoxOnMouseClicked(MouseEvent event)
    {
        CCManager ccm = new CCManager();
        if (ccm.delete(editableCctm.getId()))
        {
            StatusManager sm = new StatusManager();
            sm.insert();
            MainLayoutController.statusProperty.setValue("برنامه با موفقیت حذف شد.");
        }
        else
        {
            MainLayoutController.statusProperty.setValue("عملیات حذف برنامه با شکست مواجه شد.");
        }

        scheduleDeleteDialogStage.close();
    }

    @FXML
    private void noHBoxOnMouseExited(MouseEvent event)
    {
    }

    @FXML
    private void noHBoxOnMouseEntered(MouseEvent event)
    {
    }

    @FXML
    private void noHBoxOnMouseClicked(MouseEvent event)
    {
        MainLayoutController.statusProperty.setValue("عملیات حذف برنامه لغو شد.");
        scheduleDeleteDialogStage.close();
    }

    /**
     * @return the scheduleDeleteDialogStage
     */
    public Stage getScheduleDeleteDialogStage()
    {
        return scheduleDeleteDialogStage;
    }

    /**
     * @param scheduleDeleteDialogStage the scheduleDeleteDialogStage to set
     */
    public void setScheduleDeleteDialogStage(Stage scheduleDeleteDialogStage)
    {
        this.scheduleDeleteDialogStage = scheduleDeleteDialogStage;
    }

    /**
     * @return the editableCctm
     */
    public Cctm getEditableCctm()
    {
        return editableCctm;
    }

    /**
     * @param editableCctm the editableCctm to set
     */
    public void setEditableCctm(Cctm editableCctm)
    {
        this.editableCctm = editableCctm;
    }

    public void initDialog()
    {
        yesHBox.setDisable(false);
        if (!editableCctm.getDedication().getField().getName().equals(Login.loggedUserField))
        {
            yesHBox.setDisable(true);
            MainLayoutController.statusProperty.setValue("شما قادر به حذف برنامه دیگر رشته ها نیستید!");
        }
    }

}

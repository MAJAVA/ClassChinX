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
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import jclasschin.JClassChin;
import jclasschin.model.Login;
import jclasschin.util.Effect;
import jclasschin.util.Utilities;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class LoginLayoutController implements Initializable
{

    private BorderPane objectLayout;
    private AnchorPane loginLayout;

    private final FXMLLoader mainLayoutLoader;
    private final BorderPane mainLayout;
    private final MainLayoutController mainLayoutController;

    private Login login;
//    private boolean isLoggedIn;
//    private Image emptyImage, unlockImage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ImageView loginImageView;
    @FXML
    private HBox okHBox;
    @FXML
    private ImageView okImageView;
    @FXML
    private HBox cancelHBox;
    @FXML
    private ImageView cancelImageView;

    public LoginLayoutController() throws IOException
    {

//        login = new Login();
//        unlockImage = new Image("jclasschin/gallery/image/unlockIcon.png");
        mainLayoutLoader = new FXMLLoader(JClassChin.class.getResource("view/MainLayout.fxml"));
        mainLayout = (BorderPane) mainLayoutLoader.load();
        mainLayoutController = mainLayoutLoader.getController();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void start()
    {

    }

    /**
     * @param objectLayout the objectLayout to set
     */
    public void setObjectLayout(BorderPane objectLayout)
    {
        this.objectLayout = objectLayout;
    }

    @FXML
    private void okHboxOnMouseClicked(MouseEvent event) throws InterruptedException
    {

        okImageView.setImage(new Image("jclasschin/gallery/image/okButtonActive.png"));

        login = new Login();
        if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && login.checkForLogin(usernameTextField.getText(), passwordField.getText()))
        {
            //new Effect().fadeInTransition(loginImageView, 1000);

            System.out.println(login.getLoggedUser().getPerson().getJob().getId());
            System.out.println(Utilities.getCurrentShamsidate());

            new Effect().fadeInTransition(mainLayout, 1000);
            objectLayout.setCenter(mainLayout);
            mainLayoutController.setObjectLayout(objectLayout);
            mainLayoutController.setLayout(mainLayout);
            mainLayoutController.setLoginLayout(loginLayout);
            usernameTextField.setText("");
            passwordField.setText("");
            okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));
            mainLayoutController.start();
        } else
        {
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(6);
            timeline.setAutoReverse(true);
            final KeyValue kv = new KeyValue(loginImageView.opacityProperty(), 0.0);
            final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            passwordField.setText("");

        }

    }

    @FXML
    private void cancelHboxOnMouseClicked(MouseEvent event)
    {
        cancelImageView.setImage(new Image("jclasschin/gallery/css/cancelButtonActive.png"));
        Platform.exit();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) throws InterruptedException
    {

        okHboxOnMouseClicked(null);
        okImageView.setImage(new Image("jclasschin/gallery/image/okButton.png"));

//        login = new Login();
//        if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && login.checkForLogin(usernameTextField.getText(), passwordField.getText()))
//        {
//            //new Effect().fadeInTransition(loginImageView, 1000);
//
//            System.out.println(login.getLoggedUser().getPerson().getJob().getId());
//            System.out.println(Utilities.getCurrentShamsidate());
//
//            new Effect().fadeInTransition(mainLayout, 1000);
//            objectLayout.setCenter(mainLayout);
//            mainLayoutController.setObjectLayout(objectLayout);
//            mainLayoutController.setLayout(mainLayout);
//            mainLayoutController.setLoginLayout(loginLayout);
//            mainLayoutController.start();
//        } else
//        {
//            final Timeline timeline = new Timeline();
//            timeline.setCycleCount(6);
//            timeline.setAutoReverse(true);
//            final KeyValue kv = new KeyValue(loginImageView.opacityProperty(), 0.0);
//            final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
//            timeline.getKeyFrames().add(kf);
//            timeline.play();
//            passwordField.setText("");
//        }
    }

    @FXML
    private void usernameTextFieldOnAction(ActionEvent event) throws InterruptedException
    {
        passwordFieldOnAction(event);
    }

    /**
     * @param loginLayout the loginLayout to set
     */
    public void setLayout(AnchorPane loginLayout)
    {
        this.loginLayout = loginLayout;
    }

}

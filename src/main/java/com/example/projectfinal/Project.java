package com.example.projectfinal;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.nio.file.Paths;
import java.util.Stack;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import static javafx.application.Application.launch;

public class Project extends Application {
    Stack<Scene> BackesStack = new Stack<>();

    static ArrayList<Products> products = FileProducts.readProducts("src/main/resources/com/example/ProjectFinal/Products.txt");
        ArrayList<Categories> categories = File_Categories.readCategories("src/main/resources/com/example/ProjectFinal/Categories.txt");
        Cart cart=new Cart();

    private void setScene(Stage stage, Scene scene, boolean saveToStack) {
        if (saveToStack && stage.getScene() != null) {
            BackesStack.push(stage.getScene());
        }
        stage.setScene(scene);
        stage.setMaximized(true); // ← هاد بس كافي
        stage.show();

    }
    @Override
        public void start(Stage stage) throws IOException {

            BorderPane root = new BorderPane();

            // ================= TITLE =================
            Label title = new Label("Gulnar SHOPE");
            title.setFont(Font.font("Segoe Script", FontWeight.BOLD, FontPosture.ITALIC, 60));
            title.setTextFill(Color.ROYALBLUE);
            title.setEffect(new Glow(0.7));

            HBox topBox = new HBox(title);
            topBox.setAlignment(Pos.CENTER);
            topBox.setPadding(new Insets(20));

            // animations
            TranslateTransition move = new TranslateTransition(Duration.millis(1500), title);
            move.setByY(8);
            move.setAutoReverse(true);
            move.setCycleCount(Animation.INDEFINITE);
            move.play();

            ScaleTransition pulse = new ScaleTransition(Duration.millis(1200), title);
            pulse.setFromX(1.0);
            pulse.setFromY(1.0);
            pulse.setToX(1.06);
            pulse.setToY(1.06);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(Animation.INDEFINITE);
            pulse.play();

            root.setTop(topBox);

            // ================= CARD =================
            VBox card = new VBox(20);
            card.setAlignment(Pos.CENTER);
            card.setPadding(new Insets(50));
            card.setMaxWidth(500);

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 20;" +
                            "-fx-border-radius: 20;" +
                            "-fx-border-color: #7EC0EE;" +
                            "-fx-border-width: 2;"
            );

            // ================= IMAGE =================
            ImageView imageView1 = new ImageView(
                    new Image(getClass().getResource("/com/example/projectfinal/ROSE2.jpeg").toExternalForm())
            );
            imageView1.setFitHeight(250);
            imageView1.setFitWidth(250);

            StackPane imageContainer = new StackPane(imageView1);
            imageContainer.setStyle(
                    "-fx-border-color: #7EC0EE;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 10;" +
                            "-fx-background-radius: 10;"
            );

            // ================= LABELS =================
            Label label_name = new Label("Enter the name:");
            label_name.setTextFill(Color.ROYALBLUE);
            label_name.setFont(Font.font("Arial", FontWeight.BOLD, 16));

            Label label_password = new Label("Enter the password:");
        label_password.setTextFill(Color.ROYALBLUE);
            label_password.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            // ================= TEXTFIELDS =================
            TextField text_name = new TextField();

        text_name.setPrefSize(400, 40);

        ImageView username = new ImageView(
                new Image(getClass().getResource("/com/example/projectfinal/username.png").toExternalForm())
        );

        username.setFitHeight(40);
        username.setFitWidth(35);

        HBox textBox = new HBox(30);
        textBox.setAlignment(Pos.CENTER_LEFT);

        textBox.getChildren().addAll(username, text_name );

            TextField text_password = new TextField();
            text_password.setPrefSize(400,40);

        ImageView lockIcon = new ImageView(
                new Image(getClass().getResource("/com/example/projectfinal/lock.png").toExternalForm())
        );

        lockIcon.setFitHeight(40);
        lockIcon.setFitWidth(40);

        HBox passwordBox = new HBox(30);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        passwordBox.getChildren().addAll(lockIcon, text_password);
            // ================= BUTTON =================
            Button button_Login = new Button("Login Now");
        button_Login.setFont(Font.font("Segoe Script", FontWeight.BOLD, FontPosture.ITALIC, 25));

        button_Login.setTextFill(Color.ROYALBLUE);
            button_Login.setPrefSize(200, 50);
            button_Login.setAlignment(Pos.CENTER);


        Button registerButton = new Button("Create Account");
        registerButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #1E90FF;" +
                        "-fx-font-size: 14px;" +
                        "-fx-underline: true;"
        );
            // ================= USERS =================
            ArrayList<USER> User =
                    File_Information.readFile("src/main/resources/com/example/ProjectFinal/USERS.txt");

            button_Login.setOnAction(e ->
                    handelLogin(stage, text_name, text_password)
            );

            // ================= ADD TO CARD =================
            card.getChildren().addAll(
                    imageContainer,
                    label_name,
                    textBox,
                    label_password,
                    passwordBox,
                    button_Login,
                    registerButton
            );
        registerButton.setOnAction(e -> openRegisterWindow());

            // ================= CENTER =================
            StackPane center = new StackPane(card);
            center.setAlignment(Pos.CENTER);

            root.setCenter(center);

            // background
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #dbe9f4, #f5f7fa);"
            );

            Scene scene = new Scene(root,
                    Screen.getPrimary().getVisualBounds().getWidth(),
                    Screen.getPrimary().getVisualBounds().getHeight()
            );

        setScene(stage, scene, false);
            stage.setTitle("Login Page");
        stage.show();

        }

        public void handelLogin (Stage stage, TextField text_Name, TextField text_passwprd) {
            String enter_Name = text_Name.getText().trim();
            String enter_password = text_passwprd.getText().trim();
            boolean found = false;

            List<USER> userList = new ArrayList<>();
            try {
                userList = File_Information.readFile("src/main/resources/com/example/ProjectFinal/USERS.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button logein_Button = new Button();
            for (USER user : userList) {
                if (user.getUesrName().equalsIgnoreCase(enter_Name) && user.getPasswod().equalsIgnoreCase(enter_password) && !found) {
                    found = true;
                    //logein_Button.setOnAction(e -> showCategoryInterface(stage,categories));
                    break;
                }
            }
            if (found) {
                BackesStack.push(stage.getScene());
                showCategoryInterface(stage,categories);
            } else {
                JOptionPane.showMessageDialog(null, "please try again, Username or Password is not correct.");

            }
        }
    public void openRegisterWindow() {
        Stage regStage = new Stage();

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Label title = new Label("Create Account");
        title.setFont(Font.font("Segoe Script", FontWeight.BOLD, 30));
        title.setTextFill(Color.DARKBLUE);

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefSize(250, 40);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setPrefSize(250, 40);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setPrefSize(250, 40);

        Button saveBtn = new Button("Register");

        Label msg = new Label();

        saveBtn.setOnAction(e -> {

            String user = username.getText().trim();
            String pass = password.getText().trim();
            String confirm = confirmPassword.getText().trim();


            if (user.isEmpty() || pass.isEmpty()) {
                msg.setText("Please fill all fields");
                msg.setTextFill(Color.RED);
                return;
            }

            if (!pass.equals(confirm)) {
                msg.setText("Passwords do not match");
                msg.setTextFill(Color.RED);
                return;
            }

            //try (FileWriter fw = new FileWriter("src/main/resources/com/example/ProjectFinal/USERS.txt", true)) {

                String path = System.getProperty("user.home") + "/GulnarStore/USERS.txt";
                try (FileWriter fw = new FileWriter(path, true)) {
                    fw.write(System.lineSeparator() + user + " " + pass);

                msg.setTextFill(Color.GREEN);
                msg.setText("Account created successfully!");

                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> regStage.close());
                pause.play();

            } catch (IOException ex) {
                msg.setText("Error saving user");
                msg.setTextFill(Color.RED);
            }
        });
        layout.getChildren().addAll(
                title,
                username,
                password,
                confirmPassword,
                saveBtn,
                msg
        );

        Scene scene = new Scene(layout, 350, 400);
        regStage.setScene(scene);
        regStage.setTitle("Register");
        regStage.show();
    }

        public void showCategoryInterface(Stage stage, List<Categories>categories) {

            Button backButton = new Button("←");
            backButton.setStyle(
                    "-fx-background-color: darkblue;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 50%;" +  // ← دايرة
                            "-fx-min-width: 45px;" +
                            "-fx-min-height: 45px;" +
                            "-fx-max-width: 45px;" +
                            "-fx-max-height: 45px;"
            );
            backButton.setOnAction(e -> {
                if (!BackesStack.isEmpty()) {
                    stage.setScene(BackesStack.pop());
                }
            });
            GridPane thingPane = new GridPane();
            thingPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            thingPane.setAlignment(Pos.CENTER);
            thingPane.setVgap(70);
            thingPane.setHgap(70);
            thingPane.setPadding(new Insets(5, 0, 0, 0)); // يقلل الفراغ فوق

            BorderPane header = new BorderPane();
            header.setLeft(backButton);
            Label title = new Label("Gulnar STORE");
            title.setFont(Font.font("Segoe Script", FontWeight.BOLD, FontPosture.ITALIC, 100));
            title.setTextFill(Color.DARKBLUE);

            header.setCenter(title);

            header.setPadding(new Insets(10, 15, 10, 15));
            VBox mainLayout = new VBox(15, header, thingPane);
            mainLayout.setAlignment(Pos.TOP_CENTER);
            mainLayout.setPadding(new Insets(5, 0, 0, 0));
            mainLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

ArrayList<Button>buttons = new ArrayList<>();
int col=0,row=0;

            for (Categories c : categories) {
                Button categoryButton = createCategoryButton(c.getImageName(), c.getImagePath(), 100);
                buttons.add(categoryButton);
                categoryButton.setMouseTransparent(false);
                categoryButton.setPickOnBounds(true);

                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.LIGHTBLUE);
                shadow.setRadius(55);
                shadow.setSpread(0.6);

                categoryButton.setOnMouseEntered(e -> {
                    categoryButton.setScaleX(1.1);
                    categoryButton.setScaleY(1.1);
                    categoryButton.setEffect(shadow);
                });

                categoryButton.setOnMouseExited(e -> {
                    categoryButton.setScaleX(1.0);
                    categoryButton.setScaleY(1.0);
                    categoryButton.setEffect(null);
                });
                thingPane.add(categoryButton, col, row);
                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
                categoryButton.setOnAction(e -> showCatgoryWithImags(stage, c.getImage_categoriesName().toUpperCase()));
            }
            addExitButton(mainLayout);
           // Scene scene2 = new Scene(mainLayout);
            Scene scene2 = new Scene(
                    mainLayout,
                    Screen.getPrimary().getVisualBounds().getWidth(),
                    Screen.getPrimary().getVisualBounds().getHeight()
            );
            setScene(stage, scene2, true);
            stage.setTitle("MY STORE");
            stage.show();

        }

        public void showCatgoryWithImags(Stage stage, String catgoryName) {
            Button backButton = new Button("←");
            backButton.setStyle(
                    "-fx-background-color: darkblue;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 50%;" +  // ← دايرة
                            "-fx-min-width: 45px;" +
                            "-fx-min-height: 45px;" +
                            "-fx-max-width: 45px;" +
                            "-fx-max-height: 45px;"
            );
            backButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 20));
            backButton.setTextFill(Color.STEELBLUE);
            backButton.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-border-color: #4682B4;" +           // Steel Blue
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 30;" +               // شكل دائري
                            "-fx-background-radius: 30;" +
                            "-fx-padding: 10;" +
                            "-fx-cursor: hand;"
            );
// تأثير عند المرور بالفأرة
            backButton.setOnMouseEntered(e -> backButton.setTextFill(Color.DEEPSKYBLUE));
            backButton.setOnMouseExited(e -> backButton.setTextFill(Color.STEELBLUE));

            HBox top = new HBox(20, backButton);//وضع العنوان في الاعلى
            top.setAlignment(Pos.CENTER);
            top.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10;");

            backButton.setOnAction(e -> {
                if (!BackesStack.isEmpty()) {
                    stage.setScene(BackesStack.pop());
                }
            });
            List<Products>info = FileProducts.readProducts("src/main/resources/com/example/projectfinal/Products.txt");
            switch (catgoryName.toUpperCase()) {
                case "ELECTRONICS":
                    shothingCategory(stage,info, "ELECTRONICS");
                    return;
                case "FURNITURE":
                    shothingCategory(stage,info, "FURNITURE");
                    return;
                case "CLOTHES"://Clothes
                    shothingCategory(stage,info, "CLOTHES");
                    return;
                case "SHOSE"://shose
                    shothingCategory(stage,info, "SHOSE");
                    return;
                case "BAGS"://Bags
                    shothingCategory(stage,info, "BAGS");
                    return;
                case "MISCELLANEOUS":
                    shothingCategory(stage,info, "MISCELLANEOUS");
                    return;
                default:
                    break;

            }
        }


    public void shothingCategory(Stage stage  ,List<Products> productsList, String name ) {
        Button backButton = new Button("←");
        backButton.setStyle(
                "-fx-background-color: darkblue;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 50%;" +  // ← دايرة
                        "-fx-min-width: 45px;" +
                        "-fx-min-height: 45px;" +
                        "-fx-max-width: 45px;" +
                        "-fx-max-height: 45px;"
        );

        backButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 20));
        backButton.setTextFill(Color.STEELBLUE);
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #4682B4;" +           // Steel Blue
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +               // شكل دائري
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 10;" +
                        "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {
            if (!BackesStack.isEmpty()) {
                stage.setScene(BackesStack.pop());
            }
        });

// تأثير عند المرور بالفأرة
        backButton.setOnMouseEntered(e -> backButton.setTextFill(Color.DEEPSKYBLUE));
        backButton.setOnMouseExited(e -> backButton.setTextFill(Color.STEELBLUE));

        BorderPane newPane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        int row = 0;
        int col = 0;

        Label label = new Label(" ---- " + name.toUpperCase() + " ----");
        label.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, FontPosture.ITALIC, 48));
        label.setTextFill(Color.DARKBLUE);

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10));
        topBar.setLeft(backButton);
        topBar.setCenter(label);

// تعيين الحاوية العلوية في الـ newPane
        newPane.setTop(topBar);
        newPane.setCenter(grid);

        for (Products products1 : productsList) {//لو ما كان المنتج من نفس الفئة name، بيتجاهله (continue).
            if (!products1.getCategory().equalsIgnoreCase(name)) {
                continue;
            }
            InputStream imageStream = getClass().getResourceAsStream(products1.getImagesPathes());
            if (imageStream != null) {
                Image image = new Image(imageStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(230);
                imageView.setFitHeight(230);
                imageView.setPreserveRatio(true);

                DropShadow shadow = new DropShadow();
                shadow.setRadius(18);
                shadow.setOffsetX(3);
                shadow.setOffsetY(3);
                shadow.setColor(Color.color(0, 0, 0, 0.4));

                imageView.setEffect(shadow);

                Label nameLabel = new Label("Name: " + products1.getProductName());//🔹 عرض معلومات المنتج:
                nameLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 20));
                nameLabel.setTextFill(Color.DARKBLUE);
                Label priceLabel = new Label("Price: $" + products1.getPrice());
                priceLabel.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 20));
                priceLabel.setTextFill(Color.DARKBLUE);
                Label madeIn = new Label("Made In: " + products1.getMadeIn());
                madeIn.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 20));
                madeIn.setTextFill(Color.DARKBLUE);

                Button addToCartButton = new Button("🛒Add to Cart🛒");//🔹 زر الإضافة إلى السلة:
                addToCartButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 16));
                addToCartButton.setTextFill(Color.DARKBLUE);//عند الضغط على الزر، يظهر Alert فيه اختيار Yes أو No.
                Products selectedProduct = products1; // ضروري لتفادي مشاكل variable capture
                addToCartButton.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cart");
                    alert.setHeaderText(null);//هذا السطر يعطل النص العلوي (Header) في النافذة، بحيث لا يظهر أي عنوان فرعي فوق الرسالة.
                    alert.setContentText("Do you want to add this product to the cart?");

                    ButtonType yesButton = new ButtonType("Yes");// تخصيص الأزرار
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(yesButton, noButton);
// إظهار التنبيه وانتظار الرد
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == yesButton) {
                        // ✅ فقط هنا يتم إضافة المنتج
                        cart.addProduct(selectedProduct);
                    }

                    Alert confirm = new Alert(Alert.AlertType.INFORMATION);// للاضافى بنجاح
                    confirm.setContentText(products1.getProductName() + " added successfully👌💙 ");
                    confirm.showAndWait();
                });


                VBox infoBox = new VBox(20, nameLabel, priceLabel, madeIn, addToCartButton);
                infoBox.setAlignment(Pos.CENTER_LEFT);

                HBox productBox = new HBox(20, imageView, infoBox);
                productBox.setOnMouseEntered(e -> {
                    productBox.setScaleX(1.07);
                    productBox.setScaleY(1.07);
                    productBox.setOpacity(0.95);

                });

                productBox.setOnMouseExited(e -> {
                    productBox.setScaleX(1.0);
                    productBox.setScaleY(1.0);
                    productBox.setOpacity(0.95);

                });
                productBox.setPrefHeight(400); // مثلاً 250 بكسل
                productBox.setPadding(new Insets(10));
                productBox.setStyle(
                        "-fx-background-color: #f4f4f4;" +
                                "-fx-border-color: #7EC0EE;" +  // لون أزرق
                                "-fx-border-width: 3;" +
                                "-fx-border-radius: 10;" +
                                "-fx-background-radius: 10;"
                );
                productBox.setAlignment(Pos.CENTER_LEFT);
                productBox.setPrefWidth(400);
                productBox.setPrefHeight(400);


                productBox.setMouseTransparent(false);
                productBox.setPickOnBounds(true);

                productBox.setOnMouseClicked(e -> showProductDetails(stage, products1, this.cart));
                grid.add(productBox, col, row);
            }
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }

        }

        newPane.setCenter(grid);

        String blueBackgroundStyle = "-fx-background-color: #004080; -fx-text-fill: white;"; // لون أزرق داكن مع نص أبيض
        //            showInvoiceButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 18));

        Button showInvoiceButton = new Button("showInvoice");
        showInvoiceButton.setOnAction(e -> showInvoice(cart));
        showInvoiceButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 15));
//            showInvoiceButton.setTextFill(Color.STEELBLUE);
        showInvoiceButton.setStyle(blueBackgroundStyle);


        Button clearCartButton = new Button("Clear Cart 🗑️🗑️");
        clearCartButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 15));
//            clearCartButton.setTextFill(Color.STEELBLUE);
        clearCartButton.setStyle(blueBackgroundStyle);

        clearCartButton.setOnAction(e -> {
            cart.clearCart();
            // تحديث واجهة المستخدم بعد التفريغ، مثلاً إعادة تحميل قائمة المنتجات في السلة
        });

        Button showCartButton = new Button("show cart");
        showCartButton.setOnAction(e -> showCart(stage, cart));
        showCartButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 20));
        showCartButton.setTextFill(Color.STEELBLUE);
        showCartButton.setStyle(blueBackgroundStyle);


        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> addExitButton(newPane));
        exitButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 22));
//            exitButton.setTextFill(Color.RED);
        exitButton.setStyle("-fx-background-color:  #004080; -fx-text-fill: red;");


        double buttonWidth = 150;
        double buttonHeight = 50;

        clearCartButton.setPrefSize(buttonWidth, buttonHeight);
        showCartButton.setPrefSize(buttonWidth, buttonHeight);
        showInvoiceButton.setPrefSize(buttonWidth, buttonHeight);
        exitButton.setPrefSize(buttonWidth, buttonHeight);

        VBox leftColumn = new VBox(10);  // 10 بكسل مسافة بين الأزرار
        leftColumn.setAlignment(Pos.CENTER);
        leftColumn.getChildren().addAll(clearCartButton, showCartButton);

// مجموعة 2: showInvoiceButton و exitButton عمودياً
        VBox rightColumn = new VBox(10);
        rightColumn.setAlignment(Pos.CENTER);
        rightColumn.getChildren().addAll(showInvoiceButton, exitButton);

// الحاوية الأفقية التي تحتوي المجموعتين
        HBox bottomBar = new HBox(30);  // 30 بكسل مسافة بين المجموعتين
        bottomBar.setPadding(new Insets(15));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.getChildren().addAll(leftColumn, rightColumn);

// تعيين الحاوية في أسفل الـ BorderPane
        newPane.setBottom(bottomBar);
        Scene scene = new Scene(
                newPane,
                Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight()
        );
        //Scene scene = new Scene(newPane);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.LIGHTBLUE);
        glow.setRadius(35);
        glow.setSpread(0.25);

        newPane.setEffect(glow);
        setScene(stage, scene, true);
        stage.show();
    }


    public void showProductDetails(Stage stage, Products product, Cart cart) {
        BorderPane detailPane = new BorderPane();

        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Name: " + product.getImageName());
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.DARKSLATEGRAY);
        Label priceLabel = new Label("Price: $" + product.getPrice());
        priceLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        priceLabel.setTextFill(Color.DARKGREEN);  // السعر أخضر
        Label madeInLabel = new Label("Made In:" + product.getMadeIn());
        madeInLabel.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 15));
        madeInLabel.setTextFill(Color.GRAY);

        Image image = new Image(getClass().getResource(product.getImagesPathes()).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        Rectangle clip = new Rectangle(400, 400);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        imageView.setClip(clip);

        DropShadow imgShadow = new DropShadow();
        imgShadow.setRadius(30);
        imgShadow.setColor(Color.LIGHTGRAY);
        imageView.setEffect(imgShadow);

        URL imgUrl = getClass().getResource(product.getImagesPathes());

        Label messageLabel= new Label();
        messageLabel.setTextFill(Color.GREEN);
        messageLabel.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 17));

        Button addToCartButton = new Button("🛒Add to Cart🛒");
        addToCartButton.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 23));
        addToCartButton.setStyle("-fx-background-color: lightgreen;");
        addToCartButton.setOnAction(e -> {
            cart.addProduct(product);
            messageLabel.setText(product.getProductName() + " Added to cart😊👌");

        });

        Button backButton = new Button("←");
        backButton.setStyle(
                "-fx-background-color: darkblue;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 50%;" +  // ← دايرة
                        "-fx-min-width: 50px;" +
                        "-fx-min-height: 50px;" +
                        "-fx-max-width: 50px;" +
                        "-fx-max-height: 50px;"
        );

                backButton.setOnAction(e -> {
                    if (!BackesStack.isEmpty()) {
                        stage.setScene(BackesStack.pop());
                    }
                });

        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.TOP_LEFT);
        backBox.setPadding(new Insets(15, 0, 0, 20));
// تأثير عند المرور بالفأرة
        backButton.setOnMouseEntered(e -> backButton.setTextFill(Color.DEEPSKYBLUE));
        backButton.setOnMouseExited(e -> backButton.setTextFill(Color.STEELBLUE));
        content.getChildren().addAll(imageView, nameLabel, priceLabel, madeInLabel, addToCartButton, messageLabel);
        content.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        detailPane.setTop(backBox);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        detailPane.setCenter(scrollPane);
        Scene scene = new Scene(detailPane,
                Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight()
        );
        setScene(stage, scene, true);
    }

            public Button createCategoryButton(String labelText, String imagePath, double radius) {
                Image image = new Image(getClass().getResource(imagePath).toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(2 * radius);
                imageView.setFitWidth(2 * radius);
                imageView.setClip(new Circle(radius, radius, radius));

                Label label = new Label(labelText.toUpperCase());
                label.setTextFill(Color.BLUE);
                label.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, FontPosture.ITALIC, 17));
                label.setTextFill(Color.DARKBLUE);
                label.setEffect(new Glow(0.7));//  عمل توهج حول ال نص

                VBox vBox= new VBox(5);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(imageView,label);
                vBox.setPrefSize(2 * radius, 2 * radius);

                Button button = new Button();
                button.setShape(new Circle(radius));
                button.setMinSize(2 * radius, 2 * radius);
                button.setMaxSize(2 * radius, 2 * radius);
                button.setStyle("-fx-background-color: transparent;");
                button.setGraphic(vBox);
                return button;

            }

    public  VBox createProductButton(String labelText, String imagePath, String price) {
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Label namelabel = new Label(labelText);
        namelabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        namelabel.setTextFill(Color.DARKBLUE);
        namelabel.setAlignment(Pos.CENTER);
        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", 13));

        Node nameLabel=namelabel;
        VBox box = new VBox(10, imageView, nameLabel, priceLabel);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;");
        return box;

    }
    public void showCart(Stage ownerStage, Cart cart) {
        Stage cartStage = new Stage();
        cartStage.setTitle("Shopping Cart");
        cartStage.initOwner(ownerStage);
        cartStage.initModality(Modality.WINDOW_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("🛒 Your Cart");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox itemsBox = new VBox(5);

        if (cart.getCart().isEmpty()) {
            itemsBox.getChildren().add(new Label("Cart is empty 😢"));
        } else {
            for (Products product : cart.getProducts()) {
                String priceFormatted = String.format("%.2f", product.getPrice());
                Label item = new Label(product.getProductName() + " - $" + priceFormatted);
                itemsBox.getChildren().add(item);
            }
        }

        Label total = new Label("Total: $" + cart.getTotalPrice());
        total.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        total.setTextFill(Color.FORESTGREEN);

        // زر الشراء
        Button purchaseButton = new Button("Confirm Purchase 💵💵✅");
        purchaseButton.setOnAction(e -> {
            showInvoice(cart);
            cartStage.close(); // إغلاق نافذة السلة بعد الشراء
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> cartStage.close());

        root.getChildren().addAll(title, itemsBox, total, purchaseButton, closeButton);

        Scene scene = new Scene(root, 400, 450);
        BackesStack.push(cartStage.getScene()); // احفظ الصفحة الحالية
        cartStage.setScene(scene);
        cartStage.show();
    }
    public void showInvoice(Cart cart) {//  دالة عرض لفاتورة
        Stage invoiceStage = new Stage();
        invoiceStage.setTitle("Invoice");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label header = new Label(" Invoice");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        VBox invoiceItems = new VBox(5);
        for (Products product : cart.getProducts()) {
            Label item = new Label("- " + product.getProductName() + ": $" + product.getPrice());
            invoiceItems.getChildren().add(item);
        }

        Label total = new Label("Total Paid: $" + cart.getTotalPrice());
        total.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        total.setTextFill(Color.DARKGREEN);

        Label thanks = new Label("Thank you for shopping with us 💖");
        thanks.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        thanks.setTextFill(Color.DARKGREEN);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> invoiceStage.close());

        root.getChildren().addAll(header, invoiceItems, total, thanks, closeBtn);

        Scene scene = new Scene(root, 400, 450);
        BackesStack.push(invoiceStage.getScene()); // احفظ الصفحة الحالية
        invoiceStage.setScene(scene);
        invoiceStage.show();
    }

    public void addExitButton(Node root) {// تعمل مع VBox, Border , Hob ....
        Button exitButton = new Button("EXIT OF STORE");
        exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        exitButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //Alert يعرض رسالة واضحة مع خيارات (مثلاً: نعم / لا، موافق / إلغاء).
        exitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm checkout\n");
            alert.setHeaderText(null);
            alert.setContentText("\n" +"Do you want to leave the store?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
//                Platform.exit();
                try {
                    start((Stage) exitButton.getScene().getWindow()); // ⬅️ استدعاء فقط
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        HBox exitBox = new HBox(exitButton);
        exitBox.setAlignment(Pos.CENTER);
        exitBox.setPadding(new Insets(10));

        // تحققي من نوع الحاوية، وأضيفي الزر بطريقة مناسبة
        if (root instanceof VBox) {
            ((VBox) root).getChildren().add(exitBox);
        } else if (root instanceof BorderPane) {
            ((BorderPane) root).setBottom(exitBox);
        } else if (root instanceof GridPane) {
            ((GridPane) root).add(exitBox, 0, ((GridPane) root).getRowCount() + 1);
        } else {
      //      System.err.println("⚠️ لا يمكن إضافة زر الخروج إلى هذا النوع من الحاويات: " + root.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
    launch();
}


}

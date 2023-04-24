import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


public class Main extends Application {
    static SessionFactory MainSessionFactory;
    String login;
    String password;
    Trener trener=null;
    Klient loggedClient=null;
    //center panel
    GridPane loginPage = new GridPane();
    GridPane registryPage = new GridPane();
    GridPane ProfilPage = new GridPane();
    GridPane GymPage = new GridPane();
    GridPane TrainersPage = new GridPane();
    GridPane ConfirmationPage = new GridPane();
    //bottom panel
    HBox loginHBox = new HBox();
    HBox registryHBox = new HBox();
    HBox profilHBox = new HBox();
    HBox gymHBox = new HBox();
    HBox trainersHBox = new HBox();
    HBox confirmationHBox = new HBox();
    public static void main(String[] args) {
        StandardServiceRegistry registry;
        try{
            registry = new StandardServiceRegistryBuilder().configure().build();
            MainSessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = MainSessionFactory.openSession();
            session.beginTransaction();
            System.out.println("============== Klienci ===============");
            Klient klient = new Klient("Jan","Kowalski",123123123,"asd","123","Jan@wp.pl",123123);
            System.out.println(klient);
            System.out.println("====================================");

            System.out.println("============== Karnet ===============");
            Karnet karnet = new Karnet(true, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),2000);
            Karnet karnet2 = new Karnet(true, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),2000);
            klient.addCarnet(karnet);
            klient.addCarnet(karnet2);
            System.out.println(karnet);
            System.out.println("====================================");
            System.out.println("============== Silownia ===============");

            Silownia silownia = new Silownia("ZDROFIT","Plac Konesera 5","03-736");
            karnet.addcarnetGymKarnet(silownia);
            silownia.addGymKarnet(karnet);

            Silownia silownia2 = new Silownia("ZDROFIT2","Aleje jerozolimskie","03-200");
            karnet2.addcarnetGymKarnet(silownia2);
            silownia2.addGymKarnet(karnet2);

            Silownia silownia3 = new Silownia("ZDROFIT3","Nowy świat","05-806");
            karnet.addcarnetGymKarnet(silownia3);
            silownia3.addGymKarnet(karnet);

            System.out.println(karnet);
            System.out.println("====================================");

            System.out.println("============== Trener ===============");
            Trener trainer = new Trener("Adam","Trener1",123123123,3000,123123123,250);
            silownia.addTrainer(trainer);
            Trener trainer2 = new Trener("Krzysztof","Trener2",123456789,4000,123456789,300);
            silownia.addTrainer(trainer2);
            Trener trainer3 = new Trener("Jakub","Trener3",123789456,3500,123789456,350);
            silownia2.addTrainer(trainer3);
            Trener trainer4 = new Trener("Paweł","Trener4",456123789,2500,456123789,220);
            silownia3.addTrainer(trainer4);
            session.save(trainer);
            session.save(trainer2);
            session.save(trainer3);
            session.save(trainer4);
            System.out.println(karnet);
            System.out.println("====================================");


            session.save(silownia);
            session.save(silownia2);
            session.save(silownia3);
            session.save(klient);
            session.save(karnet2);


            session.getTransaction().commit();
            session.close();

            /*System.out.println("\nClients from the db:");

            session = MainSessionFactory.openSession();
            session.beginTransaction();
            List<Klient> clientsFromDb = session.createQuery( "from Klient" ).list();
            for ( var client : clientsFromDb) {
                System.out.println(client.toString());
            }*/
            /*session.getTransaction().commit();
            session.close();*/
            launch();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        //styles
          final String blueButtonStyle = "-fx-background-color: #C6D373 ";
          final String blueHovered = "-fx-background-color: #B6C649";

          final String greenButtonStyle = " -fx-background-color:#01BAEF ";
          final String greenHovered = " -fx-background-color:#63BBB6";
        //=================


        //stage configuration and styles
        stage.setTitle("System zarządzający siłownią");
        javafx.scene.image.Image fxImage = new javafx.scene.image.Image("C:/Projekty/s21588_projekt/src/icons/gym.jpg");
        stage.getIcons().add(fxImage);

        //


        ArrayList<String> myListViewBooks = new ArrayList<>();
        ArrayList<String> myListViewChar = new ArrayList<>();

        //Panes
        BorderPane borderPane= new BorderPane();
        borderPane.setStyle("-fx-background-color: linear-gradient(from 10% 10% to 100% 100%, #dc143c, #661a33)");

        //

        // Login page
        {

            //Registry button
            Button registryButton = new Button("Zarejestruj się");
            registryButton.setPrefSize(100, 20);
            registryButton.setStyle(blueButtonStyle);
            registryButton.setOnMouseEntered(e -> registryButton.setStyle(blueHovered));
            registryButton.setOnMouseExited(e -> registryButton.setStyle(blueButtonStyle));
            registryButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    borderPane.setCenter(registryPage);
                    borderPane.setBottom(registryHBox);
                    registryButton.setStyle(blueButtonStyle);
                }
            });

            //login button
            Button loginButton = new Button("Zaloguj się");
            loginButton.setPrefSize(100, 20);
            loginButton.setStyle(blueHovered);
            loginButton.setOnMouseEntered(e -> loginButton.setStyle(blueButtonStyle));
            loginButton.setOnMouseExited(e -> loginButton.setStyle(blueHovered));
            loginButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Session session = MainSessionFactory.openSession();
                    session.beginTransaction();
                    Klient loggedClient = (Klient) session.createQuery( "from Klient WHERE Login = :typedLogin AND password = :typedPassword" ).setParameter("typedLogin", getLogin()).setParameter("typedPassword",getPassword()).uniqueResult();
                    if(loggedClient!=null)
                    {
                        borderPane.setCenter(ProfilPage);
                        borderPane.setBottom(profilHBox);
                        setLoggedClient(loggedClient);
                        profile(ProfilPage,borderPane);
                    }else{
                        System.out.println("Nie ma takiego użytkownika");
                        session.getTransaction().commit();
                        session.close();
                    }
                }
            });

            //login field
            TextArea loginField = new TextArea();
            loginField.setPrefHeight(20);
            loginField.setPrefWidth(300);
            loginField.setPromptText("Login");
            loginField.textProperty().addListener((observable, oldValue, newValue) -> {
               this.login=newValue;
            });
            //password field
            PasswordField passwordField = new PasswordField();
            passwordField.setPrefSize(300,30);
            passwordField.setPromptText("hasło");
            passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
                this.password=newValue;
            });

            Label label = new Label("System zarządzania siłownią");
            label.setTextFill(Color.web("#e8edeb"));
            label.setStyle("-fx-font-size:25px;");

            //styles for the login page
            loginPage.setVgap(20);
            loginPage.addRow(0,label);
            loginPage.addRow(1,loginField);
            loginPage.addRow(2,passwordField);
            loginPage.setAlignment(Pos.CENTER);

            //styles for login bottom box
            loginHBox.setPadding(new Insets(30, 30, 30, 30));
            loginHBox.setSpacing(80);
            loginHBox.setAlignment(Pos.CENTER);

            //adding fields to the pane
            loginHBox.getChildren().addAll(registryButton, loginButton);
            borderPane.setBottom(loginHBox);
            borderPane.setCenter(loginPage);
        }
        //

        //Registry page
        {
            String[] fieldOfRegistry = new String[]{"Imie","Nazwisko","Pesel","Login", "Hasło","Email","KontoBankowe"};
            HashMap<String,TextArea> fields = new HashMap<>();
            int rowNb=0;
            for(String fieldName : fieldOfRegistry)
            {
                TextArea textArea = new TextArea();
                textArea.setPrefHeight(20);
                textArea.setPrefWidth(300);
                textArea.setPromptText(fieldName);
                registryPage.addRow(rowNb,textArea);
                rowNb++;
                fields.put(fieldName,textArea);
            }


            //back to login button
            Button backButton = new Button("Cofnij się");
            backButton.setPrefSize(100, 20);
            backButton.setStyle(blueButtonStyle);
            backButton.setOnMouseEntered(e -> backButton.setStyle(blueHovered));
            backButton.setOnMouseExited(e -> backButton.setStyle(blueButtonStyle));
            backButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    borderPane.setCenter(loginPage);
                    borderPane.setBottom(loginHBox);
                }
            });
            //Registry button
            Button registryButtonRegistryPage = new Button("Zarejestruj się");
            registryButtonRegistryPage.setPrefSize(100, 20);
            registryButtonRegistryPage.setStyle(blueButtonStyle);
            registryButtonRegistryPage.setOnMouseEntered(e -> registryButtonRegistryPage.setStyle(blueHovered));
            registryButtonRegistryPage.setOnMouseExited(e -> registryButtonRegistryPage.setStyle(blueButtonStyle));

            registryButtonRegistryPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Session session = MainSessionFactory.openSession();
                    session.beginTransaction();
                    /*System.out.println(fields.get("Imie"));
                    System.out.println(fields.get("Nazwisko").getText());*/
                    session.save(new Klient(fields.get("Imie").getText(),fields.get("Nazwisko").getText(),Integer.parseInt(fields.get("Pesel").getText()),fields.get("Login").getText(),fields.get("Hasło").getText(),fields.get("Email").getText(),Integer.parseInt(fields.get("KontoBankowe").getText())));
                    fields.forEach((key,value)->{
                        value.clear();
                    });

                    borderPane.setCenter(loginPage);
                    borderPane.setBottom(loginHBox);
                    System.out.println("\nClients from the db:");
                    List<Klient> clientsFromDb = session.createQuery( "from Klient" ).list();
                    for ( var client : clientsFromDb) {
                        System.out.println(client.toString());
                    }
                    session.getTransaction().commit();
                    session.close();

                }
            });
            //profil page

            //

            registryHBox.getChildren().addAll(backButton,registryButtonRegistryPage);
            registryHBox.setPadding(new Insets(30, 30, 30, 30));
            registryHBox.setSpacing(80);
            registryHBox.setAlignment(Pos.CENTER);
            registryPage.setVgap(20);
            registryPage.setAlignment(Pos.CENTER);

        }
        stage.setScene(new Scene(borderPane, 1200, 750));
        stage.show();

    }
    public void profile(GridPane ProfilPage,BorderPane borderPane){
        {
            System.out.println("profil page");
            final String blueButtonStyle = "-fx-background-color: #C6D373 ";
            final String blueHovered = "-fx-background-color: #B6C649";

            final String greenButtonStyle = " -fx-background-color:#01BAEF ";
            final String greenHovered = " -fx-background-color:#63BBB6";
            Session session = MainSessionFactory.openSession();
            session.beginTransaction();
            if(loggedClient!=null)
            {

                HBox profilDetails = new HBox();
                profilDetails.setAlignment(Pos.CENTER_LEFT);
                profilDetails.setSpacing(10);
                VBox.setMargin(profilDetails, new Insets(20));

                HBox profilTitle = new HBox();
                profilTitle.setSpacing(10);
                VBox.setMargin(profilTitle, new Insets(20));

                List<Karnet> carnets = new ArrayList<>();
                Label labelProfil = new Label("Dane profilu:");
                labelProfil.setTextFill(Color.web("#e8edeb"));
                labelProfil.setStyle("-fx-font-size: 18px;");
                String accountDetails="";
                accountDetails+="Imie:     "+loggedClient.getName()+"\n\n";
                accountDetails+="Nazwisko: "+loggedClient.getLastName()+"\n\n";
                accountDetails+="Pesel:    "+loggedClient.getPesel()+"\n\n";
                accountDetails+="email:    "+loggedClient.getEmail()+"\n";
                Button label = new Button(accountDetails);
                label.setPrefSize(180, 150);
                label.setStyle(greenButtonStyle);
                profilTitle.getChildren().addAll(labelProfil);
                profilDetails.getChildren().addAll(label);

                Label labelKarnet = new Label("Karnety:");
                labelKarnet.setPrefWidth(250);
                labelKarnet.setTextFill(Color.web("#e8edeb"));
                ProfilPage.add(labelKarnet,2,0);
                labelKarnet.setStyle("-fx-font-size: 18px;");



                if(loggedClient.getPersonalTrainingList().size()!=0)
                {
                    Label labelTraining = new Label("Treningi:");
                    labelTraining.setPrefWidth(250);
                    labelTraining.setTextFill(Color.web("#e8edeb"));
                    labelTraining.setStyle("-fx-font-size: 18px;");
                    ProfilPage.add(labelTraining,4,0);


                    Button trainingLabel = new Button();
                    trainingLabel.setPrefSize(230, 250);
                    ProfilPage.add(trainingLabel,4,1);
                    trainingLabel.setStyle(greenButtonStyle);

                    ArrayList<String> options = new ArrayList<>();
                    for(var personalTraining: loggedClient.getPersonalTrainingList())
                    {
                        options.add("Trening nr"+personalTraining.getId());
                    }
                    ObservableList<String> optionsForChoiceBox =
                            FXCollections.observableArrayList(
                                    options
                            );
                    ComboBox priorityComboBox = new ComboBox(optionsForChoiceBox);
                    priorityComboBox.setValue("Wybierz trening");
                    priorityComboBox.setStyle(blueButtonStyle);
                    ProfilPage.add(priorityComboBox,4,0);
                    priorityComboBox.setOnAction((actionEvent -> {
                        int indexChoice = priorityComboBox.getSelectionModel().getSelectedIndex();
                        if(!(priorityComboBox.getValue().equals("Wybierz trening")) && priorityComboBox.getValue() != null)
                        {
                            TreningPersonalny selectedTraining=loggedClient.getPersonalTrainingList().get(indexChoice);
                            String trainingDetailsTxt="";
                            trainingDetailsTxt+="Koszt treningu:   "+selectedTraining.getCost()+"\n\n";
                            trainingDetailsTxt+="Data treningu:    "+selectedTraining.getDate()+"\n\n";
                            trainingDetailsTxt+="Klient imie:      "+selectedTraining.getKlient().getName()+"\n";
                            trainingDetailsTxt+="Klient Nazwisko:  "+selectedTraining.getKlient().getLastName()+"\n\n";
                            trainingDetailsTxt+="Trener imie:      "+selectedTraining.getTrainer().getLastName()+"\n";
                            trainingDetailsTxt+="Trener Nazwisko:  "+selectedTraining.getTrainer().getName()+"\n\n";
                            trainingDetailsTxt+="Siłownia nazwa:   "+selectedTraining.getTrainer().getSilownia().getNazwa()+"\n";
                            trainingDetailsTxt+="Siłownia adres:   "+selectedTraining.getTrainer().getSilownia().getAdres()+"\n\n";
                            trainingLabel.setText(trainingDetailsTxt);
                        }
                    }));

                    Label space2 = new Label("");
                    space2.setPrefWidth(200);
                    ProfilPage.add(space2,3,0);
                }
                /*Label gymLabel = new Label("Siłownie:");
                gymLabel.setTextFill(Color.web("#e8edeb"));
                gymLabel.setStyle("-fx-font-size: 18px;");
                gymLabel.setPrefWidth(250);

                Label trainerLabel = new Label("Trenerzy:");
                trainerLabel.setTextFill(Color.web("#e8edeb"));
                trainerLabel.setStyle("-fx-font-size: 18px;");
                trainerLabel.setPrefWidth(250);
                Label trainingLabel = new Label("Umówione treningi:");
                trainingLabel.setTextFill(Color.web("#e8edeb"));
                trainingLabel.setStyle("-fx-font-size: 18px;");
                trainingLabel.setPrefWidth(250);*/
                Label space = new Label("");
                space.setPrefWidth(200);
                ProfilPage.add(space,1,0);
                ProfilPage.add(profilTitle,0,0);
                ProfilPage.add(profilDetails,0,1);
                /*ProfilPage.add(gymLabel,4,0);
                ProfilPage.add(trainerLabel,6,0);
                ProfilPage.add(trainingLabel,8,0);*/

                VBox karnetsDetails = new VBox();
                karnetsDetails.setAlignment(Pos.CENTER_LEFT);
                karnetsDetails.setSpacing(40);

                VBox gymDetails = new VBox();
                gymDetails.setAlignment(Pos.CENTER_LEFT);
                gymDetails.setSpacing(40);

                VBox trainerDetails = new VBox();
                trainerDetails.setAlignment(Pos.CENTER_LEFT);
                trainerDetails.setSpacing(40);

                //Training button
                /*Button training = new Button("Umów się na trening");
                training.setPrefSize(100, 20);
                training.setStyle(greenHovered);*/
                int carnetNb=1;
                /*System.out.println("lalalala");*/
                for (var carnet : loggedClient.getKarnets())
                {
                    String carnetDetailsText="";
                    carnetDetailsText+="Typ:         "+carnet.getClass().getSimpleName()+"\n";
                    carnetDetailsText+="Nr:          "+carnet.getId()+"\n";
                    carnetDetailsText+="Opłacone:    "+carnet.getPaid()+"\n";
                    carnetDetailsText+="Koszt msc:   "+carnet.getMonthlyCost()+"\n";
                    carnetDetailsText+="Od kiedy:    "+carnet.getSinceWhen()+"\n";
                    carnetDetailsText+="Do kiedy:    "+carnet.getSinceTo()+"\n";
                    Button button = new Button(carnetDetailsText);
                    carnets.add(carnet);
                    button.setPrefSize(220, 130);
                    button.setStyle(blueButtonStyle);
                    button.setOnMouseEntered(e->button.setStyle(blueHovered));
                    button.setOnMouseExited(e->button.setStyle(blueButtonStyle));
                    karnetsDetails.getChildren().addAll(button);
                    carnetNb++;
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            gyms(borderPane,carnet);
                        }
                    });
                    /*button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            gymDetails.getChildren().removeAll();
                            gymDetails.getChildren().clear();
                            ProfilPage.getChildren().remove(gymDetails);
                            trainerDetails.getChildren().removeAll();
                            trainerDetails.getChildren().clear();
                            ProfilPage.getChildren().remove(trainerDetails);
                            button.setStyle(blueButtonStyle);
                            training.setStyle(greenHovered);
                            setTrener(null);
                            int rowNb=1;
                            for(Silownia gym : carnet.getCarnetsGymKarnets())
                            {
                                String title="";
                                title+="Nazwa: "+gym.getNazwa()+"\n";
                                title+="Adres: "+gym.getAdres()+"\n";
                                title+="Kod:   "+gym.getKodPocztowy()+"\n";
                                Button btn = new Button(title);
                                btn.setPrefSize(220, 100);
                                gymDetails.getChildren().addAll(btn);
                                btn.setStyle(blueHovered);
                                //

                                btn.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        trainerDetails.getChildren().removeAll();
                                        trainerDetails.getChildren().clear();
                                        ProfilPage.getChildren().remove(trainerDetails);
                                        training.setStyle(greenHovered);
                                        setTrener(null);
                                        btn.setStyle(blueButtonStyle);
                                        for(Trener trainer : gym.getTrainers())
                                        {
                                            String trainerDetailsText="";
                                            trainerDetailsText+="Imie:      "+trainer.getName()+"\n";
                                            trainerDetailsText+="Nazwisko:  "+trainer.getLastName()+"\n";
                                            trainerDetailsText+="Pesel:     "+trainer.getPesel()+"\n";
                                            trainerDetailsText+="Pensja:    "+trainer.getPensja()+"\n";
                                            trainerDetailsText+="Koszt:     "+trainer.getCostOfTraining()+"\n";
                                            Button btnTrainer = new Button(trainerDetailsText);
                                            btnTrainer.setPrefSize(220, 100);
                                            btnTrainer.setStyle(blueHovered);
                                            trainerDetails.getChildren().addAll(btnTrainer);

                                            btnTrainer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent actionEvent) {
                                                    setTrener(trainer);
                                                    training.setStyle(blueHovered);
                                                }
                                            });
                                        }
                                        ProfilPage.add(trainerDetails,6,1);
                                    }
                                });
                                rowNb++;

                            }
                            ProfilPage.add(gymDetails,4,1);
                        }
                    });*/



                    /**/
                }

                ProfilPage.add(karnetsDetails,2,1);
                /*Label space2 = new Label("");
                space2.setPrefWidth(70);
                ProfilPage.add(space2,2,0);*/

               /* Label space3 = new Label("");
                space3.setPrefWidth(10);
                ProfilPage.add(space3,3,0);

                Label space4 = new Label("");
                space4.setPrefWidth(10);
                ProfilPage.add(space4,4,0);*/

                session.getTransaction().commit();
                session.close();
                //ProfilPage

                //buttons

                /*training.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if((getTrener()!=null) && (getLoggedClient()!=null))
                        {
                            Session session = MainSessionFactory.openSession();
                            session.beginTransaction();
                            trainerDetails.getChildren().removeAll(trainerDetails);
                            trainerDetails.getChildren().clear();
                            gymDetails.getChildren().removeAll();
                            gymDetails.getChildren().clear();
                            ProfilPage.getChildren().remove(gymDetails);
                            TreningPersonalny treningPersonalny = new TreningPersonalny(getTrener().getCostOfTraining(),LocalDateTime.now().plusDays(7));
                            getTrener().addTraining(treningPersonalny);
                            getLoggedClient().addTraining(treningPersonalny);
                            String trainingDetailsText="";
                            trainingDetailsText+="Kiedy:   "+treningPersonalny.getDate()+"\n";
                            trainingDetailsText+="Koszt:   "+treningPersonalny.getCost()+"\n";
                            Button labelOfTrainingText = new Button(trainingDetailsText);
                            labelOfTrainingText.setPrefWidth(220);
                            labelOfTrainingText.setStyle(greenButtonStyle);
                            *//*System.out.println(getLoggedClient().getPersonalTrainingList().size());*//*
                            int howMany=getLoggedClient().getPersonalTrainingList().size();
                            ProfilPage.add(labelOfTrainingText,8,howMany);
                            training.setStyle(greenHovered);
                            setTrener(null);
                            session.save(treningPersonalny);
                            session.getTransaction().commit();
                            session.close();
                        }else{
                            System.out.println("Wybierz karnet oraz siłownie oraz trenera");
                        }
                    }
                });*/

                //buy carnet button
                Button buyCarnet = new Button("Kup karnet");
                buyCarnet.setPrefSize(100, 20);
                buyCarnet.setStyle(greenButtonStyle);
                /*backkButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ProfilPage.getChildren().removeAll(gymDetails);
                        borderPane.setCenter(loginPage);
                        borderPane.setBottom(loginHBox);
                        logOut();
                    }
                });*/
                //back to login button
                Button backkButton = new Button("Wyloguj się");
                backkButton.setPrefSize(100, 20);
                backkButton.setOnMouseEntered(e -> backkButton.setStyle(blueHovered));
                backkButton.setOnMouseExited(e -> backkButton.setStyle(blueButtonStyle));
                backkButton.setStyle(blueButtonStyle);
                backkButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ProfilPage.getChildren().removeAll(gymDetails);
                        profilHBox.getChildren().removeAll();
                        borderPane.setCenter(loginPage);
                        borderPane.setBottom(loginHBox);
                        profilHBox.getChildren().clear();
                        logOut();
                    }
                });


                profilHBox.getChildren().addAll(backkButton, buyCarnet);
                //configuration
                ProfilPage.setVgap(20);
                ProfilPage.setAlignment(Pos.CENTER);
                profilHBox.setPadding(new Insets(30, 30, 30, 30));
                profilHBox.setSpacing(80);
                profilHBox.setAlignment(Pos.CENTER);
            }
            else{
                System.out.println("nie ma takiego użytkownika");
            }

        }
    }



    public void gyms(BorderPane borderPane, Karnet carnet) {
        gymHBox.getChildren().clear();
        profilHBox.getChildren().clear();
        ProfilPage.setAlignment(Pos.CENTER);
        gymHBox.setPadding(new Insets(30, 30, 30, 30));
        gymHBox.setSpacing(80);
        gymHBox.setAlignment(Pos.CENTER);
        final String blueButtonStyle = "-fx-background-color: #C6D373 ";
        final String blueHovered = "-fx-background-color: #B6C649";

        final String greenButtonStyle = " -fx-background-color:#01BAEF ";
        final String greenHovered = " -fx-background-color:#63BBB6";

        HBox gymDetails = new HBox();
        gymDetails.setAlignment(Pos.CENTER_LEFT);
        gymDetails.setSpacing(10);

        Label gymLabel = new Label("Siłownie dla karnetu nr: "+carnet.getId());
        gymLabel.setTextFill(Color.web("#e8edeb"));
        gymLabel.setStyle("-fx-font-size: 18px;");
        gymLabel.setPrefWidth(250);

        VBox.setMargin(gymDetails, new Insets(20));

        for(var gym : carnet.getCarnetsGymKarnets())
        {
            String gymDetailsTxt="";
            gymDetailsTxt+="Typ:      "+gym.getClass().getSimpleName()+"\n\n";
            gymDetailsTxt+="Nazwa:         "+gym.getNazwa()+"\n\n";
            gymDetailsTxt+="Adres:         "+gym.getAdres()+"\n\n";
            gymDetailsTxt+="Kod pocztowy:  "+gym.getKodPocztowy()+"\n\n";
            Button label = new Button(gymDetailsTxt);
            label.setStyle(blueButtonStyle);
            label.setOnMouseEntered(e-> label.setStyle(blueHovered));
            label.setOnMouseExited(e-> label.setStyle(blueButtonStyle));
            gymDetails.getChildren().addAll(label);
            label.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                trainers(borderPane,gym,carnet);
            }
            });
        }


        //back to login button
        Button backkButton = new Button("Cofnij się");
        backkButton.setPrefSize(100, 20);
        backkButton.setOnMouseEntered(e -> backkButton.setStyle(blueHovered));
        backkButton.setOnMouseExited(e -> backkButton.setStyle(blueButtonStyle));
        backkButton.setStyle(blueButtonStyle);
        backkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GymPage.getChildren().clear();
                borderPane.setCenter(ProfilPage);
                borderPane.setBottom(profilHBox);
                profile(ProfilPage,borderPane);
            }
        });
        gymHBox.getChildren().addAll(backkButton);

        this.GymPage.add(gymLabel,0,0);
        this.GymPage.add(gymDetails,0,1);
        this.GymPage.setVgap(20);
        this.GymPage.setAlignment(Pos.CENTER);
        borderPane.setCenter(this.GymPage);
        borderPane.setBottom(gymHBox);
    }
    public void trainers(BorderPane borderPane, Silownia gym, Karnet carnet) {
        confirmationHBox.getChildren().clear();
        trainersHBox.getChildren().clear();
        TrainersPage.getChildren().clear();
        confirmationHBox.setAlignment(Pos.CENTER);
        confirmationHBox.setPadding(new Insets(30, 30, 30, 30));
        confirmationHBox.setSpacing(80);
        confirmationHBox.setAlignment(Pos.CENTER);
        final String blueButtonStyle = "-fx-background-color: #C6D373 ";
        final String blueHovered = "-fx-background-color: #B6C649";

        final String greenButtonStyle = " -fx-background-color:#01BAEF ";
        final String greenHovered = " -fx-background-color:#63BBB6";

        HBox trainersDetails = new HBox();
        trainersDetails.setAlignment(Pos.CENTER_LEFT);
        trainersDetails.setSpacing(10);

        Label trainersLabel = new Label("Tenerzy dla siłowni o nazwie: "+gym.getNazwa());
        trainersLabel.setTextFill(Color.web("#e8edeb"));
        trainersLabel.setStyle("-fx-font-size: 18px;");
        trainersLabel.setPrefWidth(400);

        VBox.setMargin(trainersDetails, new Insets(20));

        for(var trainer : gym.getTrainers())
        {
            String trainerDetailsTxt="";
            trainerDetailsTxt+="Typ:              "+trainer.getClass().getSimpleName()+"\n\n";
            trainerDetailsTxt+="Imie:             "+trainer.getName()+"\n\n";
            trainerDetailsTxt+="Nazwisko:         "+trainer.getLastName()+"\n\n";
            trainerDetailsTxt+="Koszt Treningu:   "+trainer.getCostOfTraining()+"\n\n";
            Button label = new Button(trainerDetailsTxt);
            label.setStyle(blueButtonStyle);
            label.setOnMouseEntered(e-> label.setStyle(blueHovered));
            label.setOnMouseExited(e-> label.setStyle(blueButtonStyle));
            trainersDetails.getChildren().addAll(label);
            label.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GymPage.getChildren().clear();
                TrainersPage.getChildren().clear();
                trainersHBox.getChildren().clear();
                confirmation(borderPane,trainer,gym,carnet);
            }
        });
        }


        //back to login button
        Button backkButton = new Button("Cofnij się");
        backkButton.setPrefSize(100, 20);
        backkButton.setOnMouseEntered(e -> backkButton.setStyle(blueHovered));
        backkButton.setOnMouseExited(e -> backkButton.setStyle(blueButtonStyle));
        backkButton.setStyle(blueButtonStyle);
        backkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GymPage.getChildren().clear();
                TrainersPage.getChildren().clear();
                trainersHBox.getChildren().clear();
                gyms(borderPane,carnet);
            }
        });
        trainersHBox.getChildren().addAll(backkButton);

        this.TrainersPage.add(trainersLabel,0,0);
        this.TrainersPage.add(trainersDetails,0,1);
        this.TrainersPage.setVgap(20);
        this.TrainersPage.setAlignment(Pos.CENTER);
        trainersHBox.setPadding(new Insets(30, 30, 30, 30));
        trainersHBox.setSpacing(80);
        trainersHBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(this.TrainersPage);
        borderPane.setBottom(trainersHBox);
    }


    public void confirmation(BorderPane borderPane, Trener trainer ,Silownia gym, Karnet carnet) {
        gymHBox.getChildren().clear();
        ConfirmationPage.getChildren().clear();
        HBox confirmationHBox = new HBox();
        ConfirmationPage.setAlignment(Pos.CENTER);
        confirmationHBox.setPadding(new Insets(30, 30, 30, 30));
        confirmationHBox.setSpacing(80);
        confirmationHBox.setAlignment(Pos.CENTER);
        final String blueButtonStyle = "-fx-background-color: #C6D373 ";
        final String blueHovered = "-fx-background-color: #B6C649";

        final String greenButtonStyle = " -fx-background-color:#01BAEF ";
        final String greenHovered = " -fx-background-color:#63BBB6";

        HBox trainersDetails = new HBox();
        trainersDetails.setAlignment(Pos.CENTER_LEFT);
        trainersDetails.setSpacing(10);

        Label trainersLabel = new Label("Potwierdzenie treningu");
        trainersLabel.setTextFill(Color.web("#e8edeb"));
        trainersLabel.setStyle("-fx-font-size: 18px;");
        trainersLabel.setPrefWidth(400);

        VBox.setMargin(trainersDetails, new Insets(20));
            //Karnet
            String carnetDetailsTxt="";
            carnetDetailsTxt+="Typ:            "+carnet.getClass().getSimpleName()+"\n\n";
            carnetDetailsTxt+="Numer:            "+carnet.getId()+"\n\n";
            carnetDetailsTxt+="Od kiedy:         "+carnet.getSinceWhen()+"\n\n";
            carnetDetailsTxt+="Do kiedy:         "+carnet.getSinceTo()+"\n\n";
            Button label = new Button(carnetDetailsTxt);
            label.setStyle(blueButtonStyle);
            label.setOnMouseEntered(e-> label.setStyle(blueHovered));
            label.setOnMouseExited(e-> label.setStyle(blueButtonStyle));
            //Silownia
            String gymDetailsTxt="";
            gymDetailsTxt+="Typ:            "+gym.getClass().getSimpleName()+"\n\n";
            gymDetailsTxt+="Nazwa:             "+gym.getNazwa()+"\n\n";
            gymDetailsTxt+="Adres:         "+gym.getAdres()+"\n\n";
            gymDetailsTxt+="Kod pocztowy:   "+gym.getKodPocztowy()+"\n\n";
            Button labelGym = new Button(gymDetailsTxt);
            labelGym.setStyle(blueButtonStyle);
            labelGym.setOnMouseEntered(e-> labelGym.setStyle(blueHovered));
            labelGym.setOnMouseExited(e-> labelGym.setStyle(blueButtonStyle));

            //trener
            String trainerDetailsTxt="";
            trainerDetailsTxt+="Typ:            "+trainer.getClass().getSimpleName()+"\n\n";
            trainerDetailsTxt+="Imie:             "+trainer.getName()+"\n\n";
            trainerDetailsTxt+="Nazwisko:         "+trainer.getLastName()+"\n\n";
            trainerDetailsTxt+="Koszt Treningu:   "+trainer.getCostOfTraining()+"\n\n";
            Button labelTrainer = new Button(trainerDetailsTxt);
            labelTrainer.setStyle(blueButtonStyle);
            labelTrainer.setOnMouseEntered(e-> label.setStyle(blueHovered));
            labelTrainer.setOnMouseExited(e-> label.setStyle(blueButtonStyle));


            trainersDetails.getChildren().addAll(label,labelGym,labelTrainer);


        //back to login button
        Button backkButton = new Button("Cofnij się");
        backkButton.setPrefSize(100, 20);
        backkButton.setOnMouseEntered(e -> backkButton.setStyle(blueHovered));
        backkButton.setOnMouseExited(e -> backkButton.setStyle(blueButtonStyle));
        backkButton.setStyle(blueButtonStyle);
        backkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TrainersPage.getChildren().clear();
                ConfirmationPage.getChildren().clear();
                confirmationHBox.getChildren().clear();
                gyms(borderPane,carnet);
            }
        });

        //back to login button
        Button confButtonn = new Button("Potwierdź trening");
        confButtonn.setPrefSize(100, 20);
        confButtonn.setOnMouseEntered(e -> confButtonn.setStyle(greenHovered));
        confButtonn.setOnMouseExited(e -> confButtonn.setStyle(greenButtonStyle));
        confButtonn.setStyle(greenButtonStyle);
        confButtonn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TreningPersonalny treningPersonalny = new TreningPersonalny(trainer.getCostOfTraining(), LocalDateTime.now().plusDays(7));
                treningPersonalny.setKlient(loggedClient);
                treningPersonalny.setTrainer(trainer);
                loggedClient.addTraining(treningPersonalny);
                trainer.addTraining(treningPersonalny);

                Session session = MainSessionFactory.openSession();
                session.beginTransaction();
                session.save(treningPersonalny);
                session.getTransaction().commit();
                session.close();
                TrainersPage.getChildren().clear();
                ConfirmationPage.getChildren().clear();
                confirmationHBox.getChildren().clear();
                borderPane.setCenter(ProfilPage);
                borderPane.setBottom(profilHBox);
                profile(ProfilPage,borderPane);

            }
        });
        confirmationHBox.getChildren().addAll(backkButton,confButtonn);

        this.ConfirmationPage.add(trainersLabel,0,0);
        this.ConfirmationPage.add(trainersDetails,0,1);
        this.ConfirmationPage.setVgap(20);
        this.ConfirmationPage.setAlignment(Pos.CENTER);
        borderPane.setCenter(this.ConfirmationPage);
        borderPane.setBottom(confirmationHBox);
    }


   /**/

    public void setLoggedClient(Klient loggedClient) {
        this.loggedClient = loggedClient;
    }

    public Klient getLoggedClient() {
        return loggedClient;
    }

    public String getLogin() {
        return login;
    }

    public void logOut(){
        this.login=null;
        this.password=null;
    }
    public String getPassword() {
        return password;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Trener getTrener() {
        return trener;
    }

}

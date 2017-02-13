package Damas;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static Damas.Damas.HEIGHT;
import static Damas.Damas.TAMANY;
import static Damas.Damas.WIDTH;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
public static final int TAMANY = 80;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public Tablero[][] Tabla = new Tablero[WIDTH][HEIGHT];

    public Group TableroGroup = new Group();
    public Group PiezaGroup = new Group();
    public static Font font;
    public MenuBox menu;

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TAMANY, HEIGHT * TAMANY);
        root.getChildren().addAll(TableroGroup, PiezaGroup);
/*
        try (InputStream is = Files.newInputStream(Paths.get("src\\damas\\damasimg.jpg"));)
       {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(1066);
            img.setFitHeight(650);

            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("No carga imagen");
        }
*/
        
        MenuItem itemNewGame = new MenuItem("MODO CAMPAÑA");
        itemNewGame.setOnMouseClicked(event->   {
            
                if (menu.isVisible()) {
                    menu.hide();
                }
                else {
                    menu.show();
                }
            
        });
        
        MenuItem itemOptions = new MenuItem("MULTIJUGADOR");
        itemOptions.setOnMouseClicked(event2 ->  {
            Label secondLabel = new Label("Not yet implemented" );
            
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            
            Scene secondScene = new Scene(secondaryLayout, 210, 110);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Multiplayer");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(230);        
            secondStage.setMaxHeight(110);
            secondStage.setMinWidth(230);        
            secondStage.setMinHeight(110);
            secondStage.show();
            
        });
        MenuItem itemCredits = new MenuItem("CREDITS");
        itemCredits.setOnMouseClicked(event1 -> {
            Label secondLabel = new Label("Hecho por Marc llobera, \n Victor Marchante y \n Sheng Ye " );
            
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            
            Scene secondScene = new Scene(secondaryLayout, 210, 110);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Creditos");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(210);        
            secondStage.setMaxHeight(110);
            secondStage.setMinWidth(210);        
            secondStage.setMinHeight(110);
            secondStage.show();
            
        });
        
        MenuItem itemQuit = new MenuItem("SALIR");
        itemQuit.setOnMouseClicked(event -> System.exit(0));
        
        menu = new MenuBox("LAS DAMAS",
                itemNewGame,
                itemOptions,
                itemCredits,
                new MenuItem(""),
                itemQuit);

        root.getChildren().add(menu);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tablero Tablero = new Tablero((x + y) % 2 == 0, x, y);
                Tabla[x][y] = Tablero;

                TableroGroup.getChildren().add(Tablero);

                Pieza Pieza = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    Pieza = makePieza(TiposPieza.NEGRO, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    Pieza = makePieza(TiposPieza.BLANCO, x, y);
                }

                if (Pieza != null) {
                    Tablero.setPieza(Pieza);
                    PiezaGroup.getChildren().add(Pieza);
                }
            }
        }

        return root;
    }
   /* public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1000, 650);

        try (InputStream is = Files.newInputStream(Paths.get("src\\damas\\damasimg.jpg"));)
       {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(1066);
            img.setFitHeight(650);

            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("No carga imagen");
        }

        
        MenuItem itemNewGame = new MenuItem("MODO CAMPAÑA");
        itemNewGame.setOnMouseClicked(event->   {
            Label secondLabel = new Label("Not yet implemented" );
            
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            
            Scene secondScene = new Scene(secondaryLayout, 210, 110);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Campaña");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(230);        
            secondStage.setMaxHeight(110);
            secondStage.setMinWidth(230);        
            secondStage.setMinHeight(110);
            secondStage.show();
            
        });
        
        MenuItem itemOptions = new MenuItem("MULTIJUGADOR");
        itemOptions.setOnMouseClicked(event2 ->  {
            Label secondLabel = new Label("Not yet implemented" );
            
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            
            Scene secondScene = new Scene(secondaryLayout, 210, 110);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Multiplayer");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(230);        
            secondStage.setMaxHeight(110);
            secondStage.setMinWidth(230);        
            secondStage.setMinHeight(110);
            secondStage.show();
            
        });
        MenuItem itemCredits = new MenuItem("CREDITS");
        itemCredits.setOnMouseClicked(event1 -> {
            Label secondLabel = new Label("Hecho por Marc llobera, \n Victor Marchante y \n Sheng Ye " );
            
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            
            Scene secondScene = new Scene(secondaryLayout, 210, 110);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Creditos");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(210);        
            secondStage.setMaxHeight(110);
            secondStage.setMinWidth(210);        
            secondStage.setMinHeight(110);
            secondStage.show();
            
        });
        
        MenuItem itemQuit = new MenuItem("SALIR");
        itemQuit.setOnMouseClicked(event -> System.exit(0));
        
        menu = new MenuBox("LAS DAMAS",
                itemNewGame,
                itemOptions,
                itemCredits,
                new MenuItem(""),
                itemQuit);

        root.getChildren().add(menu);
        return root;
    }*/
    
    public ResultadoMovimiento tryMove(Pieza Pieza, int newX, int newY) {
        if (Tabla[newX][newY].hasPieza() || (newX + newY) % 2 == 0) {
            return new ResultadoMovimiento(TiposMovimiento.NONE);
        }

        int x0 = toTabla(Pieza.getOldX());
        int y0 = toTabla(Pieza.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == Pieza.getType().moveDir) {
            return new ResultadoMovimiento(TiposMovimiento.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == Pieza.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (Tabla[x1][y1].hasPieza() && Tabla[x1][y1].getPieza().getType() != Pieza.getType()) {
                return new ResultadoMovimiento(TiposMovimiento.KILL, Tabla[x1][y1].getPieza());
            }
        }

        return new ResultadoMovimiento(TiposMovimiento.NONE);
    }
    public int toTabla(double pixel) {
        return (int)(pixel + TAMANY / 2) / TAMANY;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (menu.isVisible()) {
                    menu.hide();
                }
                else {
                    menu.show();
                }
            }
        });
        primaryStage.setTitle("Damas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Pieza makePieza(TiposPieza type, int x, int y) {
        Pieza Pieza = new Pieza(type, x, y);

        Pieza.setOnMouseReleased(e -> {
            int newX = toTabla(Pieza.getLayoutX());
            int newY = toTabla(Pieza.getLayoutY());

            ResultadoMovimiento result = tryMove(Pieza, newX, newY);

            int x0 = toTabla(Pieza.getOldX());
            int y0 = toTabla(Pieza.getOldY());

            switch (result.getType()) {
                case NONE:
                    Pieza.abortMove();
                    break;
                case NORMAL:
                    Pieza.move(newX, newY);
                    Tabla[x0][y0].setPieza(null);
                    Tabla[newX][newY].setPieza(Pieza);
                    break;
                case KILL:
                    Pieza.move(newX, newY);
                    Tabla[x0][y0].setPieza(null);
                    Tabla[newX][newY].setPieza(Pieza);

                    Pieza otherPieza = result.getPieza();
                    Tabla[toTabla(otherPieza.getOldX())][toTabla(otherPieza.getOldY())].setPieza(null);
                    PiezaGroup.getChildren().remove(otherPieza);
                    break;
            }
        });

        return Pieza;
    }

/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (menu.isVisible()) {
                    menu.hide();
                }
                else {
                    menu.show();
                }
            }
        });
        primaryStage.setTitle("Damas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    public static class MenuBox extends StackPane {
        public MenuBox(String title, MenuItem... items) {
            Rectangle bg = new Rectangle(639.4, 650);
            bg.setOpacity(0.2);

            DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
            shadow.setSpread(0.8);

            bg.setEffect(shadow);

            Text text = new Text(title + "");
            text.setFont(font);
            text.setFill(Color.WHITE);

            Line hSep = new Line();
            hSep.setEndX(250);
            hSep.setStroke(Color.DARKGREEN);
            hSep.setOpacity(0.4);

            Line vSep = new Line();
            vSep.setStartX(300);
            vSep.setEndX(300);
            vSep.setEndY(600);
            vSep.setStroke(Color.DARKGREEN);
            vSep.setOpacity(0.4);

            VBox vbox = new VBox();
            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setPadding(new Insets(60, 0, 0, 0));
            vbox.getChildren().addAll(text, hSep);
            vbox.getChildren().addAll(items);

            setAlignment(Pos.TOP_RIGHT);
            getChildren().addAll(bg, vSep, vbox);
        }

        public void show() {
            setVisible(true);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1.5), this);
            tt.setToX(0);
            tt.play();
        }

        public void hide() {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1.5), this);
            tt.setToX(-639.4);
            tt.setOnFinished(event -> setVisible(false));
            tt.play();
        }
    }

    public static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(300, 24);

            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.BLACK),
                    new Stop(0.2, Color.DARKGREY)
            });

            bg.setFill(gradient);
            bg.setVisible(false);
            bg.setEffect(new DropShadow(5, 0, 5, Color.BLACK));

            Text text = new Text(name + "      ");
            text.setFill(Color.LIGHTGREY);
            text.setFont(Font.font(20));

            setAlignment(Pos.CENTER_RIGHT);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setVisible(true);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(event -> {
                bg.setVisible(false);
                text.setFill(Color.LIGHTGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

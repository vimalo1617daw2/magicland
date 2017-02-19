package Damas;

import static Damas.Main.HEIGHT;
import static Damas.Main.TAMANY;
import static Damas.Main.WIDTH;
import java.io.File;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main extends Application {
        int movimientoBlanco = 0;
        int movimientoNegro = 2;
    public static final int TAMANY = 80;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public Tablero[][] Tabla = new Tablero[WIDTH][HEIGHT];

    public Group TableroGroup = new Group();
    public Group PiezaGroup = new Group();
    public static Font font;
    public CajaMenu menu;

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TAMANY, HEIGHT * TAMANY);
        root.getChildren().addAll(TableroGroup, PiezaGroup);

        ItemsMenu itemContador = new ItemsMenu("CONTADOR");
        itemContador.setOnMouseClicked(event3 -> {
            Label secondLabel = new Label("Not yet implemented");
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            Scene secondScene = new Scene(secondaryLayout, 400, 110);
            Stage secondStage = new Stage();
            secondStage.setTitle("CONTADOR");
            secondStage.setScene(secondScene);
            secondStage.setMaxWidth(230);
            secondStage.setMaxHeight(110);
            secondStage.show();

        });

        ItemsMenu itemNewGame = new ItemsMenu("MODO CAMPAÑA");
        itemNewGame.setOnMouseClicked(event -> {

            if (menu.isVisible()) {
                menu.hide();
            } else {
                menu.show();
            }

        });

        ItemsMenu itemOptions = new ItemsMenu("MULTIJUGADOR");
        itemOptions.setOnMouseClicked(event2 -> {
            Label secondLabel = new Label("Not yet implemented");

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

        ItemsMenu itemCredits = new ItemsMenu("CREDITS");
        itemCredits.setOnMouseClicked(event1 -> {
            Label secondLabel = new Label("Hecho por Marc llobera, \n Victor Marchante y \n Sheng Ye ");
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

        ItemsMenu itemQuit = new ItemsMenu("SALIR");
        itemQuit.setOnMouseClicked(event -> System.exit(0));

        menu = new CajaMenu("LAS DAMAS",
                itemNewGame,
                itemContador,
                itemOptions,
                itemCredits,
                new ItemsMenu(""),
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

    public ResultadoMovimiento tryMove(Pieza Pieza, int newX, int newY) {

        MovimientoA blanco =  new MovimientoA(movimientoBlanco);
        MovimientoA negro =  new MovimientoA(movimientoNegro);
        int x0 = toTabla(Pieza.getOldX());
        int y0 = toTabla(Pieza.getOldY());
        
        if(blanco.compareTo(negro)<movimientoNegro){
            ClaseGenerica<String> moveBlanco = new ClaseGenerica<String>("moveBlanco");
            moveBlanco.moveBlanco();
            movimientoBlanco = movimientoBlanco + 1;
            movimientoNegro = movimientoNegro - 1;
            System.out.println("movimientoblanco" + movimientoBlanco);
        }else if(negro.compareTo(blanco)>movimientoBlanco){
           ClaseGenerica<String> moveNegro = new ClaseGenerica<String>("moveNegro");
            moveNegro.moveNegro();
            movimientoNegro = movimientoNegro + 1;
            movimientoBlanco = movimientoBlanco - 1;
            System.out.println("movimientoNegro" + movimientoNegro);
        }
        
        if (Tabla[newX][newY].hasPieza() || (newX + newY) % 2 == 0) {
            return new ResultadoMovimiento(TiposMovimiento.NONE);
        }

        if (Math.abs(newX - x0) == 1 && newY - y0 == Pieza.getType().moveDir) {
            return new ResultadoMovimiento(TiposMovimiento.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == Pieza.getType().moveDir * 2) {
            
            
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (Tabla[x1][y1].hasPieza() && Tabla[x1][y1].getPieza().getType() != Pieza.getType()) {

                //if (Pieza1.compareTo(Pieza2) ) {
                   // ClaseGenerica<String> muerte1 = new ClaseGenerica<String>("muerte negro");
                  //  muerte1.muerte1();
                //} else {
                  //  ClaseGenerica<String> muerte2 = new ClaseGenerica<String>("muerte blancp");
                 //   muerte2.muerte2();
                //}
               // return new ResultadoMovimiento(TiposMovimiento.KILL,Tabla[x1][y1].getPieza());

                 // ClaseGenerica<String> muerte1 = new
                 // ClaseGenerica<String>("muerte negro"); muerte1.muerte1();
                 // ClaseGenerica<String> muerte2 = new
                  //ClaseGenerica<String>("muerte blanco"); muerte2.muerte2();
                 
                return new ResultadoMovimiento(TiposMovimiento.KILL, Tabla[x1][y1].getPieza());
            }
        }
        
        return new ResultadoMovimiento(TiposMovimiento.NONE);
    }

    public int toTabla(double pixel) {
        return (int) (pixel + TAMANY / 2) / TAMANY;
    }

    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (menu.isVisible()) {
                    menu.hide();
                } else {
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
                    ClaseGenerica<String> nulo = new ClaseGenerica<String>("nulo");
                    nulo.nulo();
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

                    ClaseGenerica<String> matar = new ClaseGenerica<String>("Test");
                    matar.classType();
                    // Label secondLabel = new Label("Not yet implemented");

                    break;
            }
        });

        return Pieza;
    }

    public static class CajaMenu extends StackPane {

        public CajaMenu(String title, ItemsMenu... items) {
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

    public static class ItemsMenu extends StackPane {

        public ItemsMenu(String name) {
            Rectangle bg = new Rectangle(300, 24);

            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
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

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
                
                Document docu = docBuilder.newDocument();
		Element rootElemen = docu.createElement("compania");
		docu.appendChild(rootElemen);
                
                // staff elements
		Element staf = docu.createElement("Staf");
		rootElemen.appendChild(staf);

		// set attribute to staff element
		Attr att = docu.createAttribute("i");
		att.setValue("1");
		staf.setAttributeNode(att);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element firstnam = docu.createElement("firstnam");
		firstnam.appendChild(docu.createTextNode("yong"));
		staf.appendChild(firstnam);

		// lastname elements
		Element lastnam = docu.createElement("lastnam");
		lastnam.appendChild(docu.createTextNode("mook kim"));
		staf.appendChild(lastnam);

		// nickname elements
		Element nicknam = docu.createElement("nicknam");
		nicknam.appendChild(docu.createTextNode("mkyong"));
		staf.appendChild(nicknam);

		// salary elements
		Element salar = docu.createElement("salar");
		salar.appendChild(docu.createTextNode("100000"));
		staf.appendChild(salar);
                
                ////////////////////////////////////////////////////////
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("company");
		doc.appendChild(rootElement);

		// staff elements
		Element staff = doc.createElement("Staff");
		rootElement.appendChild(staff);

		// set attribute to staff element
		Attr attr = doc.createAttribute("id");
		attr.setValue("1");
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element firstname = doc.createElement("firstname");
		firstname.appendChild(doc.createTextNode("yong"));
		staff.appendChild(firstname);

		// lastname elements
		Element lastname = doc.createElement("lastname");
		lastname.appendChild(doc.createTextNode("mook kim"));
		staff.appendChild(lastname);

		// nickname elements
		Element nickname = doc.createElement("nickname");
		nickname.appendChild(doc.createTextNode("mkyong"));
		staff.appendChild(nickname);

		// salary elements
		Element salary = doc.createElement("salary");
		salary.appendChild(doc.createTextNode("100000"));
		staff.appendChild(salary);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
                DOMSource sourc = new DOMSource(docu);
		StreamResult result = new StreamResult(new File("jugador.xml"));
                StreamResult resulta = new StreamResult(new File("jugador2.xml"));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
                transformer.transform(sourc, resulta);

		System.out.println("File saved!");

        launch(args);
        

    }
}

class ClaseGenerica<T> {

    T obj;

    public ClaseGenerica(T o) {
        obj = o;
    }

    public static void classType() {

        Label secondLabel = new Label("pieza muerta ");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 400, 110);
        Stage secondStage = new Stage();
        secondStage.setTitle("pieza Muerta");
        secondStage.setScene(secondScene);
        secondStage.setMaxWidth(230);
        secondStage.setMaxHeight(110);
        secondStage.show();
    }

    public static void nulo() {

        Label secondLabel = new Label("error movimiento");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 400, 110);
        Stage secondStage = new Stage();
        secondStage.setTitle("pieza mismo sitio");
        secondStage.setScene(secondScene);
        secondStage.setMaxWidth(230);
        secondStage.setMaxHeight(110);
        secondStage.show();
    }

    public static void moveNegro() {

        Label secondLabel = new Label("moveNegro");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 400, 110);
        Stage secondStage = new Stage();
        secondStage.setTitle("moveNegro");
        secondStage.setScene(secondScene);
        secondStage.setMaxWidth(230);
        secondStage.setMaxHeight(110);
        secondStage.show();
    }

    public static void moveBlanco() {

        Label secondLabel = new Label("moveBlanco");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 400, 110);
        Stage secondStage = new Stage();
        secondStage.setTitle("moveBlanco");
        secondStage.setScene(secondScene);
        secondStage.setMaxWidth(230);
        secondStage.setMaxHeight(110);
        secondStage.show();
    }
}

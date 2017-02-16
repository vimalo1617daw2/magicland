package Damas;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tablero extends Rectangle {

    public Pieza Pieza;

    public boolean hasPieza() {
        return Pieza != null;
    }

    public Pieza getPieza() {
        return Pieza;
    }

    public void setPieza(Pieza Pieza) {
        this.Pieza = Pieza;
    }

    public Tablero(boolean light, int x, int y) {
        setWidth(Main.TAMANY);
        setHeight(Main.TAMANY);

        relocate(x * Main.TAMANY, y * Main.TAMANY);

        setFill(light ? Color.valueOf("#ffffff") : Color.valueOf("#424242"));
    }
}

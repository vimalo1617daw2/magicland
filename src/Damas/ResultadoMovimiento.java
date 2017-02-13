package Damas;

public class ResultadoMovimiento {

    public TiposMovimiento type;

    public TiposMovimiento getType() {
        return type;
    }

    public Pieza Pieza;

    public Pieza getPieza() {
        return Pieza;
    }

    public ResultadoMovimiento(TiposMovimiento type) {
        this(type, null);
    }

    public ResultadoMovimiento(TiposMovimiento type, Pieza Pieza) {
        this.type = type;
        this.Pieza = Pieza;
    }
}

package Aplicacao;

public class Validation extends Exception {

    public Validation(String message) {
        super(message);
    }

    public Validation(Throwable t) {
        super(t);
    }
}

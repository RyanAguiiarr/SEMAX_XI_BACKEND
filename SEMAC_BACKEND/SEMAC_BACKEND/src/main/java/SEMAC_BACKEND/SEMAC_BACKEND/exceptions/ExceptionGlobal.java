package SEMAC_BACKEND.SEMAC_BACKEND.exceptions;

public class ExceptionGlobal {

    public static class AlunoJaInscritoException extends RuntimeException {
        public AlunoJaInscritoException(String message) {
            super(message);
        }
    }

    // PalestraLotadaException.java
    public static class PalestraLotadaException extends RuntimeException {
        public PalestraLotadaException(String message) {
            super(message);
        }
    }

    public static class PalestraNaoEncontradaException extends RuntimeException {
        public PalestraNaoEncontradaException(String message) {
            super(message);
        }
    }


}

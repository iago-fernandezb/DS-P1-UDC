package e2;

import java.util.HashSet;
import java.util.Set;

public class KeyPad {
    private char[][] keyboard;
    private int filas;
    private int columnas;

    public KeyPad(int filasaux, int columnasaux, char[][] keyboardaux) {
        this.filas = filasaux;
        this.columnas = columnasaux;
        this.keyboard = keyboardaux;
    }

    public static boolean isValidKeyPad(char[][] keypad) {
        if (keypad == null) {
            return false;
        }

        String validos = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Set<Character> encontrados = new HashSet<>();

        int expectedLength = -1;
        boolean isHorizontal = true, isVertical = true;

        for (int i = 0; i < keypad.length; i++) {
            if (keypad[i] == null) {
                return false;
            }
            if (expectedLength == -1) {
                expectedLength = keypad[i].length;
            }
            if (keypad[i].length != expectedLength) {
                return false;
            }

            for (int j = 0; j < keypad[i].length; j++) {
                char actual = keypad[i][j];
                boolean esValido = validos.indexOf(actual) != -1;
                if (!esValido) {
                    System.out.println("Carácter inválido encontrado en la posición (" + i + ", " + j + "): " + actual);
                    return false;
                }
                if (encontrados.contains(actual)) {
                    System.out.println("Carácter duplicado encontrado en la posición (" + i + ", " + j + "): " + actual);
                    return false;
                } else {
                    encontrados.add(actual);
                }
                if (i == 0 && j > 0) {
                    char anterior = keypad[i][j - 1];
                    if (validos.indexOf(anterior) + 1 != validos.indexOf(actual)) {
                        isHorizontal = false;
                    }
                }
                if (i > 0 && j == 0) {
                    char anterior = keypad[i - 1][j];
                    if (validos.indexOf(anterior) + 1 != validos.indexOf(actual)) {
                        isVertical = false;
                    }
                }
            }
        }
        if (!isHorizontal && !isVertical) {
            System.out.println("El teclado no sigue una secuencia horizontal o vertical.");
            return false;
        }
        return true;
    }

    public static boolean areValidMovements(String[] movimientos) {
        if (movimientos == null) {
            return false;
        }
        for (String move : movimientos) {
            if (move == null) {
                return false;
            }
            if (!move.matches("[UDLR]+")) {
                return false;
            }
        }
        return true;
    }

    public static String obtainCode(char[][] keypad, String[] movimientos) throws IllegalArgumentException{
        if(!areValidMovements(movimientos)){
            throw new IllegalArgumentException("Argumento ilegal");
        }
        if(!isValidKeyPad(keypad)){
            throw new IllegalArgumentException("Argumento ilegal");
        }
        StringBuilder contrasena = new StringBuilder();
        int filaActual = 0, columnaActual = 0;

        for(String move : movimientos){
            for(char direccion : move.toCharArray()){
                switch(direccion){
                    case 'U': if (filaActual > 0) filaActual--; break;
                    case 'D': if (filaActual < keypad.length - 1) filaActual++; break;
                    case 'L': if (columnaActual > 0) columnaActual--; break;
                    case 'R': if (columnaActual < keypad[0].length - 1) columnaActual++; break;
                }
            }
            contrasena.append(keypad[filaActual][columnaActual]);
        }
        return contrasena.toString();
    }
}


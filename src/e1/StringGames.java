package e1;

import java.util.Arrays;

public class StringGames {
    public static String bestCharacters(String cadena1, String cadena2) throws IllegalArgumentException{
        if(cadena1.length() == cadena2.length()) {
            int minusculas1 = 0, minusculas2 = 0;
            int mayusculas1 = 0, mayusculas2 = 0;
            int digitos1 = 0, digitos2 = 0;
            int puntos1 = 0, puntos2 = 0;

            for (int i = 0; i < cadena1.length(); i++) {
                char m1 = cadena1.charAt(i);
                char m2 = cadena2.charAt(i);
                if (Character.isLowerCase(m1)) {
                    minusculas1++;
                }
                if (Character.isLowerCase(m2)) {
                    minusculas2++;
                }
            }
            for (int i = 0; i < cadena1.length(); i++) {
                char M1 = cadena1.charAt(i);
                char M2 = cadena2.charAt(i);
                if (Character.isUpperCase(M1)) {
                    mayusculas1++;
                }
                if (Character.isUpperCase(M2)) {
                    mayusculas2++;
                }
            }
            for (int i = 0; i < cadena1.length(); i++) {
                char d1 = cadena1.charAt(i);
                char d2 = cadena2.charAt(i);
                if (Character.isDigit(d1)) {
                    digitos1++;
                }
                if (Character.isDigit(d2)) {
                    digitos2++;
                }
            }

            if (minusculas1 > minusculas2) {
                puntos1++;
            } else if (minusculas1 < minusculas2) {
                puntos2++;
            }

            if (mayusculas1 > mayusculas2) {
                puntos1++;
            } else if (mayusculas1 < mayusculas2) {
                puntos2++;
            }

            if (digitos1 > digitos2) {
                puntos1++;
            } else if (digitos1 < digitos2) {
                puntos2++;
            }

            if (puntos2 > puntos1) return cadena2;
            else return cadena1;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public static int crossingWords(String cadena1, String cadena2) {
        int iguales=0;

        for (int i=0; i<cadena1.length(); i++){
            for (int j=0; j<cadena2.length(); j++){
                if (cadena1.charAt(i)==cadena2.charAt(j)){
                    iguales++;
                }

            }
        }
        return iguales;
    }

    public static String wackyAlphabet(String cadena1, String cadena2) throws IllegalArgumentException {
        StringBuilder builder = new StringBuilder();
        StringBuilder builderAux = new StringBuilder();
        String cadenaAux = "abcdefghijklmnopqrstuvwxyz";
        if(cadena2.length() == 26){
            int a=0;
            for (int i=0; i<cadena2.length(); i++){
                for(int j=0; j<cadenaAux.length(); j++) {
                    if (cadena2.charAt(i) == cadenaAux.charAt(j)) {
                        a++;
                        char currentChar = cadena2.charAt(i);
                        cadenaAux = cadenaAux.replace(String.valueOf(currentChar), "");
                    }
                }
            }
            if(a == 26) {
                for (int i = 0; i < cadena2.length(); i++) {
                    for (int j = 0; j < cadena1.length(); j++) {
                        if (cadena1.charAt(j) == cadena2.charAt(i)) {
                            builder.append(cadena1.charAt(j));
                        }
                    }
                }
                return builder.toString();
            }
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();
    }

}



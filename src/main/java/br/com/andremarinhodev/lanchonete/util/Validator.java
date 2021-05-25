package br.com.andremarinhodev.lanchonete.util;

import java.math.BigInteger;

public class Validator {

	public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && obj.equals("")) {
            return true;
        } else if (obj instanceof Long && obj.equals(0l)) {
            return true;
        } else if (obj instanceof BigInteger && obj.equals(BigInteger.ZERO)) {
            return true;
        } else if (obj instanceof Integer && obj.equals(0)) {
            return true;
        } else if (obj instanceof Double && obj.equals(0.0)) {
            return true;
        } else return false;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.util;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author ely
 */
public class Caracter {
    
    public static String cpfMask = "###.###.###-##";
    public static String cnpjMask = "##.###.###/####-##";
    public static String cepMask = "##.###-###";
    public static String phoneMask = "(##) ####-####";
    
    public static String removecaracter(String s) {
        String caracteres = s;
        if(s != null){
            caracteres = new String(s).replaceAll("\\.", "").replace("-", "").replace("/", "").replace("-", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
        }
        return caracteres;
    }

    public static String addMask(String s, String mask){
        MaskFormatter maskFormatter;
        
        try {
            maskFormatter = new MaskFormatter(mask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            return maskFormatter.valueToString(s);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}

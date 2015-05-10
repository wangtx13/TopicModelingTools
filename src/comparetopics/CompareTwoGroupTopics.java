/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparetopics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author apple
 */
public class CompareTwoGroupTopics {

    public static void main(String[] args) {

        try {
            File file1 = new File("/Users/apple/Desktop/output/after-process/code+javadoc/composite-originalWords-camel-stopwords/keys.txt");
            File file2 = new File("/Users/apple/Desktop/output/after-process/javadoc+comments/composite-noCopyright-originalWords-camel-stopwords/keys.txt");
            
            if (!file1.exists()) {
                System.out.println("File1 isn't exist");
            } else if (!file2.exists()) {
                System.out.println("File2 isn't exist");
            } else {
                try (
                        InputStream in1 = new FileInputStream(file1.getPath());
                        BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));) {

                    String line1 = null;
                    int lineNr1 = -1;
                    while ((line1 = reader1.readLine()) != null) {
                        ++lineNr1;

                        int lineNr2 = -1;
                        String line2 = null;
                        try (
                                InputStream in2 = new FileInputStream(file2.getPath());
                                BufferedReader reader2 = new BufferedReader(new InputStreamReader(in2))) {
                            while ((line2 = reader2.readLine()) != null) {
                                ++lineNr2;
                            compareTwoGroups(line1, line2, lineNr1, lineNr2);   
                            }
                        }
                        System.out.println();
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CompareTopics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void compareTwoGroups(String groups1, String groups2, int lineNr1, int lineNr2) {
        String[] topics1 = groups1.split("\t| ");
        String[] topics2 = groups2.split("\t| ");

//        for (String w1 : topics1) {
//            if (!NumberUtils.isNumber(w1)) {
//                System.out.println(w1);
//            }
//        }
//
//        System.out.println();
//
//        for (String w2 : topics2) {
//            if (!NumberUtils.isNumber(w2)) {
//                System.out.println(w2);
//            }
//        }

        int index = 0;
        for (String word1 : topics1) {
            if (!NumberUtils.isNumber(word1)) {
                for (String word2 : topics2) {
                    if (!NumberUtils.isNumber(word2)) {
                        if (word1.equals(word2)) {
                            ++index;
                            break;
                        }
                    }
                }
            }

        }

        if(index >= 4)
            System.out.println("File1的第" + lineNr1 + "行和File2的第" + lineNr2 + "行有" + index + "个词相同");

    }

}

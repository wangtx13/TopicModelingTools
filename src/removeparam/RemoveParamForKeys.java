/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removeparam;

import comparetopics.*;
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
public class RemoveParamForKeys {

    public static void main(String[] args) {

        try {
            File file1 = new File("/Users/apple/Desktop/output/after-process/javadoc+comments/removeClassLibrary-noCopyright-originalWords-camel-stopwords-2/keys.txt");         
            if (!file1.exists()) {
                System.out.println("File1 isn't exist");
            } else {
                try (
                        InputStream in1 = new FileInputStream(file1.getPath());
                        BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1))) {

                    String line1 = null;
                    while ((line1 = reader1.readLine()) != null) {
                        String[] topics = line1.split("\t| ");
                        System.out.print(topics[0] + "\t");
                        for(int i = 2; i < topics.length ; ++i) {
                            System.out.print(topics[i] + " ");
                        }
                        
                        System.out.println();
                        
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CompareTopics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparetopics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
public class CompareTopics {

    public CompareTopics() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File file1 = new File("/Users/apple/Desktop/graduation-project/topic-modeling/output/jhotdraw-extracted-code/keys.txt");
            File file2 = new File("/Users/apple/Desktop/graduation-project/topic-modeling/output/jhotdraw-extracted-code/keys.txt");

            CompareTopics compareTopics = new CompareTopics();
            String[] words1 = compareTopics.getWords(file1);
            String[] words2 = compareTopics.getWords(file2);
            StringBuffer words = new StringBuffer();

            File outputFile = new File("/Users/apple/Desktop/test.txt");
            if (outputFile.createNewFile()) {
                System.out.println("Create successful: " + outputFile.getName());
            }
            boolean hasSame = false;
            
            for (String w1 : words1) {
                if (!NumberUtils.isNumber(w1)) {
                    for (String w2 : words2) {
                        if (w1.equals(w2)) {
                            words.append(w1);
//                            words.append("\r\n");
                            words.append(" ");
                            hasSame = true;
                            break;
                        }
                    }
                }
            }
            if (!hasSame) {
                System.out.println("No same word.");
            } else {
                compareTopics.printToFile(words.toString(), outputFile);
            }

        } catch (IOException ex) {
            Logger.getLogger(CompareTopics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String[] getWords(File file) {

        StringBuffer content = new StringBuffer();
        try {
            if (!file.exists()) {
                System.out.println("File isn't exist");
            } else {
                try (
                        InputStream in = new FileInputStream(file.getPath());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CompareTopics.class.getName()).log(Level.SEVERE, null, ex);
        }

        return content.toString().split("\t| ");
    }

    private void printToFile(String word, File outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getPath()))) {
            writer.write(word);
            writer.flush();
        }

    }
}

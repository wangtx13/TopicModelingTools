/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorttopics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author apple
 */
public class SortTopics {

    public static void main(String[] args) throws IOException {
        
        File file = new File("/Users/apple/Desktop/stopwords-list排序.txt");
        if (file.createNewFile()) {
            System.out.println("Create successful: " + file.getName());
        }
        try (
                InputStream in = new FileInputStream("/Users/apple/Desktop/stopwords-list.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            String[][] content = new String[100][];
            String line = null;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                int numbers = 0;
                content[index] = line.split(" ");
                Arrays.sort(content[index]);
                for(String word : content[index]) {
                    ++ numbers;
                    writer.write(word + " ");
                    writer.flush();
                }
                if(!line.equals("")) {
                    writer.write(" [" + numbers + "]\r\n");
                    writer.flush();
                } else {
                    writer.write("\r\n");
                    writer.flush();
                }
                ++index;
            }
        }
    }

}

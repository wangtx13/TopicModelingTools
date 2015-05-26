/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import comparetopics.CompareTopics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author apple
 */
public class CountTopics {

    public static void main(String[] args) {

        try {
            File file = new File("/Users/apple/Desktop/output/after-process/javadoc+comments/removeClassLibrary-noCopyright-originalWords-camel-stopwords/keys.txt");
            List<String> topicslist = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            if (!file.exists()) {
                System.out.println("File1 isn't exist");
            } else {
                try (
                        InputStream in1 = new FileInputStream(file.getPath());
                        BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1))) {

                    String line1 = null;
                    while ((line1 = reader1.readLine()) != null) {
                        String[] topics1 = line1.split("\t| ");

                        for (String t : topics1) {
                            if (!NumberUtils.isNumber(t)) {
                                topicslist.add(t);
                            }

                        }
                    }
                }

                for (int i = 0; i < topicslist.size(); i++) {
                    String word = topicslist.get(i);
                    int index = 1;
                    for (int j = 0; j < topicslist.size(); ++j) {
                        if (word.equals(topicslist.get(j))) {
                            ++index;
                        }
                    }
                    map.put(word, index);
                }

                Iterator iter = map.entrySet().iterator();
                int index = -1;
                while (iter.hasNext()) {
                    ++index;
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    System.out.println(key + ":" + val);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CompareTopics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

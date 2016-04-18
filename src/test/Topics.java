/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author apple
 */
public class Topics {

    public static void main(String[] args) {
        try {
            String topicsFilePath = "/Users/apple/NetBeansProjects/TopicModelingTools/test/topic_keys.txt";

            try (
                    InputStream topicsIn = new FileInputStream(topicsFilePath);
                    BufferedReader topicsReader = new BufferedReader(new InputStreamReader(topicsIn));) {

                JSONObject json = new JSONObject();
                JSONArray children = new JSONArray();
                json.put("parent", children);
                
                String topicsLine = "";
                while ((topicsLine = topicsReader.readLine()) != null) {
                    JSONObject topicGroup = new JSONObject();
                    JSONArray topicArray = new JSONArray();
                    topicGroup.put("children", topicArray);
                    
                    String[] topics = topicsLine.split("\\s");
                    for (int i = 0; i < topics.length; ++i) {
                        if (topics.length > 2) {
                            if (!NumberUtils.isNumber(topics[i])) {
                                JSONObject topic = new JSONObject();
                                topic.put("name", topics[i]);
                                topicArray.put(topic);
                            }
                        }
                    }
                    children.put(topicGroup);
                }
                
                System.out.println(json);             
                
                JSONObject json2 = json;
                JSONArray parent2 = json2.getJSONArray("parent");
                
                for(int i = 0; i < parent2.length(); ++i) {
                    JSONObject topicGroup2 = parent2.getJSONObject(i);
                    JSONArray children2 = topicGroup2.getJSONArray("children");
                    
                    for(int j = 0; j < children2.length(); ++j) {
                        JSONObject topic2 = children2.getJSONObject(j);
                        String name2 = topic2.getString("name");
                        System.out.print(name2 + " ");
                    }
                    System.out.println();
                }
                
                

            } catch (JSONException ex) {
                Logger.getLogger(Topics.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(GenerateTopicsJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

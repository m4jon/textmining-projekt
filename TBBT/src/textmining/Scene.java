/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel-Mac
 */
public class Scene {
    private final int sceneNr;
    private final String sceneName;
    
    private int numberOfSpeaker;
    private int numberOfWords;
    
    private Map<String, String> sceneMap;
    public Scene(int inputSceneNr,String inputSceneName)
    {
        this.sceneNr = inputSceneNr;
        this.sceneName = inputSceneName;
        sceneMap = new HashMap<String, String>();
    }
    
    public void outPutSceneName()
    {
        System.out.println("SceneNr:"+sceneNr);
        System.out.println("SceneName:"+sceneName);
    }
    
    public void addLine(String inputSpeaker,String inputLine)
    {
        sceneMap.put(inputSpeaker, inputLine);
    }
}
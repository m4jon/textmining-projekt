/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

/**
 *
 * @author Manuel-Mac
 */

public class Speaker {

private String SpeakerString;

    Speaker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSpeakerString() {
        return SpeakerString;
    }

    public void setSpeakerString(String SpeakerString) {
        this.SpeakerString = SpeakerString;
    }

    public Speaker(String SpeakerString) {
        this.SpeakerString = SpeakerString;
    }

    @Override
    public String toString() {
        return "Speaker{" + "SpeakerString=" + SpeakerString + '}';
    }

    void setString(String SpeakerString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

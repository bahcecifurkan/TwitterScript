/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tables;

/**
 *
 * @author furkan
 */
public class Tweet {
    private int idtweet;
    private String content;
    private String date;
    private int iduserfk;
    private int parent;

    public int getIdtweet() {
        return idtweet;
    }

    public void setIdtweet(int idtweet) {
        this.idtweet = idtweet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIduserfk() {
        return iduserfk;
    }

    public void setIduserfk(int iduserfk) {
        this.iduserfk = iduserfk;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
    
    
    
}

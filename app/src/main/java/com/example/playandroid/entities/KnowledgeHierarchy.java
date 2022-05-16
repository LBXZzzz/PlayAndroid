package com.example.playandroid.entities;

public class KnowledgeHierarchy {
    private String nameFirst;
    private String nameSecond;
    public KnowledgeHierarchy(String nameFirst, String nameSecond){
        this.nameFirst=nameFirst;
        this.nameSecond=nameSecond;
    }
    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameSecond() {
        return nameSecond;
    }

    public void setNameSecond(String nameSecond) {
        this.nameSecond = nameSecond;
    }
}

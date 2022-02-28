package com.seaclub.Model;

import java.io.Serializable;

public class Notification implements Serializable {
    private int id;
    private String name;

    public Notification(int id, String nome){
        this.id=id;
        this.name=nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

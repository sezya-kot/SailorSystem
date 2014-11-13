package com.sezyakot.android.sailorapp.sailor.model;
public class Plant extends DefaultData {

    public Plant() {};

    public Plant(     int id,
               int serverId,
               String name,
               int version,
               int status) {
        super(id, serverId, name, version, status);
    }

}

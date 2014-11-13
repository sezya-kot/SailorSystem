package com.sezyakot.android.sailorapp.sailor.model;
public class Unit extends DefaultData {

    public Unit() {};

    public Unit(int id,
         int serverId,
         String name,
         int version,
         int status) {
       super(id, serverId, name, version, status);
    }

}

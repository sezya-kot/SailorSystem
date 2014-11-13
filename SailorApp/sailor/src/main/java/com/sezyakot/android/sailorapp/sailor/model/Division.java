package com.sezyakot.android.sailorapp.sailor.model;
public class Division extends DefaultData {

    public Division() {};

    public Division(  int id,
               int serverId,
               String name,
               int version,
               int status) {
        super(id, serverId, name, version, status);
    }
}

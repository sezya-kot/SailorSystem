package com.sezyakot.android.sailorapp.sailor.model;
public class Warehouse extends DefaultData {
    public Warehouse() {};

    public Warehouse( int id,
               int serverId,
               String name,
               int version,
               int status) {
        super(id, serverId, name, version, status);
    }
}

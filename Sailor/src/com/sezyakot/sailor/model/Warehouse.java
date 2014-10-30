package com.sezyakot.sailor.model;

/**
 * Created by Android on 28.08.2014.
 */
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

package com.sezyakot.sailor.model;

/**
 * Created by Android on 28.08.2014.
 */
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

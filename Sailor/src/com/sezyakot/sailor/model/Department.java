package com.sezyakot.sailor.model;

import com.sezyakot.sailor.model.DefaultData;

/**
 * Created by Android on 28.08.2014.
 */
public class Department extends DefaultData {

    public Department() {};

    public Department(int id,
         int serverId,
         String name,
         int version,
         int status) {
        super(id, serverId, name, version, status);
    }

}

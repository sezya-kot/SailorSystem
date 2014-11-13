package com.sezyakot.android.sailorapp.sailor.model;
public class Product extends Service{

    public Product() {}

    public Product(int id,
                   int server_id,
                   String name,
                   String description,
                   String code,
                   int vat,
                   int unit,
                   int version,
                   int status) {
        super(id, server_id, name, description, code, vat, unit, version, status);
    }
}

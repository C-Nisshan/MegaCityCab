package com.megacitycab.megacitycab.models;

import java.util.Date;

public class DateEntity {
    protected Date createdAt;
    protected Date updatedAt;


    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}

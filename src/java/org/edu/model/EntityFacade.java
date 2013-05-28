package org.edu.model;

import java.io.Serializable;


public interface EntityFacade<ID> extends Serializable {
    
    public abstract ID getId();
    //public abstract void setId(final ID id);
    public abstract boolean isNew();
}

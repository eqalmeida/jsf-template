package org.edu.model;


public interface EntityFacade<ID> {
    
    public abstract ID getId();
    public abstract void setId(final ID id);
    public abstract boolean isNew();
}

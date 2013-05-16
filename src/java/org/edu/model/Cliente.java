package org.edu.model;

public class Cliente implements EntityFacade<Integer> {
    
    private Integer id;
    private int version;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return(this.id==null?true:this.id==0?true:false);
    }

    
    
}

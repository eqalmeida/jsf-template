package org.edu.model;

public class Cliente implements GenericEntity<Integer> {
    
    private Integer id;

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

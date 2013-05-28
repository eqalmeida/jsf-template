package org.edu.model;

public abstract class IntegerModel implements EntityFacade<Integer> {
    
    @Override
    public abstract Integer getId();
    
    @Override
    public boolean isNew(){
        return (getId() == null || getId() == 0);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    
    
}

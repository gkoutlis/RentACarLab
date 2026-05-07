package model;

public abstract class BaseEntity {
    protected int id;

    public BaseEntity(){

    }

    public BaseEntity(int id){
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public abstract String toString();
}

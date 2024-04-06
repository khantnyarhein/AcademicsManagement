package cz.cvut.fit.tjv.semprojheinkhan.entities;

public interface EntityWithId<ID>{
    ID getId();

    void setId(ID id);
}

package com.mycompany.a2.Interfaces;

public interface ICollection extends IIterator {
    public void add(Object newObject);

    public IIterator getIterator();

}

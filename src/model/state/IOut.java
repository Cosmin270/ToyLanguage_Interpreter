package model.state;

import model.value.IValue;

public interface IOut {
    void add(IValue value);
    void clear();
}

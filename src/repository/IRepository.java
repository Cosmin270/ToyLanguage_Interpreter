package repository;

import model.state.ProgramState;

public interface IRepository {
    ProgramState getCrtProgram();
    void addProgram(ProgramState program);
    void deleteFirst();
    void reset();
    void increment();
}

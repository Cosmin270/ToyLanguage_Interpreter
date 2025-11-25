package repository;

import model.exception.RepositoryException;
import model.state.ProgramState;

public interface IRepository {
    ProgramState getCrtProgram();
    void addProgram(ProgramState program);
    void deleteFirst();
    void reset();
    void increment();
    void decrement();
    void logPrgStateExec() throws RepositoryException;
}

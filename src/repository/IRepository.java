package repository;

import model.exception.RepositoryException;
import model.list.MyList;
import model.state.ProgramState;

public interface IRepository {
    //ProgramState getCrtProgram();
    void addProgram(ProgramState program);
    void deleteFirst();
    void reset();
    void increment();
    void decrement();
    void logPrgStateExec(ProgramState programState) throws RepositoryException;
    MyList<ProgramState> getPrgList();
    void setPrgList(MyList<ProgramState> prgList);
}

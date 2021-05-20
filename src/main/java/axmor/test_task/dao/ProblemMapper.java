package axmor.test_task.dao;

import axmor.test_task.entity.Problem;
import axmor.test_task.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
public class ProblemMapper {

    //Запрос всех проблем
    public List<Problem> getAllProblems() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Problem> problemsList = session.selectList("getAllProblems");
        session.commit();
        session.close();
        return problemsList;
    }

    //Присваивание новой проблемы,пришедшей с формы ввода даты, даты-времени.
    //Сохранение новой проблемы
    public void saveProblem(Problem problem) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        problem.setDateTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
        problem.setDate(new SimpleDateFormat("dd/MM/yyyy ").format(Calendar.getInstance().getTime()));
        session.insert("insertProblem", problem);
        session.commit();
        session.close();
    }

    //Запрос из базы проблемы по id
    public Problem getProblemById(int problemId) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Problem problem = session.selectOne("findById", problemId);
        session.commit();
        session.close();
        return problem;
    }

    //Обновление проблемы при изменении статуса
    public void updateProblem(Problem problem) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("updateProblem", problem);
        session.commit();
        session.close();

    }


}

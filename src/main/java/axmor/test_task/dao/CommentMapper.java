package axmor.test_task.dao;

import axmor.test_task.entity.Comment;
import axmor.test_task.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
public class CommentMapper {

    //Запрос всех комментариев из базы
    public List<Comment> getAllCommentsById(int problemId) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Comment> comentsList = session.selectList("getAllComments", problemId);
        session.commit();
        session.close();
        return comentsList;
    }

    //Сохранение нового комментария
    public void saveComment(Comment comment) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        comment.setDateTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
        session.insert("insertComment", comment);
        session.commit();
        session.close();
    }
}

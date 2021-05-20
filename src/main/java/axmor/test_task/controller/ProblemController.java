package axmor.test_task.controller;

import axmor.test_task.dao.CommentMapper;
import axmor.test_task.dao.ProblemMapper;
import axmor.test_task.entity.Comment;
import axmor.test_task.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ProblemController {

    @Autowired
    ProblemMapper problemMapper;

    @Autowired
    CommentMapper commentMapper;

    // редирект на главную сраницу при авторизации,необходим при нескольких авторизациях в одной сессии
    @GetMapping("")
    public String redirect() {
        return "redirect:/problems/";
    }

    // главная страница со списком проблем
    @GetMapping("problems/")
    public String showAllProblems(Model model) {
        model.addAttribute("listProblems", problemMapper.getAllProblems());
        return "allProblems";
    }

    /*
    Переход на страницу проблемы,выполняется по клику на названии проблемы
    Создается новый объект комментария.
    Присваивается id пробелемы.
    На страницу передаётся: новый комменарий в форму ввода,проблема,на страницу которой идёт переход и список
    причастных комментариев для отображения
     */
    @GetMapping("problems/{id}")
    public String showProblem(@PathVariable("id") int id, Model model) {
        Comment comment = new Comment();
        comment.setProblemId(id);
        model.addAttribute("comment", comment);
        model.addAttribute("problem", problemMapper.getProblemById(id));
        model.addAttribute("listComments", commentMapper.getAllCommentsById(id));
        return "showProblem";
    }

    /*
    Создание нового объекта проблемы.
    В форму передаётся имя авторизованного пользователя для автоматической подставновки в соответствующую строку.
    Переход на страницу формы
     */
    @GetMapping("problems/create")
    public String createProblem(@AuthenticationPrincipal User user, Model model) {
        Problem problem = new Problem();
        problem.setAuthor(user.getUsername());
        model.addAttribute("problem", problem);
        return "problemForm";
    }

    /*
    Возварщение объекта с формы создания новой проблемы и сохранение его в базу
    Редирект на главную страницу со списком проблем
     */
    @PostMapping("problems/save")
    public String saveProblem(@ModelAttribute("problem") Problem problem) {
        problemMapper.saveProblem(problem);
        return "redirect:/problems/";
    }

    /*
    Сохранение нового комментрия к проблеме
    Запрос из базы проблемы, к которой добавляется комментрий.
    Проверка,на совпадение статусов проблемы и комментария.
    При несовпадении у проблемы обновляется статус
    Обновление  в базе с новым статусом
    Сохранение в базу нового комментария
    Редирект на страницу проблемы.
     */
    @PostMapping("problems/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        Problem problem = problemMapper.getProblemById(comment.getProblemId());
        if (!problem.getStatus().equals(comment.getStatus())) {
            problem.setStatus(comment.getStatus());
            problemMapper.updateProblem(problem);
        }
        commentMapper.saveComment(comment);

        return "redirect:/problems/" + comment.getProblemId();
    }
}

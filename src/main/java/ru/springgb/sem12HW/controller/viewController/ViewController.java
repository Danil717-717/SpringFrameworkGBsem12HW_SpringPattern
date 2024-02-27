package ru.springgb.sem12HW.controller.viewController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.model.factory.TaskFactoryImpl;
import ru.springgb.sem12HW.service.ExecutorService;
import ru.springgb.sem12HW.service.TaskService;

import static ru.springgb.sem12HW.model.entity.Task.TaskType.NORMAL_EXECUTION;
import static ru.springgb.sem12HW.model.entity.Task.TaskType.URGENT_IMPLEMENTATION;


@Controller
@RequestMapping("/index")
public class ViewController {
    private final TaskService taskService;
    private final ExecutorService executorService;
    private final TaskFactoryImpl taskFactory;

    @Autowired
    public ViewController(TaskService taskService, ExecutorService executorService, TaskFactoryImpl taskFactory) {
        this.taskService = taskService;
        this.executorService = executorService;
        this.taskFactory = taskFactory;
    }


    /**
     * Главная страница
     * @return index
     */
    @GetMapping
    public String getIndex() {
        return "index";
    }


    /**
     * Получение списков задач и исполнителей
     */
    @GetMapping("/tasks")
    public String indexTask(Model model, @Param("keyword") String keyword) {
        model.addAttribute("tasks", taskService.getAllTasks(keyword));
        model.addAttribute("keyword",keyword);
        return "tasks";
    }
    @GetMapping("/executors")
    public String indexExec(Model model) {
        model.addAttribute("executors", executorService.findAll());
        return "executors";
    }


    /**
     * Страница добавления новой задачи и нового исполнителя
     */

    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "new";
    }


    @GetMapping("/executors/newExecutor")
    public String newExecutor(Model model) {
        model.addAttribute("executor", new Executor());
        return "newExecutor";
    }


    /**
     * Добавление новой задачи и нового исполнителя
     */

    @PostMapping("/tasks")
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        taskService.createTask(task);
        return "redirect:tasks";
    }

    @PostMapping("/tasks/ntype")
    public String createNTask(@ModelAttribute("task") Task task) {
        Task.TaskType type = NORMAL_EXECUTION;
        task = (Task) taskFactory.createTask(type);
        taskService.createTask(task);
        return "redirect:/index/tasks";
    }

    @PostMapping("/tasks/utype")
    public String createUTask(@ModelAttribute("task") Task task) {
        Task.TaskType type = URGENT_IMPLEMENTATION;
        task = (Task) taskFactory.createTask(type);
        taskService.createTask(task);
        return "redirect:/index/tasks";
    }


    @PostMapping("/executors")
    public String createExecutors(@ModelAttribute("executor") @Valid Executor executor, BindingResult result) {
        if (result.hasErrors()) {
            return "newExecutor";
        }
        executorService.save(executor);
        return "redirect:executors";
    }


    /**
     * Поиск по id задачи и исполнителя
     */
    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskProfile";
    }

    @GetMapping("/executors/{id}")
    public String getExecutor(@PathVariable Long id, Model model) {
        model.addAttribute("executor", executorService.findById(id));
        return "executorProfile";
    }


    /**
     *  Изменение задачи и исполнителя
     */
    @PostMapping("/tasks/update/{id}")
    private String updateTaskValid(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "updateTask";
        }
        task.setId(id);
        taskService.apdateTask(task);
        return "redirect:/index/tasks";
    }
    @GetMapping("/tasks/updateTask/{id}")
    public String updateTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }

    @PostMapping("/executors/updateExecutor/{id}")
    private String updateExecutorValid(@PathVariable("id") Long id,
                                       @ModelAttribute @Valid Executor executor, BindingResult result) {
        if (result.hasErrors()) {
            return "updateExecutor";
        }
        executor.setId(id);
        executorService.apdateExecutor(executor);
        return "redirect:/index/executors";
    }

    @GetMapping("/executors/update/{id}")
    public String updateExecutor(@PathVariable(value = "id") Long id, Model model) {
        Executor executor = executorService.findById(id);
        model.addAttribute("executor", executor);
        return "updateExecutor";
    }


    /**
     * Добавление по id
     */

    @PostMapping("/tasks/executors/{id}")
    public String addExecutorInTask(@PathVariable Long id,@ModelAttribute("executor") @Valid Executor executor){
        taskService.createExecutorForTask(id,executor);
        return "redirect:/index/taskProfile";
    }


    /**
     * Удаление задачи и исполнителя
     */
    @GetMapping("/tasks/delete/{id}")
    private String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/index/tasks";
    }

    @GetMapping("/executors/delete/{id}")
    private String deleteExecutor(@PathVariable("id") Long id) {
        executorService.deleteById(id);
        return "redirect:/index/executors";
    }



}

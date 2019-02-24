package com.example.web;

import com.example.domain.Customer;
import com.example.domain.Todo;
import com.example.service.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("todos")
public class TodoController {
  @Autowired
  TodoService todoService;

  @ModelAttribute
  TodoForm setUpForm() {
    return new TodoForm();
  }

  @RequestMapping(method = RequestMethod.GET)
  String list(Model model) {
    List<Todo> todoList = todoService.findAll();

    List<Todo> trueList = null;
    List<Todo> falseList = null;

    trueList = todoList.stream().filter(x -> x.getFinish() == true).collect(Collectors.toList());
    falseList = todoList.stream().filter(x -> x.getFinish() == false).collect(Collectors.toList());

//    for (Todo todo : todoList) {
//      if (todo.getFinish()) {
//        trueList.add(todo);
//      } else {
//        trueList.add(todo);
//      }
//    }

    model.addAttribute("falseList", falseList);
    model.addAttribute("trueList", trueList);

    return "todos/list";
  }

  @RequestMapping(value = "create", method = RequestMethod.POST)
  String create(@Validated TodoForm form, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return list(model);
    }
    Todo todo = new Todo();
    BeanUtils.copyProperties(form, todo);
    todoService.create(todo);
    return "redirect:/todos";
  }

  @RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
  String editForm(@RequestParam Integer id, TodoForm form) {
    Todo todo = todoService.findOne(id);
    BeanUtils.copyProperties(todo, form);
    return "todos/edit";
  }

  @RequestMapping(value = "edit", method = RequestMethod.POST)
  String edit(@RequestParam Integer id, @Validated TodoForm form, BindingResult result) {
    if (result.hasErrors()) {
      return editForm(id, form);
    }
    Todo todo = new Todo();
    BeanUtils.copyProperties(form, todo);
    todo.setId(id);
    todoService.update(todo);
    return "redirect:/todos";
  }

  @RequestMapping(value = "edit", params = "goToTop")
  String goToTop() {
    return "redirect:/todos";
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  String delete(@RequestParam Integer id) {
    todoService.delete(id);
    return "redirect:/todos";
  }

  @RequestMapping(value = "finish", method = RequestMethod.POST)
  String finish(@RequestParam Integer id) {
    Todo todo = todoService.findOne(id);
    todo.setFinish(!todo.getFinish());
    todoService.update(todo);
    return "redirect:/todos";
  }
}

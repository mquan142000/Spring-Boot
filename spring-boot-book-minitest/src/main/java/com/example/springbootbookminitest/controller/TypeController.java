package com.example.springbootbookminitest.controller;


import com.example.springbootbookminitest.model.DTO.ICountBook;
import com.example.springbootbookminitest.model.Type;
import com.example.springbootbookminitest.service.impl.BookService;
import com.example.springbootbookminitest.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BookService bookService;

    @GetMapping
    private ModelAndView listType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<Type> types = typeService.findAll();
        modelAndView.addObject("types", types);
        Iterable<ICountBook> type1 = typeService.getCountBook();
        modelAndView.addObject("type1", type1);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveType(@ModelAttribute("type") Type type) {
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        modelAndView.addObject("message", "New type created successfully");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public ModelAndView updateType(@ModelAttribute("type") Type type) {
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/update");
        modelAndView.addObject("type", type);
        modelAndView.addObject("message", "Type updated successful");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/delete");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete")
    public String deleteType(@ModelAttribute("type") Type type) {
        typeService.remove(type.getId());
        return "redirect:/types";
    }

    @GetMapping("/demo")
    public ModelAndView getDemo() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<ICountBook> types = typeService.getCountBook();
        modelAndView.addObject("type1", types);
        return modelAndView;
    }

}

package com.example.springbootbookminitest.controller;

import com.example.springbootbookminitest.model.Book;
import com.example.springbootbookminitest.model.BookForm;
import com.example.springbootbookminitest.model.Type;
import com.example.springbootbookminitest.repository.IBookRepository;
import com.example.springbootbookminitest.service.IBookService;
import com.example.springbootbookminitest.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBookRepository bookRepository;

    @Value("${file-upload}")
    private String upload;

    @ModelAttribute("types")
    public Iterable<Type> listTypes() {
        return typeService.findAll();
    }

    @GetMapping
    public String listBooks(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "") String search,
                            Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Book> bookPage = (search != null && !search.isEmpty())
                ? bookService.findAllByNameContaining(pageable, search)
                : bookService.findAll(pageable);

        model.addAttribute("page", bookPage);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("search", search);

        return "book/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookForm());
        return "/book/create";
    }

    @PostMapping("/create")
    public String saveBook(@ModelAttribute("book") BookForm bookForm, RedirectAttributes redirectAttributes) {
        MultipartFile file = bookForm.getImage();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setPrice(bookForm.getPrice());
        book.setType(bookForm.getType());
        book.setImage(fileName);
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Book saved successfully");
        return "redirect:/books";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "/book/detail";
        } else {
            return "/error_404";
        }
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "/book/update";
        } else {
            return "/error_404";
        }
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") BookForm bookForm, RedirectAttributes redirectAttributes) {
        MultipartFile file = bookForm.getImage();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setPrice(bookForm.getPrice());
        book.setType(bookForm.getType());
        book.setImage(fileName);
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "/book/delete";
        } else {
            return "/error_404";
        }
    }

    @PostMapping("/delete")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.remove(book.getId());
        return "redirect:/books";
    }

    @GetMapping("/type")
    public String searchByType(@RequestParam("typeId") Long typeId, @PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Book> books = bookService.findByTypeId(typeId, pageable);
        model.addAttribute("books", books);
        return "/book/list";
    }

    @PostMapping("/search")
    public String findBookByName(@RequestParam("search") Optional<String> search,
                                 @PageableDefault(size = 5) Pageable pageable,
                                 Model model) {
        Page<Book> books = search.map(s -> bookService.findAllByNameContaining(pageable, s))
                .orElseGet(() -> bookService.findAll(pageable));

        model.addAttribute("page", books);
        model.addAttribute("books", books.getContent());
        model.addAttribute("search", search.orElse(""));
        return "/book/list";
    }

    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "redirect:/books";
        }
    }

}

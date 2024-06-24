package com.cdweb.bookstore.controller.web.admin;

import com.cdweb.bookstore.api.input.BookInput;
import com.cdweb.bookstore.api.input.UserInput;
import com.cdweb.bookstore.dto.*;
import com.cdweb.bookstore.service.*;
import com.cdweb.bookstore.service.impl.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin-page")
public class AdminController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private IBookService bookService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ImageImpl imageImpl;

    public AdminController(ImageImpl imageImpl) {
        this.imageImpl = imageImpl;
    }

    //book page management
    @GetMapping("/book-management")
    public ModelAndView listBook() {
        ModelAndView mav = new ModelAndView("admin/book-management/books");
        mav.addObject("list", bookService.findAll()); // trả về tất cả sách
        return mav;
    }
    //add book page
    @GetMapping("/add-book-page")
    public ModelAndView addBookPage() {
        ModelAndView mav = new ModelAndView("admin/book-management/addBook");
        mav.addObject("categories", categoryService.findAll()); // trả về tất cả danh mục
        mav.addObject("authors", authorService.findAll());// trả về tất cả tác giả
        return mav;
    }

    // xử lý thêm sách
    @PostMapping("/add-book")
    public ModelAndView addBook(@ModelAttribute("bookInput") BookInput input) throws IOException, GeneralSecurityException {
        String path = null; // link image
        if (!input.getImages().isEmpty()) {
            File tempFile = File.createTempFile("temp", null); // tạo file temp
            input.getImages().transferTo(tempFile); //  chuyển đổi ảnh sang file temp
            path = imageImpl.uploadImageToDrive(tempFile); // gán link sau khi upload lên drive
        }

        ModelAndView mav = new ModelAndView("redirect:/admin-page/book-management");  //book page management
        // tạo sách
        BookDTO newBook = new BookDTO();
        if (input.getId() != 0) newBook.setId(input.getId());
        newBook.setTitle(input.getTitle());
        newBook.setDescription(input.getDescription());
        newBook.setYear_public(input.getYear_public());
        if (input.getQuantitySold() != 0) newBook.setQuantitySold(input.getQuantitySold());
        newBook.setPrice(input.getPrice());
        if (input.getDiscount_percent() != 0) newBook.setDiscountPercent(input.getDiscount_percent());
        if (input.getPublisher() != null) newBook.setPublisher(input.getPublisher());
        if (input.getTotal_page() != 0) newBook.setTotal_page(input.getTotal_page());
        newBook.setNews(input.isNews());
        newBook.setActive(true);
        newBook.setHot(input.isHot());
        newBook.setCategory(categoryService.findById(input.getCategoryId()));
        newBook.setAuthor(authorService.findById(input.getAuthorId()));

        //load hình
        if (path != null) {
            List<BookImageDTO> imageList = new ArrayList<>();
            BookImageDTO img = new BookImageDTO();
            img.setPath(path);
            imageList.add(img);
            newBook.setImages(imageList);
        }
        bookService.save(newBook);
        return mav;
    }
    //edit book page
    @GetMapping("/edit-book-page")
    public ModelAndView editBookPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/book-management/editBook");
        List<Integer> years = new ArrayList<>();
        for (int i = 1940; i <= 2023; i++)
            years.add(i);
        mav.addObject("years", years);
        mav.addObject("book", bookService.findById(id));
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("authors", authorService.findAll());
        return mav;
    }
    //detail book page
    @GetMapping("/detail-book")
    public ModelAndView detail(@RequestParam(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/book-management/detail");
        mav.addObject("book", bookService.findById(id));
        return mav;
    }

    @GetMapping("/delete-book")
    public ModelAndView deleteBook(@RequestParam("id") int id) {
        Path staticPath = Paths.get("src/main/resources/static");
        List<BookImageDTO> images = bookService.findById(id).getImages();
        for (BookImageDTO i : images) {
            Path imgPath = CURRENT_FOLDER.resolve(staticPath).resolve(i.getPath());
            try {
                if (Files.exists(imgPath))
                    Files.deleteIfExists(imgPath);
                else break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        bookService.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/book-management");
        return mav;
    }

    //amdin category
    @GetMapping("/category-management")
    public ModelAndView listCategory() {
        ModelAndView mav = new ModelAndView("admin/category-management/categories");
        mav.addObject("cats", categoryService.findAll());
        return mav;
    }

    @GetMapping("/add-category-page")
    public ModelAndView addCategoryPage() {
        ModelAndView mav = new ModelAndView("admin/category-management/addCategory");
        return mav;
    }

    @PostMapping("/add-category")
    public ModelAndView addCategory(@ModelAttribute("cat") CategoryDTO cat) {
        categoryService.save(cat);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    @GetMapping("/edit-category-page")
    public ModelAndView editCategoryPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/category-management/editCategory");
        mav.addObject("category", categoryService.findById(id));
        return mav;
    }

    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute("cat") CategoryDTO cat) {
        categoryService.updateCat(cat);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    @GetMapping("/delete-category")
    public ModelAndView deleteCat(@RequestParam("id") int id) {
        categoryService.deleteByCatId(id);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    //admin author
    @GetMapping("/author-management")
    public ModelAndView listAuthor() {
        ModelAndView mav = new ModelAndView("admin/author-management/authors");
        mav.addObject("authors", authorService.findAll());
        return mav;
    }

    @GetMapping("/add-author-page")
    public ModelAndView addAuthorPage() {
        return new ModelAndView("admin/author-management/addAuthor");
    }

    @PostMapping("/add-author")
    public ModelAndView addAuthor(@ModelAttribute("author") AuthorDTO author) {
        authorService.save(author);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/author-management");
        return mav;
    }

    @GetMapping("/edit-author-page")
    public ModelAndView editAuthorPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/author-management/editAuthor");
        mav.addObject("au", authorService.findById(id));
        return mav;
    }

    @PostMapping("/edit-author")
    public ModelAndView editAuthor(@ModelAttribute("author") AuthorDTO author) {
        authorService.update(author, author.getAuthorID());
        ModelAndView mav = new ModelAndView("redirect:/admin-page/author-management");
        return mav;
    }

    @GetMapping("/delete-author")
    public ModelAndView deleteAuthor(@RequestParam("id") int id) {
        authorService.delete(id);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/author-management");
        return mav;
    }

    //order
    @GetMapping("/order-management")
    public ModelAndView listOrder() {
        ModelAndView mav = new ModelAndView("admin/order-management/orders");
        mav.addObject("orders", orderService.findAll());
        return mav;
    }

    @GetMapping("/edit-order-page")
    public ModelAndView editOrderPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/order-management/editOrder");
        mav.addObject("order", orderService.findById(id));
        return mav;
    }

    @PostMapping("/edit-order")
    public ModelAndView editOrder(@ModelAttribute("orderInput") OrderDTO order) {
        orderService.update(order, order.getOrderID());
        ModelAndView mav = new ModelAndView("redirect:/admin-page/order-management");
        return mav;
    }

    @GetMapping("/detail-order")
    public ModelAndView detailOrder(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/order-management/detail");
        mav.addObject("order", orderService.findById(id));
        return mav;
    }

    @GetMapping("/delete-order")
    public ModelAndView deleteOrder(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("redirect:/admin-page/order-management");
        orderService.deleteById(id);
        return mav;
    }

    //account
    @GetMapping("/account-management")
    public ModelAndView listAccount() {
        ModelAndView mav = new ModelAndView("admin/account-management/accounts");
        mav.addObject("accounts", userService.findAllUser());
        return mav;
    }

    @GetMapping("/detail-account")
    public ModelAndView detailAccount(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/account-management/detail");
        mav.addObject("account", userService.findByUserId(id));
        return mav;
    }

    @GetMapping("/edit-account-page")
    public ModelAndView editAccountPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/account-management/edit");
        mav.addObject("account", userService.findByUserId(id));
        return mav;
    }

    @PostMapping("/edit-account")
    public ModelAndView editAccount(@ModelAttribute("user") UserInput user) {
        ModelAndView mav = new ModelAndView("redirect:/admin-page/account-management");
        UserDTO userDb = userService.findByUserId(user.getUserId());
        //set lại tất cả thuộc tính của user để lưu lại trong db
        //user input chỉ thay đổi 1 số thuộc tính, các thuộc tính còn lại thì lấy từ trong db ra
        UserDTO newUser = new UserDTO();
        newUser.setUserID(user.getUserId());
        newUser.setUpdatedAt(user.getUpdatedAt());
        newUser.setStatus(user.isStatus());
        List<RoleDTO> roles = new ArrayList<>();
        //lay role cu cua user
        for (RoleDTO r : userDb.getRoles()) {
            roles.add(roleService.findRolebyName(r.getName()));
        }
        if (user.getRoleName() != null)
            roles.add(roleService.findRolebyName(user.getRoleName()));

        newUser.setRoles(roles);
        newUser.setUsername(userDb.getUsername());
        newUser.setEmail(userDb.getEmail());
        if (userDb.getFullname() != null) newUser.setFullname(userDb.getFullname());

        newUser.setPassword(userDb.getPassword());

        if (userDb.getBirthdate() != null)
            newUser.setBirthdate(userDb.getBirthdate());

        newUser.setConfirmToken(userDb.getConfirmToken());

        if (userDb.getCreatedAt() != null)
            newUser.setCreatedAt(userDb.getCreatedAt());

        newUser.setGender(userDb.isGender());

        newUser.setEnable(userDb.isEnable());

        if (userDb.getPhone() != null)
            newUser.setPhone(userDb.getPhone());

        userService.save(newUser);
        return mav;
    }

    @GetMapping("/delete-account")
    public ModelAndView deleteAccount(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("redirect:/admin-page/account-management");
        userService.deleteByUserId(id);
        return mav;
    }

    @GetMapping("getAdmin")
    public UserDTO getAdmin(Principal principal) {
        if (principal == null) return new UserDTO();
        return userService.findByEmailAndIsEnable(principal.getName());
    }
}

package com.example.Test_Project.mvc.controller;

import com.example.Test_Project.mvc.entity.ShowTime;
import com.example.Test_Project.mvc.entity.User;
import com.example.Test_Project.mvc.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowtimeService showtimeService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ChairService chairService;
    @Autowired
    private ChairStatusService chairStatusService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TicketsService ticketsService;
    // Hiển thị danh sách người dùng

//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "/admin/user-list"; // Trả về trang danh sách người dùng
//    }



 // hiển thị danh sách phim
 @GetMapping
    public String getAll(Model model){
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("tickets", ticketsService.getAllTickets());
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("chairs", chairService.getAllChairs());
         return "HomePage"; // HomePage hiển thị danh sách phim
    }
    // Hiển thị chi tiết người dùng
    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-detail"; // Trả về trang chi tiết người dùng
    }

    // Hiển thị form thêm người dùng mới
    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form"; // Trả về form thêm người dùng
    }

    // Lưu thông tin người dùng mới
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users"; // Chuyển hướng về danh sách người dùng
    }

    // Hiển thị form sửa thông tin người dùng
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-form"; // Trả về form chỉnh sửa người dùng
    }

    // Xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Chuyển hướng về danh sách người dùng sau khi xóa
    }

    // Hiển thị form đăng ký người dùng mới
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Register"; // Trả về form đăng ký
    }

    // Xử lý đăng ký người dùng mới
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) == null) {
            userService.saveUser(user);
            return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        } else {
            model.addAttribute("error", "Email đã được sử dụng");
            return "Register"; // Quay lại trang đăng ký nếu có lỗi
        }
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }
    // Xử lý đăng nhập
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String pass, HttpSession session, Model model) {
        if (userService.checkLogin(email, pass)) {
            User loggedInUser = userService.findByEmail(email);
            session.setAttribute("loggedInUserName", loggedInUser.getFullname());

            System.out.println("Tên người dùng: " + loggedInUser.getFullname());

            return "redirect:/users";
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu");
            return "Login"; // Quay lại trang đăng nhập nếu có lỗi
        }
    }

    // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Xóa session
        return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập
    }
}

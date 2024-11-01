package com.example.Test_Project.mvc.controller;

import com.example.Test_Project.mvc.entity.*;
import com.example.Test_Project.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

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

    @Value("${file.upload-dir}")
    private String uploadDir;
    @GetMapping
    public String getAllAdmin(Model model) {
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("tickets", ticketsService.getAllTickets());
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("chairs", chairService.getAllChairs());
        return "admin/AdminView";
    }
    // Movie Management
    @GetMapping("/movies")
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/DisplayViewAdmin";
    }

    @GetMapping("/movies/add")
    public String showCreateMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", movieService.getAllCategories());
        return "admin/AddMovies";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@ModelAttribute Movie movie, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            movie.setImage(imagePath);
        }
        movieService.saveMovie(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditMovieForm(@PathVariable int id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("categories", movieService.getAllCategories());
        return "admin/EditMovies";
    }

    @PostMapping("/movies/update/{id}")
    public String updateMovie(@PathVariable int id, @ModelAttribute Movie movie, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        movie.setMovieId(id); // Đặt ID cho phim trước khi lưu
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile); // Lưu hình ảnh mới
            movie.setImage(imagePath); // Cập nhật đường dẫn hình ảnh mới
        } else {
            Movie existingMovie = movieService.getMovieById(id);
            movie.setImage(existingMovie.getImage()); // Giữ nguyên hình ảnh cũ nếu không có hình ảnh mới
        }
        movieService.saveMovie(movie); // Cập nhật phim
        return "redirect:/movies"; // Chuyển hướng về danh sách phim sau khi cập nhật
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return "redirect:/admin/movies";
    }

    // Cinema Management
    @GetMapping("/cinemas")
    public String getAllCinemas(Model model) {
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        return "admin/cinema-list";
    }

    @GetMapping("/cinemas/add")
    public String showCreateCinemaForm(Model model) {
        model.addAttribute("cinema", new Cinema());
        return "admin/cinema-form";
    }

    @PostMapping("/cinemas/save")
    public String saveCinema(@ModelAttribute Cinema cinema) {
        cinemaService.saveCinema(cinema);
        return "redirect:/admin/cinemas";
    }

    @GetMapping("/cinemas/edit/{id}")
    public String showEditCinemaForm(@PathVariable int id, Model model) {
        model.addAttribute("cinema", cinemaService.getCinemaById(id));
        return "admin/cinema-form";
    }

    @GetMapping("/cinemas/delete/{id}")
    public String deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinema(id);
        return "redirect:/admin/cinemas";
    }

    // Chair Management
    @GetMapping("/chairs")
    public String getAllChairs(Model model) {
        model.addAttribute("chairs", chairService.getAllChairs());
        return "admin/chair-list";
    }

    @GetMapping("/chairs/add")
    public String showCreateChairForm(Model model) {
        model.addAttribute("chair", new Chair());
        model.addAttribute("statuses", chairStatusService.getAllStatuses());
        return "admin/chair-form";
    }

    @PostMapping("/chairs/save")
    public String saveChair(@ModelAttribute Chair chair) {
        chairService.saveChair(chair);
        return "redirect:/admin/chairs";
    }

    @GetMapping("/chairs/edit/{id}")
    public String showEditChairForm(@PathVariable int id, Model model) {
        model.addAttribute("chair", chairService.getChairById(id));
        model.addAttribute("statuses", chairStatusService.getAllStatuses());
        return "admin/chair-form";
    }

    @GetMapping("/chairs/delete/{id}")
    public String deleteChair(@PathVariable int id) {
        chairService.deleteChair(id);
        return "redirect:/admin/chairs";
    }

    // Category Management
    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/viewCategory";
    }

    @GetMapping("/categories/add")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }

    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/editCategory";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    // Showtime Management
    @GetMapping("/showtimes")
    public String getAllShowtimes(Model model) {
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        return "admin/showtime-list";
    }

    @GetMapping("/showtimes/add")
    public String showCreateShowtimeForm(Model model) {
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/showtime-form";
    }

    @PostMapping("/showtimes/save")
    public String saveShowtime(@ModelAttribute ShowTime showtime) {
        showtimeService.saveShowtime(showtime);
        return "redirect:/admin/showtimes";
    }

    @GetMapping("/showtimes/edit/{id}")
    public String showEditShowtimeForm(@PathVariable int id, Model model) {
        model.addAttribute("showtime", showtimeService.getShowtimeById(id));
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/showtime-form";
    }

    @GetMapping("/showtimes/delete/{id}")
    public String deleteShowtime(@PathVariable int id) {
        showtimeService.deleteShowtime(id);
        return "redirect:/admin/showtimes";
    }

    // Ticket Management
    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketsService.getAllTickets());
        return "admin/tickets-list";
    }

    @GetMapping("/tickets/add")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "admin/ticket-form";
    }

    @PostMapping("/tickets/save")
    public String saveTicket(@ModelAttribute Ticket ticket) {
        ticketsService.saveTicket(ticket);
        return "redirect:/admin/tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable int id) {
        ticketsService.deleteTicket(id);
        return "redirect:/admin/tickets";
    }

    // Phương thức lưu hình ảnh
    private String saveImage(MultipartFile imageFile) {
        String uploadDir = "D:/Projcet/Test_Project/uploads";
        // Đường dẫn bên ngoài thư mục `src`
        String originalFilename = imageFile.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("Tên tệp không hợp lệ.");
        }

        String filePath = uploadDir + File.separator + originalFilename;

        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
            }
            imageFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
        }

        return "/uploads/" + originalFilename; // Trả về đường dẫn ảnh tương đối
    }
}

//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Movie;
//import com.example.Test_Project.mvc.entity.ShowTime;
//import com.example.Test_Project.mvc.service.CinemaService;
//import com.example.Test_Project.mvc.service.MovieService;
//import com.example.Test_Project.mvc.service.ShowtimeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Controller
//@RequestMapping("/movies")
//public class MovieController {
//    @Autowired
//    private ShowtimeService showtimeService;
//    @Autowired
//    private MovieService movieService;
//    @Autowired
//    private CinemaService cinemaService;
//    // Đường dẫn tải lên từ file cấu hình
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    // Hiển thị danh sách phim
//    @GetMapping
//    public String getAllMovies(Model model) {
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "/admin/DisplayViewAdmin"; // Đảm bảo rằng tên view chính xác
//    }
//
//    @GetMapping("/user")
//    public String getUserMovies(Model model) {
//        model.addAttribute("showtime", new ShowTime());
//        model.addAttribute("movies", movieService.getAllMovies());
//        model.addAttribute("cinemas", cinemaService.getAllCinemas());
//        return "HomePage"; // HomePage hiển thị danh sách phim
//    }
//
//    @GetMapping("/vew-movies")
//    public String getDisplayMovies(Model model) {
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "DisplayMovies"; //   hiển thị danh sách phim
//    }
//    // Tìm kiếm phim theo tên
//    @GetMapping("/search")
//    public String searchMoviesByName(@RequestParam String name, Model model) {
//        model.addAttribute("movies", movieService.searchMoviesByName(name));
//        return "/admin/DisplayViewAdmin"; //
//    }
//
//    // Tìm kiếm phim theo ngày
//    @GetMapping("/search-by-date")
//    public String searchMoviesByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) {
//        List<Movie> movies = movieService.searchMoviesByDate(date);
//        model.addAttribute("movies", movies);
//        return "/admin/movie-list"; //
//    }
//
//    // Hiển thị form thêm phim mới
//    @GetMapping("/add-movies")
//    public String showCreateMovieForm(Model model) {
//        model.addAttribute("movie", new Movie());
//        model.addAttribute("categories", movieService.getAllCategories()); // Lấy danh mục từ cơ sở dữ liệu
//        return "/admin/AddMovies"; //
//    }
//
//    // Lưu thông tin phim mới
//    @PostMapping("/save")
//    public String saveMovie(@ModelAttribute Movie movie, @RequestParam("imageFile") MultipartFile imageFile) {
//        if (!imageFile.isEmpty()) {
//            String imagePath = saveImage(imageFile); // Lưu hình ảnh
//            movie.setImage(imagePath); // Cập nhật đường dẫn hình ảnh
//        }
//        movieService.saveMovie(movie);
//        return "redirect:/movies"; // Chuyển hướng về danh sách phim
//    }
//
//    // Hiển thị form sửa phim
//    @GetMapping("/edit/{id}")
//    public String showEditMovieForm(@PathVariable int id, Model model) {
//        Movie movie = movieService.getMovieById(id);
//        model.addAttribute("movie", movie);
//        model.addAttribute("categories", movieService.getAllCategories()); // Để có thể chọn thể loại khi sửa
//        return "/admin/EditMovies";
//    }
//
//    // Cập nhật thông tin phim
//    @PostMapping("/update/{id}")
//    public String updateMovie(@PathVariable int id, @ModelAttribute Movie movie, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
//        movie.setMovieId(id); // Đặt ID cho phim trước khi lưu
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String imagePath = saveImage(imageFile); // Lưu hình ảnh mới
//            movie.setImage(imagePath); // Cập nhật đường dẫn hình ảnh mới
//        } else {
//            Movie existingMovie = movieService.getMovieById(id);
//            movie.setImage(existingMovie.getImage()); // Giữ nguyên hình ảnh cũ nếu không có hình ảnh mới
//        }
//        movieService.saveMovie(movie); // Cập nhật phim
//        return "redirect:/movies"; // Chuyển hướng về danh sách phim sau khi cập nhật
//    }
//
//    // Xóa phim
//    @GetMapping("/delete/{id}")
//    public String deleteMovie(@PathVariable int id) {
//        movieService.deleteMovie(id);
//        return "redirect:/movies"; // Chuyển hướng về danh sách phim sau khi xóa
//    }
//
//    // Phương thức lưu hình ảnh
//    private String saveImage(MultipartFile imageFile) {
//        String uploadDir = "D:/Projcet/Test_Project/uploads";
//        // Đường dẫn bên ngoài thư mục `src`
//        String originalFilename = imageFile.getOriginalFilename();
//
//        if (originalFilename == null || originalFilename.isEmpty()) {
//            throw new IllegalArgumentException("Tên tệp không hợp lệ.");
//        }
//
//        String filePath = uploadDir + File.separator + originalFilename;
//
//        try {
//            File directory = new File(uploadDir);
//            if (!directory.exists()) {
//                directory.mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
//            }
//            imageFile.transferTo(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
//        }
//
//        return "/uploads/" + originalFilename; // Trả về đường dẫn ảnh tương đối
//    }
//
//
//
//
//
//}

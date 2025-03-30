package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Candles;
import com.springboot.dev_spring_boot_demo.service.CandlesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/candles")
public class CandlesController {
    private final CandlesService candlesService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/default/images";

    public CandlesController(CandlesService candlesService) {
        this.candlesService = candlesService;
    }

    @GetMapping("/list-candles")
    public String list(Model model) {
        model.addAttribute("candles", candlesService.findAll());
        return "list-candles";
    }

    @GetMapping("/candles-form-insert")
    public String formInsert(Model model) {
        model.addAttribute("candles", new Candles());
        return "candles-form-insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("candles") Candles candles,
                       BindingResult bindingResult,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            if (candles.getId() == 0) {
                return "candles-form-insert";
            } else {
                return "candles-form-update";
            }
        }

        if (candles.getId() == 0 && imageFile.isEmpty()) {
            bindingResult.rejectValue("imageURL", "error.candles", "Candles image is required");
            return "candles-form-insert";
        }

        if (!imageFile.isEmpty()) {
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String originalFileName = imageFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID() + fileExtension;

            Path filePath = Paths.get(UPLOAD_DIRECTORY, newFileName);
            Files.write(filePath, imageFile.getBytes());

            if (candles.getId() != 0 && candles.getImageURL() != null) {
                String oldImage = candles.getImageURL().replace("/default/images/", "");
                Path oldPath = Paths.get(UPLOAD_DIRECTORY, oldImage);
                if (Files.exists(oldPath)) Files.delete(oldPath);
            }

            candles.setImageURL("/default/images/" + newFileName);
        } else if (candles.getId() == 0) {
            // Đã xử lý validation phía trên
        }

        candlesService.save(candles);
        return "redirect:/candles/list-candles";
    }

    @GetMapping("/candles-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        try {
            Candles candle = candlesService.findById(id);
            if (candle == null) {
                throw new IllegalArgumentException("Invalid candle Id: " + id);
            }
            System.out.println("Found candle to update: " + candle); // Debug log
            model.addAttribute("candles", candle); // Đảm bảo attribute name là "candles"
            return "candles-form-update";
        } catch (Exception e) {
            System.err.println("Error in formUpdate: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/candles/list-candles";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) throws IOException {
        Candles candles = candlesService.findById(id);
        if (candles.getImageURL() != null) {
            String imageName = candles.getImageURL().replace("/default/images/", "");
            Path imagePath = Paths.get(UPLOAD_DIRECTORY, imageName);
            if (Files.exists(imagePath)) Files.delete(imagePath);
        }
        candlesService.deleteById(id);
        return "redirect:/candles/list-candles";
    }
}
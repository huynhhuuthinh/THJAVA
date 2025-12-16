package hsa.controller;

import hsa.service.DanhMucService;
import hsa.service.MonAnService;
import hsa.service.BanAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private DanhMucService danhMucService;
    
    @Autowired
    private MonAnService monAnService;
    
    @Autowired
    private BanAnService banAnService;
    
    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/menu";
    }
    
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        model.addAttribute("monAnList", monAnService.getMonAnDangBan());
        return "menu";
    }
    
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/dashboard";
    }
}
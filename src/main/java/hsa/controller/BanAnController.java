package hsa.controller;

import hsa.entity.BanAn;
import hsa.service.BanAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/banan")
public class BanAnController {
    
    @Autowired
    private BanAnService banAnService;
    
    @GetMapping
    public String listBanAn(Model model) {
        model.addAttribute("banAnList", banAnService.getAllBanAn());
        return "admin/banan/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("banAn", new BanAn());
        return "admin/banan/form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        BanAn banAn = banAnService.getBanAnById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn"));
        model.addAttribute("banAn", banAn);
        return "admin/banan/form";
    }
    
    @PostMapping("/save")
    public String saveBanAn(@ModelAttribute BanAn banAn) {
        banAnService.saveBanAn(banAn);
        return "redirect:/admin/banan";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBanAn(@PathVariable Integer id) {
        banAnService.deleteBanAn(id);
        return "redirect:/admin/banan";
    }
}
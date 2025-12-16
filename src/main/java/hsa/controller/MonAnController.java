package hsa.controller;

import hsa.entity.MonAn;
import hsa.service.DanhMucService;
import hsa.service.MonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/monan")
public class MonAnController {
    
    @Autowired
    private MonAnService monAnService;
    
    @Autowired
    private DanhMucService danhMucService;
    
    @GetMapping
    public String listMonAn(Model model) {
        model.addAttribute("monAnList", monAnService.getAllMonAn());
        return "admin/monan/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("monAn", new MonAn());
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        return "admin/monan/form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        MonAn monAn = monAnService.getMonAnById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy món ăn"));
        model.addAttribute("monAn", monAn);
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        return "admin/monan/form";
    }
    
    @PostMapping("/save")
    public String saveMonAn(@ModelAttribute MonAn monAn) {
        monAnService.saveMonAn(monAn);
        return "redirect:/admin/monan";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteMonAn(@PathVariable Integer id) {
        monAnService.deleteMonAn(id);
        return "redirect:/admin/monan";
    }
    
    @GetMapping("/toggle/{id}")
    public String toggleTrangThai(@PathVariable Integer id) {
        MonAn monAn = monAnService.getMonAnById(id).orElse(null);
        if (monAn != null) {
            monAnService.updateTrangThai(id, !monAn.getTrangThai());
        }
        return "redirect:/admin/monan";
    }
}
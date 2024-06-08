package com.example.WatchShop1.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WatchShop1.Entity.AccountDTO;
import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Service.iml.AccountService;


import java.util.List;
@Controller
@RequestMapping("/admin")
public class CustomerController {
	@Autowired
	private AccountService accountServiceImpl;
	

	public CustomerController(AccountService accountServiceImpl) {
		this.accountServiceImpl = accountServiceImpl;
	}
	
	@GetMapping("/customer/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("QLKH".equals(page)) {
	        List<AccountEntity> customersList = accountServiceImpl.findCustomers();
	        model.addAttribute("customers", customersList);
	        AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	        model.addAttribute("page","QLKH");
	        return "/admin/doc/index_admin";  
	    } else {
	        
	        return "error"; 
	    }
	}
	@GetMapping("/add_customer/pagecontrol")
	public String getAddPage(@RequestParam("page") String page, Model model) {
	    if ("Add_Customer".equals(page)) {
	        model.addAttribute("page","Add_Customer");
	        AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
	
	@PostMapping("/add_customer/pagecontrol")
	public String addCustomer(@ModelAttribute("accountDTO") AccountDTO accountDTO, Model model) {
		accountServiceImpl.creatCustomer(accountDTO);
		return "redirect:/admin/customer/pagecontrol?page=QLKH";
	}
	
	@GetMapping("/qlkhachhang/delete/{id}")
	 public String deleteKhachhang(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		accountServiceImpl.deleteTaikhoan(id);
		redirectAttributes.addAttribute("deleteSuccess", true);
      return "redirect:/admin/customer/pagecontrol?page=QLKH";
  }
	@PostMapping("/customer/pagecontrol")
	public String updateCustomer(@ModelAttribute("accountDTO") AccountDTO accountDTO,BindingResult result,
			Model model ,RedirectAttributes redirectAttributes) {
        accountServiceImpl.updateCustomer(accountDTO);
        redirectAttributes.addAttribute("updateSuccess", true);
		return "redirect:/admin/customer/pagecontrol?page=QLKH";
	}
}

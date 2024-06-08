package com.example.WatchShop1.Controller;

import java.util.List;

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
@Controller
@RequestMapping("/admin")
public class StaffController {
	private AccountService accountServiceImpl;
	
	@Autowired
	public StaffController(AccountService accountServiceImpl) {
		this.accountServiceImpl = accountServiceImpl;
	}
	
	@GetMapping("/staff/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("QLNV".equals(page)) {
	        List<AccountEntity> employeeList = accountServiceImpl.findEmployees();
	        model.addAttribute("employees", employeeList);
	        AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	        model.addAttribute("page","QLNV");
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
	
	@GetMapping("/add_staff/pagecontrol")
	public String getAddPage(@RequestParam("page") String page, Model model) {
	    if ("Add_Staff".equals(page)) {
//	        List<Account> accountList = accountService.getAllAccounts();
//	        model.addAttribute("accounts", accountList);
	        model.addAttribute("page","Add_Staff");
	        AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
	
	@PostMapping("/add_staff/pagecontrol")
	public String addCustomer(@ModelAttribute("accountDTO") AccountDTO accountDTO, Model model) {
		accountServiceImpl.creatStaff(accountDTO);
		return "redirect:/admin/staff/pagecontrol?page=QLNV";
	}
	
	@GetMapping("/qlnhanvien/delete/{id}")
	 public String deleteNhanvien(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		accountServiceImpl.deleteTaikhoan(id);
		redirectAttributes.addAttribute("deleteSuccess", true);
       return "redirect:/admin/staff/pagecontrol?page=QLNV";
   }
	@PostMapping("/staff/pagecontrol")
	public String updateStaff(@ModelAttribute("accountDTO") AccountDTO accountDTO,BindingResult result,
			Model model) {
        accountServiceImpl.updateStaff(accountDTO);
		return "redirect:/admin/staff/pagecontrol?page=QLNV";
	}
}

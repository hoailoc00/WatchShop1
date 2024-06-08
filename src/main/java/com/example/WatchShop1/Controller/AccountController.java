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
import com.example.WatchShop1.Entity.PermissionEntity;
import com.example.WatchShop1.Service.iml.AccountService;
import com.example.WatchShop1.Service.iml.PermissionService;
@Controller
@RequestMapping("/admin")
public class AccountController {
	@Autowired
	private AccountService accountServiceImpl;
	@Autowired
	private PermissionService roleServiceImpl;
	public AccountController(AccountService accountServiceImpl, PermissionService roleServiceImpl) {
		this.accountServiceImpl = accountServiceImpl;
		this.roleServiceImpl = roleServiceImpl;
	}
	
	@GetMapping("/account/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("QLTK".equals(page)) {
	        List<AccountEntity> accountList = accountServiceImpl.findAccounts();
	        model.addAttribute("accounts", accountList);
	        AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	    	List<PermissionEntity> accountRoles = roleServiceImpl.getAllRole();
	    	model.addAttribute("roles",accountRoles);
	        model.addAttribute("page","QLTK");
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
	@GetMapping("/add_account/pagecontrol")
	public String getAddPage(@RequestParam("page") String page, Model model) {
	    if ("Add_Account".equals(page)) {
	    	AccountDTO accountDTO = new AccountDTO();
	    	model.addAttribute("accountDTO",accountDTO);
	    	List<PermissionEntity> accountRoles = roleServiceImpl.getAllRole();
	    	model.addAttribute("roles",accountRoles);
	        model.addAttribute("page","Add_Account");
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
	
	@PostMapping("/add_account/pagecontrol")
	public String addCustomer(@ModelAttribute("accountDTO") AccountDTO accountDTO, Model model) {
		accountServiceImpl.creatAccount(accountDTO);
		return "redirect:/admin/account/pagecontrol?page=QLTK";
	}
	
	@GetMapping("/qltaikhoan/delete/{id}")
	 public String deleteTaikhoan(@PathVariable("id") Integer id , RedirectAttributes redirectAttributes) {
		accountServiceImpl.deleteTaikhoan(id);
		redirectAttributes.addAttribute("deleteSuccess", true);
       return "redirect:/admin/account/pagecontrol?page=QLTK";
   }
	
	@PostMapping("/account/pagecontrol")
	public String updateAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO,BindingResult result,
			Model model) {
        accountServiceImpl.updateAccount(accountDTO);
		return "redirect:/admin/account/pagecontrol?page=QLTK";
	}
	
}

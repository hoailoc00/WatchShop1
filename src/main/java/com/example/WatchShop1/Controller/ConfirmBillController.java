package com.example.WatchShop1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WatchShop1.Service.iml.AccountService;
@Controller
@RequestMapping("/admin")
public class ConfirmBillController {
	@Autowired
	private AccountService accountService;
	

	public ConfirmBillController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/*/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("POS".equals(page)) {
//	        List<Account> accountList = accountService.getAllAccounts();
//	        model.addAttribute("accounts", accountList);
	        
	        return "/admin/doc/phan-mem-ban-hang";  // Return the name of the view to be displayed
	    } else {
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}

}

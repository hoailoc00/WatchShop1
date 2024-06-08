package com.example.WatchShop1.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.WatchShop1.Entity.AccountDTO;
import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Entity.ProductTypeEntity;
import com.example.WatchShop1.Entity.ReceivedDetailDTO;
import com.example.WatchShop1.Entity.ReceivedDetailEntity;
import com.example.WatchShop1.Entity.ReceivedEntity;
import com.example.WatchShop1.Entity.SupplierEntity;
import com.example.WatchShop1.Service.iml.AccountService;
import com.example.WatchShop1.Service.iml.ProductService;
import com.example.WatchShop1.Service.iml.ProductTypeService;
import com.example.WatchShop1.Service.iml.ReceidvedDetailService;
import com.example.WatchShop1.Service.iml.ReceivedService;
import com.example.WatchShop1.Service.iml.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmployeeController {
	@Autowired
	private ProductService productServiceImpl;
	@Autowired
	private ProductTypeService productTypeServiceImpl;
	@Autowired
	private SupplierService supplierServiceImpl;
	@Autowired 
	private AccountService accountServiceImpl;
	@Autowired
	private ReceivedService receivedServiceImpl;
	@Autowired
	private ReceidvedDetailService receivedDetailServiceImpl;

	public EmployeeController(ProductService productServiceImpl, ProductTypeService productTypeServiceImpl,AccountService accountServiceImpl,ReceidvedDetailService receivedDetailServiceImpl,
			SupplierService supplierServiceImpl) {
		super();
		this.productServiceImpl = productServiceImpl;
		this.productTypeServiceImpl = productTypeServiceImpl;
		this.supplierServiceImpl = supplierServiceImpl;
		this.accountServiceImpl = accountServiceImpl;
		this.receivedServiceImpl= receivedServiceImpl;
		this.receivedDetailServiceImpl = receivedDetailServiceImpl;
	}

	@GetMapping("/employee/receice")
	public String Receive(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		AccountEntity accountEntity = accountServiceImpl.findByUsername(username);
		model.addAttribute("account", accountEntity);
		return "/staff/doc/index_NV2";
	}
	@GetMapping("/employee/addreceived")
	public String addReceive(Model model,@RequestParam(name = "receivedId", required = false) Integer receivedId,
			@ModelAttribute("receivedDTO") ReceivedDetailDTO receivedDetailDTO) {
		 if (receivedId != null) {
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String username = authentication.getName();
				AccountEntity accountEntity = accountServiceImpl.findByUsername(username);
				model.addAttribute("account", accountEntity);
				List<ProductEntity> productList = productServiceImpl.findAllProducts();
				model.addAttribute("products", productList);
				List<SupplierEntity> supplierList = supplierServiceImpl.getAllSuppliers();
				model.addAttribute("suppliers", supplierList);
				List<ProductTypeEntity> typeList = productTypeServiceImpl.getAllProductTypes();
				model.addAttribute("types", typeList);
				ReceivedEntity savedReceived = receivedServiceImpl.findReceivedById(receivedId);
		        // Thêm thông tin đơn nhập hàng vào model
		        model.addAttribute("received", savedReceived);
		    } else {
		List<ProductEntity> productList = productServiceImpl.findAllProducts();
		model.addAttribute("products", productList);
		List<SupplierEntity> supplierList = supplierServiceImpl.getAllSuppliers();
		model.addAttribute("suppliers", supplierList);
		List<ProductTypeEntity> typeList = productTypeServiceImpl.getAllProductTypes();
		model.addAttribute("types", typeList);
		
		model.addAttribute("page","home");
		// Lấy ngày hiện tại dưới dạng LocalDate
        LocalDate localDate = LocalDate.now();
        // Chuyển đổi LocalDate thành đối tượng Date
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		AccountEntity accountEntity = accountServiceImpl.findByUsername(username);
		model.addAttribute("account", accountEntity);
		
		int accountId = accountEntity.getAccount_id();
		
		ReceivedEntity receive = new ReceivedEntity();
		AccountEntity account = new AccountEntity();
		account.setAccount_id(accountId);
		receive.setAccountEntity(accountEntity);
		receive.setReceived_date(currentDate);
		receive.setReceived_money(0);
		
		ReceivedEntity savedReceived = receivedServiceImpl.addReceived(receive);
		
		
		model.addAttribute("received", savedReceived);
		ReceivedDetailDTO receivedDTO = new ReceivedDetailDTO();
	    // Thêm đối tượng vào model với tên 'receivedDTO'
	    model.addAttribute("receivedDTO", receivedDTO);
		    }
		return "/staff/doc/index_NV";
	}
	@PostMapping("/employee/addreceived")
	public String addReceivedDetail(@ModelAttribute("receivedDTO") ReceivedDetailDTO receivedDetailDTO, Model model , RedirectAttributes redirectAttributes) {
		
		System.out.println("ID nhập hàng " + receivedDetailDTO.getReceived_id() );
		int receivedId = receivedDetailDTO.getReceived_id();
		System.out.println("Giá tiền của 1 sp " + receivedDetailDTO.getProduct_inprice() );
		boolean result =receivedDetailServiceImpl.createReceivedDetail(receivedDetailDTO);
		if(result == true) {
			redirectAttributes.addAttribute("addSuccess", true);
		} else {
			redirectAttributes.addAttribute("addSuccess", false);
		}
		ReceivedDetailDTO receivedDTO = new ReceivedDetailDTO();
	    // Thêm đối tượng vào model với tên 'receivedDTO'
	    model.addAttribute("receivedDTO", receivedDTO);
		return "redirect:/employee/addreceived" + "?receivedId=" + receivedId;
	}
	@GetMapping("/employee/receive-order")
	public String approveOrder(@RequestParam("receivedId") int receivedId, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		AccountEntity accountEntity = accountServiceImpl.findByUsername(username);
		model.addAttribute("account", accountEntity);
		List<ReceivedDetailEntity> listreceiveddetail = receivedDetailServiceImpl.findAllById(receivedId);
		model.addAttribute("receiveddetails",listreceiveddetail);
		ReceivedEntity received = receivedServiceImpl.findReceivedById(receivedId);
		model.addAttribute("received",received);
		
		
		return "/staff/doc/received_order" ;
	}

}

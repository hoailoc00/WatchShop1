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

import com.example.WatchShop1.Entity.ProductDTO;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Entity.ProductTypeEntity;
import com.example.WatchShop1.Entity.SupplierDTO;
import com.example.WatchShop1.Entity.SupplierEntity;
import com.example.WatchShop1.Service.iml.ProductService;
import com.example.WatchShop1.Service.iml.ProductTypeService;
import com.example.WatchShop1.Service.iml.SupplierService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ProductService productServiceImpl;
	@Autowired
	private ProductTypeService productTypeServiceImpl;
	@Autowired
	private SupplierService supplierServiceImpl;

	public ProductController(ProductService productServiceImpl, ProductTypeService productTypeServiceImpl,
			SupplierService supplierServiceImpl) {
		this.productServiceImpl = productServiceImpl;
		this.productTypeServiceImpl = productTypeServiceImpl;
		this.supplierServiceImpl = supplierServiceImpl;
	}
	
//	@GetMapping("/")
//    public String home(Model model) {
//		List<ProductEntity> productList = productServiceImpl.findAllProducts();
//		model.addAttribute("products", productList);
//		List<SupplierEntity> supplierList = supplierServiceImpl.getAllSuppliers();
//		model.addAttribute("suppliers", supplierList);
//		List<ProductTypeEntity> typeList = productTypeServiceImpl.getAllProductTypes();
//		model.addAttribute("types", typeList);
//		model.addAttribute("page","home");
//		return "redirect:/home/pagecontrol?page=home"; 
//        
//    }
	@GetMapping("/product/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
		if ("QLSP".equals(page)) {
			List<ProductEntity> productList = productServiceImpl.findAllProducts();
			model.addAttribute("products", productList);
			List<SupplierEntity> supplierList = supplierServiceImpl.getAllSuppliers();
			model.addAttribute("suppliers", supplierList);
			List<ProductTypeEntity> typeList = productTypeServiceImpl.getAllProductTypes();
			model.addAttribute("types", typeList);
			ProductDTO productDTO = new ProductDTO();
	    	model.addAttribute("productDTO",productDTO);
			model.addAttribute("page", "QLSP");
			return "/admin/doc/index_admin"; // Return the name of the view to be displayed
		} else {
			// Xử lý các trang khác tại đây (nếu có)
			return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
		}
	}

	@GetMapping("/add_product/pagecontrol")
	public String getAddPage(@RequestParam("page") String page, Model model) {
		if ("Add_Product".equals(page)) {
			ProductDTO productDTO = new ProductDTO();
			model.addAttribute("productDTO", productDTO);
			SupplierDTO supplierDTO = new SupplierDTO();
			model.addAttribute("supplierDTO", supplierDTO);
			ProductTypeEntity product_type = new ProductTypeEntity();
			model.addAttribute("addType", product_type);
			List<SupplierEntity> supplierList = supplierServiceImpl.getAllSuppliers();
			model.addAttribute("suppliers", supplierList);
			List<ProductTypeEntity> typeList = productTypeServiceImpl.getAllProductTypes();
			model.addAttribute("types", typeList);
			
			model.addAttribute("page", "Add_Product");
			return "/admin/doc/index_admin";
		} else {

			return "error";
		}
	}

	@GetMapping("/qlsanpham/delete/{id}")
	public String deleteSanpham(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		productServiceImpl.deleteSanpham(id);
		redirectAttributes.addAttribute("deleteSuccess", true);
		return "redirect:/admin/product/pagecontrol?page=QLSP";
	}

	

	@PostMapping("/add_product/pagecontrol")
	public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, Model model,
			@RequestParam(name = "form_add_supplier", required = false) String form_add_supplier,
			@RequestParam(name = "form_add_product", required = false) String form_add_product,
			@RequestParam(name = "form_add_type", required = false) String form_add_type,
			@RequestParam(name = "supplier_name", required = false) String supplier_name,
			@RequestParam(name = "typeName", required = false) String type_name) {
		if (form_add_supplier != null) {
			// Xử lý dữ liệu của biểu mẫu creat supplier
			SupplierEntity supplier = new SupplierEntity();
			supplier.setSupplierName(supplier_name);
			supplier = supplierServiceImpl.creatSupplier(supplier);
		} else if (form_add_product != null) {
			// Xử lý lưu thông tin sản phẩm
			productServiceImpl.saveProduct(productDTO);
		} else if (form_add_type != null) {
			// Xử lý dữ liệu của biểu mẫu creat type
			ProductTypeEntity productType = new ProductTypeEntity();
			productType.setTypeName(type_name);
			productType = productTypeServiceImpl.createProductType(productType);
		} else {
			// Xử lý khi không có biểu mẫu nào được gửi
			System.out.println("Thêm thất bại");

		}
		return "redirect:/admin/product/pagecontrol?page=QLSP";
	}
	@PostMapping("/product/pagecontrol")
	public String updateProduct(@ModelAttribute("productDTO") ProductDTO productDTO,BindingResult result,
			Model model) {
		
        productServiceImpl.updateProduct(productDTO);
		return "redirect:/admin/product/pagecontrol?page=QLSP";
	}
}

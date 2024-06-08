package com.example.WatchShop1.Service.iml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.WatchShop1.Entity.ProductDTO;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Entity.ProductTypeEntity;
import com.example.WatchShop1.Entity.SupplierEntity;
import com.example.WatchShop1.Repository.ProductRepository;
import com.example.WatchShop1.Service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
    ResourceLoader resourceLoader;
	
	@Override
	public ProductEntity save(ProductEntity product) {
		return this.productRepository.save(product);
	}

	@Override
	public ProductEntity update(ProductEntity product) {
		return product;
	}

	@Override
	public List<ProductEntity> getAll() {
		return this.productRepository.findAll();
	}

	@Override
	public int totalItem() {
		return (int) productRepository.count();
	}


	@Override
	public ProductEntity findByID(int id) {
		return  this.productRepository.findById(id).get();
	}

	@Override
	public List<ProductEntity> searchProduct(String key) {
		return null;
	}

	@Override
	public Page<ProductEntity> getAll(Integer pageNo) {
		Pageable pageable= PageRequest.of(pageNo-1, 1);
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Page<ProductEntity> getAllbyType(Integer pageNo, ProductTypeEntity type) {
		Pageable pageable= PageRequest.of(pageNo-1,6);
		return this.productRepository.findByTypeId(type, pageable);
	}

	@Override
	public Boolean updateInventoryProduct(int quantity, int product_id) {
		try {
			this.productRepository.updateInventoryProduct(quantity, product_id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<ProductEntity> findAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAllProducts();
	}

	 @Override
	    public void deleteSanpham(Integer id) {
	        ProductEntity product = productRepository.findById(id).orElse(null);
	        if (product != null) {
	            product.setIs_deleted(1);
	            productRepository.save(product);
	        }
	    }
	 @Override
	 public ProductEntity getProductById(Integer id) {
	        return productRepository.findById(id).orElse(null);
	    }

	@Override
	public void saveProduct(ProductDTO productDTO) {
        // Tạo một đối tượng Product từ thông tin trong ProductDTO
		Date creatAt = new Date();
        ProductEntity product = new ProductEntity();
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_inventory(productDTO.getProduct_inventory());
        product.setProduct_saleprice(productDTO.getProduct_saleprice());
        product.setProduct_inprice(productDTO.getProduct_inprice());
        product.setProduct_description(productDTO.getProduct_description());
        product.setProduct_warranty(creatAt);
        SupplierEntity supplier = new SupplierEntity();
        supplier.setSupplier_id(productDTO.getSupplier_id());
        product.setSupplierEntity(supplier);
        ProductTypeEntity productType = new ProductTypeEntity();
        productType.setType_id(productDTO.getType_id());
        product.setTypeId(productType);
        
        // Để lưu hình ảnh, bạn cần code ở đây để di chuyển hình ảnh vào thư mục /static/assets/image
        // và lưu đường dẫn của hình ảnh vào đối tượng Product
        
     // Xử lý hình ảnh
        MultipartFile imageFile = productDTO.getProduct_image();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Lấy thư mục nguồn
                Resource resource = resourceLoader.getResource("classpath:/static/assets/img-sanpham");
                File uploadDir = resource.getFile();
                

                // Tạo tên file duy nhất để tránh ghi đè
                String originalFilename = imageFile.getOriginalFilename();
                String filename = System.currentTimeMillis() + "_" + originalFilename;

                // Lưu trữ file
                Path copyLocation = Paths.get(uploadDir.getAbsolutePath() + File.separator + filename);
                Files.copy(imageFile.getInputStream(), copyLocation);

                // Lưu đường dẫn của hình ảnh vào đối tượng Product
                product.setProduct_image(filename);
                // Lưu sản phẩm vào cơ sở dữ liệu
                productRepository.save(product);
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý nếu có lỗi khi lưu hình ảnh
            }
        }
        
        
    }

	@Override
	public void updateProduct(ProductDTO productDTO) {
	    try {
	        ProductEntity product = productRepository.findById(productDTO.getProduct_id())
	                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID " + productDTO.getProduct_id()));
	        product.setProduct_name(productDTO.getProduct_name());
	        product.setProduct_inventory(productDTO.getProduct_inventory());
	        SupplierEntity supplier = new SupplierEntity();
	        supplier.setSupplier_id(productDTO.getSupplier_id());
	        product.setSupplierEntity(supplier);
	        product.setProduct_saleprice(productDTO.getProduct_saleprice());
	        product.setProduct_inprice(productDTO.getProduct_inprice());
	        ProductTypeEntity productType = new ProductTypeEntity();
	        productType.setType_id(productDTO.getType_id());
	        product.setTypeId(productType);
	        product.setProduct_description(productDTO.getProduct_description());
	        product.setIs_deleted(0);

	        MultipartFile imageFile = productDTO.getProduct_image();
	        if (imageFile != null && !imageFile.isEmpty()) {
	            // Xử lý lưu hình ảnh vào đây
	            try {
	                // Lấy thư mục nguồn
	                Resource resource = resourceLoader.getResource("classpath:/static/assets/img-sanpham");
	                File uploadDir = resource.getFile();

	                // Tạo tên file duy nhất để tránh ghi đè
	                String originalFilename = imageFile.getOriginalFilename();
	                String filename = System.currentTimeMillis() + "_" + originalFilename;

	                // Lưu trữ file
	                Path copyLocation = Paths.get(uploadDir.getAbsolutePath() + File.separator + filename);
	                Files.copy(imageFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

	                // Lưu đường dẫn của hình ảnh vào đối tượng Product
	                product.setProduct_image(filename);
	            } catch (IOException e) {
	                e.printStackTrace();
	                // Xử lý nếu có lỗi khi lưu hình ảnh
	            }
	        }

	        // Lưu sản phẩm vào cơ sở dữ liệu sau khi cập nhật thông tin
	        productRepository.save(product);
	    } catch (Exception e) {
	        // Xử lý lỗi khi update không thành công và hiển thị thông báo lỗi
	        e.printStackTrace();
	        // Đoạn mã xử lý thông báo lỗi ở đây, ví dụ: log lỗi, hiển thị thông báo cho người dùng, v.v.
	    }
	}
}

package com.example.WatchShop1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class DatabaseCheckController {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @GetMapping("/check-database-connection")
	    public ResponseEntity<String> checkDatabaseConnection() {
	        try {
	            jdbcTemplate.execute("SELECT 1");
	            return ResponseEntity.ok("Database connection is successful!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to connect to the database: " + e.getMessage());
	        }
	    }
}

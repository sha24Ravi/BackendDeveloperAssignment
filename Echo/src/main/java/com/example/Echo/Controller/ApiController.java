package com.example.Echo.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Echo.LogicService.LogicService;
import com.example.Echo.ResponseDto.ApiResponse;
import com.example.Echo.ResponseDto.MatrixTransformationResponse;

@RestController
public class ApiController {

	@Autowired
	public LogicService ls;
	

	@PostMapping(value = "/processCSV")
	public ResponseEntity<ApiResponse<MatrixTransformationResponse>> processCsv(@RequestParam("file") MultipartFile filePath) {
		try {
			// Check if file is null or not a CSV
			if (filePath == null || filePath.getOriginalFilename() == null
					|| !filePath.getOriginalFilename().toLowerCase().endsWith(".csv")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse<>(400, "Only CSV files are accepted", null));
			}

			// Process CSV File Input to Matrix List 
			List<List<String>> inputMatrix = ls.matrixStream(filePath);

			if (inputMatrix.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ApiResponse<>(400, "CSV file is empty", null));
			}

			//Input Matrix is passed to the Service Layer  
			MatrixTransformationResponse computedMatrix = ls.computeLogic(inputMatrix);
			//prints the transformed Matrix Data as expected
			System.out.print(computedMatrix.getCombinedData());
			
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ApiResponse<>(202, "CSV processed successfully", computedMatrix));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(500, "Internal server error: " + e.getMessage(), null));
		}
	}

}

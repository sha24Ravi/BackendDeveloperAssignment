package com.example.Echo.LogicServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Echo.ResponseDto.MatrixTransformationResponse;


//Service Interface Layer
@Service

public interface LogicServiceImpl {
	
	
	public MatrixTransformationResponse computeLogic(List<List<String>> inputMatrix);
	public List<List<String>>  matrixStream(MultipartFile file);
	public String printMatrix(List<List<String>> matrix);
	public String flatenMatrix(List<List<String>> matrix);
	public int calSum( List<List<String>> ls);
	public int calProduct(List<List<String>>ls);
	public String inverRows(List<List<String>> matrix);

}


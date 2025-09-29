package com.example.Echo.LogicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.Echo.LogicServiceImpl.LogicServiceImpl;
import com.example.Echo.ResponseDto.MatrixTransformationResponse;

@Component
public class LogicService implements LogicServiceImpl {

	// method to transform the Csv file data into List Matirx
	@Override
	public List<List<String>> matrixStream(MultipartFile filePath) {

		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(filePath.getInputStream()));
			List<List<String>> matrix = new ArrayList<>();
			String line;
			while ((line = bf.readLine()) != null) {
				if (line.matches("[0-9,]+")) {
					List<String> columns = Arrays.asList(line.split(","));
					matrix.add(columns);
				} else {

					throw new IllegalArgumentException("Invalid line detected in CSV: " + line);
				}
			}

			return matrix;

		} catch (IOException e) {
			throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
		}

	}

	// Method to flaten the Matrix
	@Override
	public String flatenMatrix(List<List<String>> matrix) {

		if (matrix == null || matrix.isEmpty()) {
			throw new IllegalArgumentException("Matrix cannot be null or empty");
		}
		List<String> list = matrix.stream().flatMap(List::stream).collect(Collectors.toList());
		String result = String.join(",", list);
		return result;
	}

	// Method to Calculate Sum
	@Override
	public int calSum(List<List<String>> matrix) {

		if (matrix == null || matrix.isEmpty()) {
			throw new IllegalArgumentException("Matrix cannot be null or empty");
		}
		int sum = 0;
		List<String> list = matrix.stream().flatMap(List::stream).collect(Collectors.toList());
		for (int i = 0; i < list.size(); i++) {
			sum += Integer.parseInt(list.get(i));
		}

		return sum;

	}

	// Method to Calculate Product
	@Override
	public int calProduct(List<List<String>> matrix) {

		int prod = 1;
		List<String> list = matrix.stream().flatMap(List::stream).collect(Collectors.toList());
		for (int i = 0; i < list.size(); i++) {
			prod *= Integer.parseInt(list.get(i));
		}
		return prod;

	}

	// Method to Inverse the Matrix
	@Override
	public String inverRows(List<List<String>> matrix) {
		String[][] matrixArray = getMatrixArray(matrix);

		// Transpose logic
		for (int i = 0; i < matrixArray.length; i++) {
			for (int j = i + 1; j < matrixArray[0].length; j++) {
				String temp = matrixArray[i][j];
				matrixArray[i][j] = matrixArray[j][i];
				matrixArray[j][i] = temp;
			}
		}

		// Build readable string
		StringBuilder invertedMatrix = new StringBuilder();
		for (int i = 0; i < matrixArray.length; i++) {
			invertedMatrix.append(String.join(",", matrixArray[i])).append("\n");
		}

		return invertedMatrix.toString().trim();

	}

	// Util Method to Transform into 2D array
	public String[][] getMatrixArray(List<List<String>> matrix) {

		if (matrix == null || matrix.isEmpty()) {
			throw new IllegalArgumentException("Matrix cannot be null or empty");
		}
		String[][] matrixArray = matrix.stream().map(row -> row.toArray(new String[0]))
				.toArray(col -> new String[col][]);
		return matrixArray;

	}

	@Override
	public String printMatrix(List<List<String>> matrix) {
		StringBuilder resultString = new StringBuilder();
		for (List<String> col : matrix) {
			resultString.append(String.join(",", col)).append("\n");
		}
		return resultString.toString();
	}

	// General Utility Method that invokes the compute logic
	@Override
	public MatrixTransformationResponse computeLogic(List<List<String>> inputMatrix) {

		if (inputMatrix == null || inputMatrix.isEmpty()) {
			throw new IllegalArgumentException("Input matrix cannot be null or empty");
		}
		
		String transformedMatrix = printMatrix(inputMatrix);
		String flattened = flatenMatrix(inputMatrix);
		String sum = String.valueOf(calSum(inputMatrix));
		String product = String.valueOf(calProduct(inputMatrix));
        String invertedMatrix= inverRows(inputMatrix);
        return new MatrixTransformationResponse(transformedMatrix,invertedMatrix,flattened,sum,product);
	}

}

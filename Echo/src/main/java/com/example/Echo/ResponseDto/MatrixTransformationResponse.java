package com.example.Echo.ResponseDto;

public class MatrixTransformationResponse {

	private String inputMatrix;
	private String flattenedMatrix;
	private String matrixSum;
	private String matrixProduct;
	private String invertedMatrix;
	
	private String combinedData;
	
	public MatrixTransformationResponse(String inputMatrix, String invertedMatrix, String flattenedMatrix, String matrixSum, String matrixProduct) {
        this.inputMatrix = inputMatrix;
        this.invertedMatrix = invertedMatrix;
        this.flattenedMatrix = flattenedMatrix;
        this.matrixSum = matrixSum;
        this.matrixProduct = matrixProduct;

        StringBuilder sb = new StringBuilder();
        sb.append("Echo:\n").append(inputMatrix).append("\n\n")
          .append("Inverted:\n").append(invertedMatrix).append("\n\n")
          .append("Flattened:\n").append(flattenedMatrix).append("\n\n")
          .append("Sum:\n").append(matrixSum).append("\n\n")
          .append("Product:\n").append(matrixProduct);

        this.combinedData = sb.toString();
    }
	
	public String getCombinedData() {
		return combinedData;
	}

	public void setCombinedData(String combinedData) {
		this.combinedData = combinedData;
	}

	public String getInputMatrix() {
		return inputMatrix;
	}
	public void setInputMatrix(String inputMatrix) {
		this.inputMatrix = inputMatrix;
	}
	public String getFlattenedMatrix() {
		return flattenedMatrix;
	}
	public void setFlattenedMatrix(String flattenedMatrix) {
		this.flattenedMatrix = flattenedMatrix;
	}
	public String getMatrixSum() {
		return matrixSum;
	}
	public void setMatrixSum(String matrixSum) {
		this.matrixSum = matrixSum;
	}
	public String getMatrixProduct() {
		return matrixProduct;
	}
	public void setMatrixProduct(String matrixProduct) {
		this.matrixProduct = matrixProduct;
	}
	public String getInvertedMatrix() {
		return invertedMatrix;
	}
	public void setInvertedMatrix(String invertedMatrix) {
		this.invertedMatrix = invertedMatrix;
	}
	
	
	
}

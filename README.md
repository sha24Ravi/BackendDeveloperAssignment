CSV Matrix Processing API – Build & Run Guide

Overview
This Spring Boot API processes a CSV file containing a square matrix of integers. It performs the following operations: Echo (original matrix), Invert (rows and columns swapped), Flatten (all values in one line), Sum (sum of all integers), and Product (product of all integers). The API returns a structured JSON response including status code, message, and matrix results.

Prerequisites
● Java 8+
● Maven 3.x
● IDE such as IntelliJ IDEA or Eclipse (optional)
● Postman (optional, for testing API)
Build Instructions
● Open a terminal in the project root folder and run:
~ mvn clean install
● This compiles the code and packages it into a JAR file.
● Run Instructions
● Run the Spring Boot application:
~ mvn spring-boot:run

API Details:
● The API server will start at: http://localhost:8080
● API Usage
● Endpoint: /processCSV
● Method: POST
● Request Parameter: file (CSV file)
Example using cURL: curl -F 'file=@/path/to/matrix.csv' "http://localhost:8080/processCSV".

Response Format
1. JSON response example:
{
"status": 202,
"message": "CSV processed successfully",
"data": {
"inputMatrix": "1,2,3\n4,5,6\n7,8,9",
"invertedMatrix": "1,4,7\n2,5,8\n3,6,9",
"flattenedMatrix": "1,2,3,4,5,6,7,8,9",
"matrixSum": "45",
"matrixProduct": "362880",
"combinedData": "Echo:\n1,2,3\n4,5,6\n7,8,9\n\nInverted:\n1,4,7\n2,5,8\n3,6,9\n\nFlattened:\n1,2,3,4,5,6,7,8,9\n\nSum:\n45\n\nProduct:\n362880"
}
}
2. Printed multi-line format from combinedData:
Echo:
1,2,3
4,5,6
7,8,9
Inverted:
1,4,7
2,5,8
3,6,9
Flattened:
1,2,3,4,5,6,7,8,9
Sum:
45
Product:
362880
   
Error Handling
● 400 BAD_REQUEST: CSV missing, invalid, or empty.
● 500 INTERNAL_SERVER_ERROR: unexpected errors during processing.
Example error response:
{
"status": 400,
"message": "CSV file is empty or invalid",
"data": null
}

Testing
Upload sample CSV files via Postman or cURL. Verify all operations are returned correctly. Edge cases like empty CSV, non-square matrices, or invalid data are handled with proper error responses.

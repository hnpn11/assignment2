package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

import response.ApiResponse;
import response.UserInfo;

public class ApiRequest {
	private static ApiRequest apiRequest = null;

	private ApiRequest() {
	}
	
	public static ApiRequest getInstance() {
		if (null == apiRequest) {
			apiRequest = new ApiRequest();
		}
		return apiRequest;
	}

	public ApiResponse callPostApi(String postRequest, URL url) throws IOException {
			ApiResponse apiResponse = new ApiResponse();
			
			//Initialize connection
		    HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
		    
		    //Set header
		    postConnection.setRequestMethod("POST");
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    
		    //Execute
		    OutputStream os = postConnection.getOutputStream();
		    os.write(postRequest.getBytes());
		    os.flush();
		    os.close();
		    
		    //Get response
		    int responseCode = postConnection.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		    
		    if (responseCode == HttpURLConnection.HTTP_OK) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        } 
		        in.close();
		        apiResponse.setStatus(responseCode);
		        apiResponse.setResponseString(response.toString());
		    } else {
		    	apiResponse.setStatus(responseCode);
		    }
			return apiResponse;
	}

	public ApiResponse callGetApi(HashMap<String, String> requestHeader, URL url) throws IOException {
		ApiResponse apiResponse = new ApiResponse();
		
		//Initialize connection
		HttpURLConnection getConection = (HttpURLConnection) url.openConnection();
		
		//Set Header
		getConection.setRequestMethod("GET");
		requestHeader.forEach((headerKey, headerValue) -> {
			getConection.setRequestProperty(headerKey,headerValue);
		});
		
		//Get Response
		int responseCode = getConection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	        		getConection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        } 
	        in.close();
	        apiResponse.setStatus(responseCode);
	        apiResponse.setResponseString(response.toString());
	    } else {
	    	apiResponse.setStatus(responseCode);
	    }
		return apiResponse;
	}
	
	public ApiResponse callApiToUploadImage(File file, URL url) throws IOException {
		ApiResponse apiResponse = new ApiResponse();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		FileBody fileBody = new FileBody(file);
		MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
		multipartEntity.addPart("file", fileBody);

		connection.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());
		connection.setRequestProperty("Authorization", UserInfo.getInstance().getToken());
		OutputStream out = connection.getOutputStream();
		try {
		    multipartEntity.writeTo(out);
		} finally {
		    out.close();
		}
		System.out.println(connection.getResponseCode());
        
		return apiResponse;
	}
}

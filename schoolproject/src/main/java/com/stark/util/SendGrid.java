package com.stark.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("rawtypes")
public class SendGrid {
	
	static String apiKeyUserNameAndPassword = "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";
	static String url = "https://api.sendgrid.com/v3/mail/send";

	@SuppressWarnings("unchecked")
	public static void putSendGrid(String toEmail,String username) throws Exception {
		/** personalizations Object */
		List personalizations = new ArrayList<Object>();

		/** To Object */
		List to = new ArrayList<Object>();
		Map<String, String> toObject = new HashMap<String, String>();
		toObject.put("email", toEmail);
		toObject.put("name", username);
		to.add(toObject);

		Map<String, Object> toMap = new HashMap<String, Object>();
		toMap.put("to", to);
		personalizations.add(toMap);

		/** From Object */
		Map<String, String> from = new HashMap<String, String>();
		from.put("email", "hoy.anilr@gmail.com");
		from.put("name", "Anil");

		/** Content Object */
		List content = new ArrayList<Object>();
		Map<String, Object> contentObj = new HashMap<String, Object>();
		contentObj.put("Type", "text/html");
		contentObj.put("value", "File Attached Please check it");
		content.add(contentObj);

		/** Velocity File */
		Writer velocity = VelocityTemplet.getVelocity(); // calling VelocityTemplet class
		String encodedString = Base64.encodeBase64String(velocity.toString().getBytes("ISO-8859-1"));// Base64
																										// encodedString
		System.out.println("encodedString:" + encodedString);

		/** File Attachment */
		List attachments = new ArrayList<Object>();
		Map<String, String> attach = new HashMap<String, String>();
		attach.put("content", encodedString);// The Base64 encoded content of the attachment.
		attach.put("filename", "Test.html");
		attachments.add(attach);

		/** Adding All Objects in a Map To convert Json formate */
		Map<String, Object> test = new HashMap<String, Object>();
		test.put("personalizations", personalizations);
		test.put("Subject", "BodyPart");
		test.put("from", from);
		test.put("content", content);
		test.put("attachments", attachments);

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(test);
	//	System.out.println(prettyJson);
		sendPostRequest(prettyJson, url, apiKeyUserNameAndPassword, false);
	}

	public static String sendPostRequest(String jsonInput, String url, String apiKeyUserNameAndPassword,
			boolean resubmitIf50X) throws Exception {
		String responseData = "";
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Bearer " + apiKeyUserNameAndPassword);
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(jsonInput));
			HttpResponse response = client.execute(httpPost);
			System.out.println("response:" + response);
			int statusCode = response.getStatusLine().getStatusCode();
			if (resubmitIf50X && (statusCode == 502 || statusCode == 503 || statusCode == 504)) {
				Thread.sleep(5000);
				response = client.execute(httpPost);
			}
		//	System.out.println("respones Content:" + response.getEntity().getContent());
		//	System.out.println("respones :" + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		//	System.out.println("rd \t" + rd);
			String inputLine = "";

			while (rd.readLine() != null) {
				responseData += inputLine;
		//		System.out.println("responseData \t" + responseData);
			}
			int i;
			while ((i = rd.read()) != -1) {
		//		System.out.print((char) i);
			}
			return responseData;
		} finally {
			try {
				if (client != null) {
					client.close();
				}
			} catch (Exception tx) {
				// do nothing
			}
		}
	}

}

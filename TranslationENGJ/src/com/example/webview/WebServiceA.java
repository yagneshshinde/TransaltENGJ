package com.example.webview;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceA {
	// Namespace of the Webservice - can be found in WSDL
	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - WSDL File location
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx";//"http://192.168.0.126/mysamaj/WebSOB.asmx";
	// private static String URL =

	// SOAP Action URI again Namespace + Web method name
	private static String SOAP_ACTION = "http://tempuri.org/";

	// private static String WEB_METHOD= "AuthenticateUser";

	public static String invokeAddressWS(String convertText, String WebMethod) {
		// boolean loginStatus = false;

		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WebMethod);
		// Property which holds input parameters
		PropertyInfo unamePI = new PropertyInfo();

		// Set Username
		unamePI.setName("convertText");
		// Set Value
		unamePI.setValue(convertText);
		// Set dataType
		unamePI.setType(String.class);

		// Add the property to request object
		request.addProperty(unamePI);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		// Create HTTP call object
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION + WebMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			// SoapPrimitive response = (SoapPrimitive) ((SoapObject)
			// envelope.bodyIn)
			// .getProperty(0);

			loginStatus = response.toString();

		} catch (Exception e) {
			// Assign Error Status true in static variable 'errored'
			loginStatus = e.toString();
			e.printStackTrace();

		}
		// Return booleam to calling object
		return loginStatus;
	}

	public static String invokeAddressUpdateWS(String intAddressId,
			String address1, String address2, String address3, String pinCode,
			String VillageID, String WebMethod) {
		// boolean loginStatus = false;

		String updateID = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WebMethod);
		// Property which holds input parameters
		PropertyInfo addressIdPI = new PropertyInfo();
		PropertyInfo address1PI = new PropertyInfo();
		PropertyInfo address2PI = new PropertyInfo();
		PropertyInfo address3PI = new PropertyInfo();
		PropertyInfo pincodePI = new PropertyInfo();
		PropertyInfo villageIdPI = new PropertyInfo();

		addressIdPI.setName("addressID");
		addressIdPI.setValue(intAddressId);
		addressIdPI.setType(String.class);
		request.addProperty(addressIdPI);

		address1PI.setName("address1");
		address1PI.setValue(address1);
		address1PI.setType(String.class);
		request.addProperty(address1PI);

		address2PI.setName("address2");
		address2PI.setValue(address2);
		address2PI.setType(String.class);
		request.addProperty(address2PI);

		address3PI.setName("address3");
		address3PI.setValue(address3);
		address3PI.setType(String.class);
		request.addProperty(address3PI);

		pincodePI.setName("pinCode");
		pincodePI.setValue(pinCode);
		pincodePI.setType(String.class);
		request.addProperty(pincodePI);

		villageIdPI.setName("VillageID");
		villageIdPI.setValue(VillageID);
		villageIdPI.setType(String.class);
		request.addProperty(villageIdPI);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		// Create HTTP call object
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION + WebMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			// SoapPrimitive response = (SoapPrimitive) ((SoapObject)
			// envelope.bodyIn)
			// .getProperty(0);

			updateID = response.toString();

		} catch (Exception e) {
			// Assign Error Status true in static variable 'errored'
			updateID = e.toString();
			e.printStackTrace();

		}
		// Return booleam to calling object
		return updateID;
	}
}

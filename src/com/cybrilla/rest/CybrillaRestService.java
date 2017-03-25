package com.cybrilla.rest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.cybrilla.entity.PaymentGatewayEntity;
import com.cybrilla.service.PaymentService;
import com.cybrilla.serviceimpl.PaymentServiceImpl;

@Path("/paymentgateway")
public class CybrillaRestService {
	
		private static Logger logger = LogManager.getLogger(CybrillaRestService.class);
		private PaymentService paymentService = new PaymentServiceImpl();
		
		@POST
		@Path("/transaction")
		@Consumes("application/json")
	    @Produces("application/json")
		public Response payment(PaymentGatewayEntity paymentGatewayEntity) {

			logger.info(" Running");
			
			String payload_to_pg = paymentService.generateMessage(paymentGatewayEntity);
			Map<String, String> map = new ConcurrentHashMap<String, String>();

			map.put("payloadtopg", payload_to_pg);
			map.put("txn_status", "success");
			
			Gson gson = new GsonBuilder().create();
			String response = gson.toJson(map);
			return Response.ok().entity(response).build();
		}
		
		@GET
		@Path("/test")
		@Produces("application/json")
		public Response test(){
			String response = "Success";
			return Response.ok().entity(response).build();
			
		}


}

package com.tesco.tps.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.tesco.tps.CostAmendApplication;
import com.tesco.tps.constant.CostAmendConstants;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.core.model.HttpRestResponse;
import com.tesco.tps.core.service.HttpRestService;

//public class TradedUnitValidateUserServiceImpl implements TradedUnitValidateUserService {

@Service
public class TradedUnitValidateUserServiceImpl {
	@Inject
	private HttpRestService httpService;
	
	static int  status;
	private static final Logger LOG = LoggerFactory.getLogger(TradedUnitValidateUserServiceImpl.class);
	
	public  int validateAccessGroup(String Access_Token,String Client_ID) {
try{	
	//LOG.info("TradedUnitValidateUserServiceImpl >> validateAccessGroup");
		String token = Access_Token;
		/*Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(CostAmendApplication.tradingPartnerConfiguration.getTPSUSERGroupURL());// read
																														// from
																														// property
																														// file
		Invocation.Builder invocationBuilder = webTarget.request(CostAmendConstants.CONTENT_TYPE);// read
																										// from
																										// the
																										// java
																										// constants
																										// file
		
		invocationBuilder.header("X-Client-Id", Client_ID);

		invocationBuilder.header("Content-Type", CostAmendConstants.CONTENT_TYPE);// read
																						// from
																						// the
																						// java
																						// constants
																						// file
		invocationBuilder.header("Authorization", token);

		Response response = invocationBuilder.get();
		

		String output=response.readEntity(String.class);*/
		
		  Map<String, String> headers = new HashMap<String, String>();
  		headers.put("X-Client-Id", CostAmendConstants.TPS_CLIENT_ID);
  		headers.put("Content-Type", CostAmendConstants.CONTENT_TYPE);
  		headers.put("Authorization", token);
  		
  		HttpRestResponse<String> resp=httpService.getEntity(CostAmendApplication.costAmendConfiguration.getTPSUSERGroupURL(), String.class, headers);
  		
  		//EntityUtils.toString(response.getEntity())
       status=resp.getStatusCode();
		
		String output=resp.getContent();
		
//		BufferedReader br=new BufferedReader(new FileReader(new File("OutputJson.txt")));
//		String line="";
//		
//		while ((line = br.readLine()) != null) {
//			output+=line;
//		}
		
		//status = response.getStatus();
		
        List<String> groupname=new ArrayList<String>();
       
        if (status == HttpServletResponse.SC_UNAUTHORIZED)
        	return status;
        else
        {
        String arr[]=output.split("\"groupName\":\"");
for(String string:arr)
{
	groupname.add(string.split("\"")[0]);
}

		if (status == HttpServletResponse.SC_OK)
		{
		for(int i=1;i<groupname.size();)
		{
			if (((CostAmendApplication.costAmendConfiguration.getGROUP_NAME().equalsIgnoreCase(groupname.get(i++)))
					&& (CostAmendApplication.costAmendConfiguration.getPARENT_GROUP_NAME().equalsIgnoreCase(groupname.get(i++))) 	))
			
			return status;
		}
		return HttpServletResponse.SC_UNAUTHORIZED;
		}
		 
			return HttpServletResponse.SC_UNAUTHORIZED;

	
	}
        
}
catch(Exception e){ 
	LOG.error("TradedUnitValidateUserServiceImpl >> validateAccessGroup Exception: "+e.getStackTrace()[0]);
    return status;
    }
//return status;
	}
	

}

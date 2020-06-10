package demo;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import java.io.IOException;

public class JoltRunner {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		
		String inputStr = "{\"items\": {\"item\": [{\"id\": \"0001\",\"type\": \"donut\"	},	{\"id\": \"1001\",\"type\": \"Regular\"	},	{\"id\": \"1002\",\"type\": \"Chocolate\"	},	{\"id\": \"1003\",\"type\": \"Blueberry\"	},	{\"id\": \"5001\",\"type\": \"None\"	},	{\"id\": \"5002\",\"type\": \"Glazed\"	},	{\"id\": \"5005\",\"type\": \"Sugar\"	},	{\"id\": \"5007\",\"type\": \"Powdered Sugar\"	},	{\"id\": \"5006\",\"type\": \"Chocolate with Sprinkles\"	},	{\"id\": \"5003\",\"type\": \"Chocolate\"	},	{\"id\": \"5004\",\"type\": \"Maple\"	}]	}} ";
		String spec = "[ {   \"operation\": \"shift\",   \"spec\": { \"items\": { \"item\": {   \"*\": { \"@(0,type)\": \"@(0,id)\"   } } }   } }] ";
		
		Chainr chainr = Chainr.fromSpec(JsonUtils.jsonToObject(spec));
		Object inputJSON = JsonUtils.jsonToObject(inputStr);
		Object transformedOutput = null;
		
		for (int i = 0; i < 1000; i++) {
			transformedOutput = chainr.transform(inputJSON);
		}
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F; 
		System.out.println(sec + " seconds"); 
		System.out.println("JOLT Demo");
		System.out.println(JsonUtils.toJsonString(transformedOutput));
	}
}
package org.apache.jmeter.protocol.java.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.flazr.rtmp.client.RtmpClient;

public class PushRequestTest extends AbstractJavaSamplerClient {
	
    private static String label = "RtmpPushSampler";

    /**
     * before runTest(), do the initialization
     */
    public void setupTest(JavaSamplerContext arg0) {
    }

    /**
     * test sample body
     */
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        sr.setSampleLabel(label);
        
        try { 
            Iterator<String> is = arg0.getParameterNamesIterator();
            Map<String, String> map = new HashMap<String, String>();
            while(is.hasNext()) {
            	String key = is.next();
            	map.put(key, arg0.getParameter(key));
            }
            sr.setRequestHeaders(map.toString());
            sr.sampleStart(); 
            pushLive(map.get("ip"), map.get("port"), 
            		map.get("app"), map.get("stream"),
            		map.get("video"));
            sr.sampleEnd();
            sr.setResponseData(map.toString(), null);
            sr.setDataType(SampleResult.TEXT);
            sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSamplerData(e.getMessage());
            e.printStackTrace();
            sr.setSuccessful(false); 
        } 
        return sr;
    }

    /**
     * default parameter setting
     */
    public Arguments getDefaultParameters() {

        Arguments args = new Arguments();
        args.addArgument("ip", "localhost");
        args.addArgument("port", "1935");
        args.addArgument("app", "live");
        args.addArgument("stream", "stream0");
        args.addArgument("video", "flv.flv");
        return args;
    }

    /**
     * after runTest(), do the teardown.
     */
    public void teardownTest(JavaSamplerContext arg0) {
    }
	
	public static void pushLive(String ip, String port, String app, 
			String stream, String video) {
		String[] args = {"-live", "-host", ip, "-port", port,
 	            "-app", app, stream, video};
		RtmpClient.main(args);
	}
	
	public static void demo() {
		Arguments params = new Arguments();  
		params.addArgument("ip", "10.120.10.80");
		params.addArgument("port", "1935");
		params.addArgument("app", "live");
		params.addArgument("stream", "stream0");
		params.addArgument("video", "flv.flv");
		JavaSamplerContext arg0 = new JavaSamplerContext(params);  
		PushRequestTest test = new PushRequestTest();  
		test.setupTest(arg0);  
		test.runTest(arg0);  
		test.teardownTest(arg0); 
	}
	
	public static void main(String[] args) {
		//pushLive("10.120.10.80", "1935", "live", "stream0", "flv.flv");
		demo();
	}

}

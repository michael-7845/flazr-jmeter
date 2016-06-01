package com.flazr.rtmp.client;

import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.flazr.rtmp.client.RtmpClient;

public class PullRequest extends AbstractJavaSamplerClient{
	
    private static String label = "RtmpPullSampling";

    /**
     * 执行runTest()方法前会调用此方法,可放一些初始化代码
     */
    public void setupTest(JavaSamplerContext arg0) {
    }

    /**
     * JMeter测试用例入口
     */
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        sr.setSampleLabel(label);
        try { // 这里调用我们要测试的java类，这里我调用的是一个Test类
            Map<String,String> map = getDefaultParameters().getArgumentsAsMap();
            sr.sampleStart(); // 记录程序执行时间，以及执行结果
            pullLive(map.get("ip"), map.get("port"), map.get("app"),
            		map.get("stream"), map.get("version"), 
            		map.get("load"), map.get("threads"));
            sr.sampleEnd();
            sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSamplerData(e.getMessage());
            e.printStackTrace();
            sr.setSuccessful(false); // 用于设置运行结果的成功或失败，如果是"false"则表示结果失败，否则则表示成功
        } 
        return sr;
    }

    /**
     * JMeter界面中可手工输入参数,代码里面通过此方法获取
     */
    public Arguments getDefaultParameters() {

        Arguments args = new Arguments();
        args.addArgument("ip", "localhost");
        args.addArgument("port", "1935");
        args.addArgument("app", "live");
        args.addArgument("stream", "stream0");
        args.addArgument("version", "00000000");
        args.addArgument("load", "1");
        args.addArgument("threads", "1");
        return args;
    }

    /**
     * 执行runTest()方法后会调用此方法.
     */
    public void teardownTest(JavaSamplerContext arg0) {
    }
	
	protected static void pullLive(String ip, String port, String app, 
			String stream, String version, String load, String threads) { 
		String[] args = {"-version", version, "-load", load, 
				"-threads", threads, "-host", ip, 
				"-port", port, "-app", app, stream};
		RtmpClient.main(args); 
	}
	
	public static void main(String[] args) {
		;
	}

}

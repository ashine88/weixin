package com.tdhz.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {
private static ExecutorService pool = Executors.newFixedThreadPool(300);
	
	public static void execute(Runnable command){
		pool.execute(command);
	}
}

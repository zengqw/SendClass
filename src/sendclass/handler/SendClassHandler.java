package sendclass.handler;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import sendclass.Activator;
import sendclass.runtime.StreamGobbler;

public class SendClassHandler extends AbstractHandler {
	private IWorkbenchWindow window;
	  public static final int SUCCESS = 0;            // 表示程序执行成功

	    public static final String SUCCESS_MESSAGE = "程序执行成功！";

	    public static final String ERROR_MESSAGE = "程序执行出错：";
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
//		rt.exec("cmd /c start " + "file:///" + StringUtils.handleSpace("D:\\putty"));
//		rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
		
		 FileOutputStream fos = new FileOutputStream("C:\\hello.txt"); 
		
//		   Process proc = rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
		   Process proc = rt.exec("D:/putty/PSCP.EXE  -P 32200 -pw a9701182 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
//		   int exitCode = proc.waitFor();
		   readProcessOutput(proc);
		   
		// 等待程序执行结束并输出状态

//	        if (exitCode == SUCCESS) {
//	            System.out.println(SUCCESS_MESSAGE);
//	        } else {
//	            System.err.println(ERROR_MESSAGE + exitCode);
//	        }
//		   
//           // any error message?  
//           StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");  
//           // any output?  
//           StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT", fos);  
//           errorGobbler.start();  
//           outputGobbler.start();  
//           int exitVal = proc.waitFor();  
//           System.out.println("ExitValue: " + exitVal);  
//           fos.flush();  
//           fos.close(); 
//		rt.exec(cmdarray, envp, dir)
	}

    /**
     * 打印进程输出
     *
     * @param process 进程
     */
    private static void readProcessOutput(final Process process) {
//    	process.getOutputStream();
        // 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印
//        read(process.getInputStream(), System.out);
        read(process.getErrorStream(), System.err);
    }

//    // 读取输出流
//    private static void read(OutputStream outputStream, PrintStream out) {
//    	try {
//    		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//    		
//    		String line;
//    		while ((line = reader.readLine()) != null) {
//    			out.println(line);
//    		}
//    		
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	} finally {
//    		
//    		try {
//    			inputStream.close();
//    		} catch (IOException e) {
//    			e.printStackTrace();
//    		}
//    	}
//    }
    
    // 读取输入流
    private static void read(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		try{
			
//			showMsg("哈哈");
			Runtime rt = Runtime.getRuntime();
//			rt.exec("cmd /c start " + "file:///" + StringUtils.handleSpace("D:\\putty"));
			rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt1  zqwn3535@192.168.40.125:");
			rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
			rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
			rt.exec("cmd /c start " + "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:");
			
		}catch (Exception e) {
			e.printStackTrace();
			showMsg(e.getMessage());
		}
		return null;
	}

	private void checkParam(){
		
		
	}
	
	private void showMsg(String msg) {
		MessageDialog.openInformation(window.getShell(), Activator.PLUGIN_ID, msg);
	}
}

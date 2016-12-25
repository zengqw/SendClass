package sendclass.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Command {  
    public static void exeCmd(String commandStr) {  
        BufferedReader br = null;  
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
//            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            br = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
//        String commandStr = "ping www.taobao.com";  
        String commandStr = "D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txsdft  zqwn3535@192.168.40.125:";  
        Command.exeCmd(commandStr);
    	
//        LocalCommandExecutorService service = new LocalCommandExecutorServiceImpl();
//        ExecuteResult result = service.executeCommand("D:/putty/PSCP.EXE  -P 32200 -pw a970118 -i D:/putty/putty2.ppk D:/putty/abchahaha.txt  zqwn3535@192.168.40.125:", 5000);
//        System.out.println("退出码："+result.getExitCode());
//        System.out.println("输出内容："+result.getExecuteOut());    
    	
    }  
}  
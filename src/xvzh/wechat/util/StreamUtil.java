package xvzh.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {
	public static String Inputstr2Str_byteArr(InputStream in, String encode)  
	   {  
	       StringBuffer sb = new StringBuffer();  
	       byte[] b = new byte[1024];  
	       int len = 0;  
	       try  
	       {  
	           if (encode == null || encode.equals(""))  
	           {  
	               // 默认以utf-8形式  
	               encode = "utf-8";  
	           }  
	           while ((len = in.read(b)) != -1)  
	           {  
	               sb.append(new String(b, 0, len, encode));  
	           }  
	           return sb.toString();  
	       }  
	       catch (IOException e)  
	       {  
	           e.printStackTrace();  
	       }  
	       return "";  
	         
	   }  
	
	public static byte[] File2byte(InputStream is)  
    {  
        byte[] buffer = null;  
        try  
        {  
  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = is.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            is.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
}


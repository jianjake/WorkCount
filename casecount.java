package text1;
import java.io.*;
public class casecount {
    static  public void count(String action,String thefile){
    	 //�����ַ���
    	 if(action.equalsIgnoreCase("-c"))
    		 charcount(thefile);
    	 else if(action.equals("-w"))
    		 wordcount(thefile);
    	 else if(action.equals("-l"))
    		 linecount(thefile);
     }
    static public void count(String action1,String action2,String thefile){
    	 count(action1,thefile);
    	 count(action2,thefile);
    }
    static public void count(String action1,String action2,String action3,String thefile){
    	if(thefile.endsWith(".c")){
   	   count(action1,thefile);
   	   count(action2,thefile);
   	   count(action3,thefile);
   	   }
    	else{
    		print(action1,action2,thefile);
    	}
   }
    
    
    static public void count(String action1,String action2,String sourcefile,String action3,String thefile){
    	count(action1,sourcefile,action3,thefile);
    	count(action2,sourcefile,action3,thefile);
    }
    static public void count(String action1,String action2,String action3,String sourcefile,String action4,String thefile){
    		count(action1,sourcefile,action3,thefile);
    		count(action2,sourcefile,action3,thefile);
    		count(action3,sourcefile,action3,thefile);
    }
    public static void charcount(String thefile){
 		int charcount=0;
 		String sfile=new String(thefile);
 		File file=new File(sfile);
 		if(file.exists()){
 			try{
 				FileInputStream fis=new FileInputStream(file);
 				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
 				BufferedReader br=new BufferedReader(isr);
 				String line=new String("");
 				StringBuffer sb=new StringBuffer();
 				while((line=br.readLine())!=null){
 					sb.append(line);
 					charcount+=line.length();
 				}
 				br.close();
 				isr.close();
 				fis.close();
 			}
 			catch(FileNotFoundException e){
 				e.printStackTrace();
 			}
 			catch(UnsupportedEncodingException e){
 				e.printStackTrace();
 			}
 			catch(IOException e){
 				e.printStackTrace();
 			}
 		}
 		try{
 			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter("result.txt", true);
            char[] message=("file.c, �ַ�����"+charcount+"\r\n").toCharArray();//����"\r\n"����"\n"
            writer.write(message);
 			writer.close();
 		}
 		catch(IOException e){
 			System.out.println("File read/write Error"+e);
 		}
     }
    public static void wordcount(String thefile){
		int wordcount=0;
		String sfile=new String(thefile);
		File file=new File(sfile);
		if(file.exists()){
			try{
				FileInputStream fis=new FileInputStream(file);
				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
				BufferedReader br=new BufferedReader(isr);
				String line=new String("");
				StringBuffer sb=new StringBuffer();
				while((line=br.readLine())!=null)
				    sb.append(line);
				wordcount=sb.toString().split("\\s+").length;
				br.close();
				isr.close();
				fis.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter("result.txt", true);
            char[] message=("file.c, ��������"+wordcount+"\r\n").toCharArray();//����"\r\n"����"\n"
            writer.write(message);
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
     }
    public static void linecount(String thefile){
    	int linecount=0;
		String sfile=new String(thefile);
		File file=new File(sfile);
		if(file.exists()){
			try{
				FileInputStream fis=new FileInputStream(file);
				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
				BufferedReader br=new BufferedReader(isr);
				String line=new String("");
				StringBuffer sb=new StringBuffer();
				while((line=br.readLine())!=null){
					linecount++;
					sb.append(line);
				}
				br.close();
				isr.close();
				fis.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter("result.txt", true);
            char[] message=("file.c,   ������"+linecount+"\r\n").toCharArray();//����"\r\n"����"\n"
            writer.write(message);
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
    }
    public static void print(String action1,String sourcefile,String thefile){
    	int linecount=0;
    	int charcount=0;
    	int wordcount=0;
		String sfile=new String(sourcefile);
		File file=new File(sfile);
		if(file.exists()){
			try{
				FileInputStream fis=new FileInputStream(file);
				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
				BufferedReader br=new BufferedReader(isr);
				String line=new String("");
				StringBuffer sb=new StringBuffer();
				while((line=br.readLine())!=null){
					linecount++;
					sb.append(line);
					charcount+=line.length();
				}
				wordcount=sb.toString().split("\\s+").length;
				br.close();
				isr.close();
				fis.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter(thefile, true);
            char[] message=null;
            if(action1.equals("-l"))
                  message=("file.c,   ������"+linecount+"\r\n").toCharArray();//����"\r\n"����"\n"
            else if(action1.equals("-c"))
            	 message=("file.c,   �ַ�����"+charcount+"\r\n").toCharArray();//����"\r\n"����"\n"
            else if(action1.equals("-w"))
            	 message=("file.c,   ��������"+wordcount+"\r\n").toCharArray();//����"\r\n"����"\n"
            writer.write(message);
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
    }
}


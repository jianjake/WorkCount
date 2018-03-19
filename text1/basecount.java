package text1;
//基本功能
import java.io.*;
import java.util.Set;
import java.util.TreeMap;
public class basecount {
	//输入两个参数
    static  public void count(String action,String thefile){
    	print(action,thefile,null);
     }
  //输入三个参数 
    static public void count(String action1,String action2,String thefile){
    	if(thefile.endsWith(".c")){//两个
    	 count(action1,thefile);
    	 count(action2,thefile);}
    	else//参数三是输出文件名
    	{print(action1,action2,thefile);
    	}
    }
    //输入四个参数
    static public void count(String action1,String action2,String action3,String thefile){
    	if(action3.equals("-o")){
    		 print(action1,action2,thefile);
   	   }
    	else{
    		   count(action1,thefile);
    	   	   count(action2,thefile);
    	   	   count(action3,thefile);
    		
    	}
   }
    ////输入五个参数
    static public void count(String action1,String action2,String sourcefile,String action3,String thefile){
    	count(action1,sourcefile,action3,thefile);
    	count(action2,sourcefile,action3,thefile);
    }
   //输入六个参数
    static public void count(String action1,String action2,String action3,String sourcefile,String action4,String thefile){
    		count(action1,sourcefile,action4,thefile);
    		count(action2,sourcefile,action4,thefile);
    		count(action3,sourcefile,action4,thefile);
    }
    //计算字符个数
    public static void charcount(String thefile,String output){
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
 			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
 			if(output==null)
 				output="result.txt";
            FileWriter writer = new FileWriter(output, true);
            char[] message=(thefile+", 字符数："+charcount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            writer.write(message);
 			writer.close();
 		}
 		catch(IOException e){
 			System.out.println("File read/write Error"+e);
 		}
     }
    //计算单词个数
    public static void wordcount(String thefile,String output){
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
				TreeMap<String, Integer> map = new TreeMap<>(); 
				while((line=br.readLine())!=null){
 			        String[] split = line.split("\\s++|\\.|,|\\;|\\(|\\)|\\[|\\]|\\<|\\>|\\=|\\-|\\+|\\*|\\/|\\{|\\}");  
 			        for (int i = 0; i < split.length; i++) { 
// 			          获取到每一个单词  
 			            Integer integer = map.get(split[i]);  
// 			          如果这个单词在map中没有，赋值1  
 			            if(null==integer){  
 			                map.put(split[i], 1);  
 			            }else{  
// 			              如果有，在原来的个数上加上一  
 			                map.put(split[i], ++integer);  
 			            }  
 			        }  
 				}
// 		      遍历，根据key获取所对应的value  
 		        Set<String> keySet = map.keySet();  
 		        for (String string : keySet)
 		        	if(!(string.equals("")))
 		        	wordcount+=map.get(string);
 	
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
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			if(output==null)
				output="result.txt";
            FileWriter writer = new FileWriter(output, true);
            char[] message=(thefile+", 单词数："+wordcount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            writer.write(message);
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
     }
    //计算行数
    public static void linecount(String thefile,String output){
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
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			if(output==null)
				output="result.txt";
            FileWriter writer = new FileWriter(output, true);
            char[] message=(thefile+",   行数："+linecount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            writer.write(message);
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
    }
    //输出到指定文件名文件
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
				 TreeMap<String, Integer> map = new TreeMap<>(); 
				while((line=br.readLine())!=null)
				{
					linecount++;
					sb.append(line);
					charcount+=line.length();

 			        String[] split = line.split("\\s++|\\.|,|\\;|\\(|\\)|\\[|\\]|\\<|\\>|\\=|\\-|\\+|\\*|\\/|\\{|\\}");  
 			        for (int i = 0; i < split.length; i++) { 
// 			          获取到每一个单词  
 			            Integer integer = map.get(split[i]);  
// 			          如果这个单词在map中没有，赋值1  
 			            if(null==integer){  
 			                map.put(split[i], 1);  
 			            }else{  
// 			              如果有，在原来的个数上加上一  
 			                map.put(split[i], ++integer);  
 			            }  
 			        }  
 				}
// 		      遍历，根据key获取所对应的value  
 		        Set<String> keySet = map.keySet();  
 		        for (String string : keySet)
 		        	if(!(string.equals("")))
 		        	wordcount+=map.get(string);
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
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			if(thefile==null)
				thefile="result.txt";
			FileWriter writer = new FileWriter(thefile, true);
            char[] message=null;
            if(action1.equals("-l"))
                  message=(sourcefile+",   行数："+linecount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            else if(action1.equals("-c"))
            	 message=(sourcefile+",   字符数："+charcount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            else if(action1.equals("-w"))
            	 message=(sourcefile+",   单词数："+wordcount+"\r\n").toCharArray();//换行"\r\n"不是"\n"
            if(message!=null)  writer.write(message);
            else System.out.println("无该操作");
 			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
    }
}


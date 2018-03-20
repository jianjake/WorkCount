package wordcount;
//基本功能
import java.io.*;
import java.util.Set;
import java.util.TreeMap;
public class basecount {
	//字符数、单词数和行数
    public static String print(String action1,String sourcefile){
    	int linecount=1;
    	int charcount=0;
    	int wordcount=0;
		File file=new File(sourcefile);
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
            String message=null;
            if(action1.equals("-l"))
                  message=(sourcefile+",   行数："+linecount+"\r\n");//换行"\r\n"不是"\n"
            else if(action1.equals("-c"))
            	 message=(sourcefile+",   字符数："+charcount+"\r\n");//换行"\r\n"不是"\n"
            else if(action1.equals("-w"))
            	 message=(sourcefile+",   单词数："+wordcount+"\r\n");//换行"\r\n"不是"\n"
           return message;
    }
    
   static void putAtoB(String message,String thefile){//将message输出到thefile中
	   try{
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			if(thefile==null)
				thefile="result.txt";
			FileWriter writer = new FileWriter(thefile, true);
           if(message.toCharArray()!=null)  writer.write(message.toCharArray());
           else System.out.println("无该操作");
			writer.close();
		}
		catch(IOException e){
			System.out.println("File read/write Error"+e);
		}
   }	
}

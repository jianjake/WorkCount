# word-<br>
java编写一个统计字符数、单词数和行数的小程序<br>
1  WordCount需求说明<br>
WordCount的需求可以概括为：对程序设计语言源文件统计字符数、单词数、行数，统计结果以指定格式输出到默认文件中，<br>
以及其他扩展功能，并能够快速地处理多个文件。<br>
<br>
可执行程序命名为：wc.exe，该程序处理用户需求的模式为：<br>
wc.exe [parameter] [input_file_name]<br>
存储统计结果的文件默认为result.txt，放在与wc.exe相同的目录下。<br>
<br>
<br>
1.1 基本功能<br>
wc.exe -c file.c     //返回文件 file.c 的字符数<br>
wc.exe -w file.c     //返回文件 file.c 的单词总数<br>
wc.exe -l file.c     //返回文件 file.c 的总行数<br>
wc.exe -o outputFile.txt     //将结果输出到指定文件outputFile.txt<br>
注意：<br>
空格，水平制表符，换行符，均算字符。<br>
由空格或逗号分割开的都视为单词，且不做单词的有效性校验，例如：thi#,that视为用逗号隔开的2个单词。<br>
-c, -w, -l参数可以共用同一个输入文件，形如：wc.exe –w –c file.c 。<br>
-o 必须与文件名同时使用，且输出文件必须紧跟在-o参数后面，不允许单独使用-o参数。 <br>
<br>
<br>
<br>
1.2 扩展功能<br>
<br>
wc.exe -s            //递归处理目录下符合条件的文件<br>
wc.exe -a file.c     //返回更复杂的数据（代码行 / 空行 / 注释行）<br>
wc.exe -e stopList.txt  // 停用词表，统计文件单词总数时，不统计该表中的单词<br>
[file_name]: 文件或目录名，可以处理一般通配符。<br>
其中，<br>
代码行：本行包括多于一个字符的代码。<br>
空   行：本行全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符，例如“{”。<br>
注释行：本行不是代码行，并且本行包括注释。一个有趣的例子是有些程序员会在单字符后面加注释：<br>
}//注释<br><br>
在这种情况下，这一行属于注释行。<br>
-e 必须与停用词文件名同时使用，且停用词文件必须紧跟在-e参数后面，不允许单独使用-e参数。<br>
stopList.txt中停用词可以多于1个，单词之间以空格分割，不区分大小写，形如：<br>
while if switch<br>
则while，if，switch作为3个停用词，在单词统计的时候不予考虑。停用词表仅对单词统计产生影响，不影响字符和行数的统计。<br>
<br>
<br>
1.3举例<br>
wc.exe -s -a *.c<br>
返回当前目录及子目录中所有.c 文件的代码行数、空行数、注释行数。<br>
<br>
wc.exe -s -a –c -w *.c–e stop.txt –o output.txt<br>
返回当前目录及子目录中所有.c 文件的字符数、单词总数、代码行数、空行数、注释行数，并将结果保存在output.txt中，且统计单词时忽略stop.txt中的单词。<br>
<br>
1.4 输出结果的格式<br>
输出结果格式形如：<br>
[文件名], [统计标志]: [总数]<br>
举例如下：<br>
wc.exe –c file.c，则输出结果保存在result.txt中<br>
内容格式（注意大小写）：file.c, 字符数: 50<br>
<br>
wc.exe –w file.c，则输出结果保存在result.txt中<br>
内容格式（注意大小写）：file.c, 单词数: 30<br>
<br>
wc.exe –l file.c，则输出结果保存在result.txt中<br>
内容格式（注意大小写）：file.c, 行数: 10<br>
<br>
 如果同时涉及多项统计，如同时需要统计字符、单词和行数，则按照字符-->单词-->行数-->代码行数/空行数/注释行的顺序，依次分行显示。
 显示顺序与输入参数的次序无关。例如：<br>
wc.exe –l –c file.c，则统计file.c中的字符数和行数，输出结果保存在result.txt中<br>
内容格式（注意大小写）：<br>
file.c, 字符数: 50<br>
file.c, 行数: 10<br>
<br>
例如：<br>
wc.exe -s -a –c -w *.c–e stop.txt –o –output.txt<br>
输出结果保存在output.txt中，内容格式为：<br>
file1.c, 字符数: 50<br>
file1.c, 单词数: 30<br>
file1.c, 代码行/空行/注释行: 5/2/3<br>
file2.c, 字符数: 40<br>
file2.c, 单词数: 26<br>
file2.c, 代码行/空行/注释行: 6/2/1<br>
<br>

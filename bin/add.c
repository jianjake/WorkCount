#include<stdio.h>
#include<time.h>
int main()
{
	clock_t start_time,end_time;
	start_time=clock();
	
	int a[100000];
//	scanf("%d",&n);
//	for(a=0,sum=0;a<=n;a++)
//	sum=sum+a;
  // sum=n*(n+1)/2;
//	printf("sum=%d",sum);
/*
jskdl
gushdjh
//hauhdsghjsdais
/*hjkashjdk*/

for(int i=0;i<100000;i++)
{/*hajhsdkjvfknc,mzjkx
hidfjskfdmb
aoidkfl*/
	a[i]=i;
	if(i%7==0)printf("%d\t",a[i]);
}/*jksadjkjkllklxkcl*/

	end_time=clock();
	printf("\n Execution_time was %lu seconds\n",(long)((end_time-start_time)/CLOCKS_PER_SEC));
	return 0;
 }


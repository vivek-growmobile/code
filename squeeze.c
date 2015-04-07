#include <stdio.h>

void squeeze(char[],char[]);

main(){
	char s1[] = "cat";
	char s2[] = "cog";
	squeeze(s1,s2);
	printf("%s\n",s1);
	return 0;
}

void squeeze(char s1[],char s2[]){
	int i,j;
	for (i = 0; s1[i] != '\0'; i++){
		for (j = 0; s2[j] != '\0'; j++){
			if(s1[i] == s2[j]){
				s1[i] = s1[i+1];
			}
		}
	}
}
					

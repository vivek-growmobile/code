#include <stdio.h>

/* print a farenheit celcius table */

/* Lower Limit of Table */
#define LOWER 0

/* Upper Limit of Table */
#define UPPER 300

/* Step Size */
#define STEP 20

main(){

int fahr;

printf("\nFarenheit Celcius Table\n\n");

for (fahr = LOWER; fahr <= UPPER; fahr = fahr + STEP){
	printf("%3d %6.1f\n", fahr, (5.0/9.0)*(fahr - 32));
}

}

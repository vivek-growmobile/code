int main (int argc, char*[] argv){

}


void level1 (char* arg1){
	char* arg2 = "A clear conscience is usually the sign of a bad memory.";
	if (strcmp(arg1,arg2) == 0){
		return;
	}
	explode_bomb();
}

void level2 (char* arg1){
	int local1;
	int local2;
	if (sscanf(arg1,"%d %d", &local1, &local2) > 1){
		if (local1 - local2 == 9926){
			if (local1 & local2 != 0){
				if (local1 ^ local2 <= 0){
					return;
				}
				else explode_bomb();

			}
			else explode_bomb();

		}
		else explode_bomb();
	}
	else explode_bomb();
}

void read_five_numbers(char* arg1, int* arg2){
	if (sscanf(arg1,"%d %d %d %d %d", arg2, arg2 + 1, arg2 + 2, arg2 + 3, arg2 + 4) == 5){
		return;
	}
	else explode_bomb();
}

void level3 (char* arg1){
	int nums[5];
	int account = 0;
	char* control = arg1;
	read_five_numbers(control, &nums[0]);

	if (nums[0] == 21){

		for (int i = 0; i <= 3; i++){
			if (vals[i] <= vals[i+1]) explode_bomb();

			else count += vals[i] * i; 	
		}

		if (-nums[4] == account) return;

		else explode_bomb();
	}

	else explode_bomb();
}

int transform(int arg1){
	int val1 = 7;
	int val2 = val1 * 2;
	int c = 0x5555556;
	switch (arg1) {
		case 115 : case 125 : val2 = 96; val1 *= val1 * 100; break;
		case 116 : explode_bomb(); break;
		case 117 : val1 = (val1 + arg1) - val2; break; 
		case 118 : val1 *= 80; 
			val1 /= 2;
			val2 = 93;
			break;  
		case 119 : val1 = val1 - val2; break; 
		case 120 : val1 /= 2; val2 = 93; break;
		case 122 : explode_bomb(); break;
		case 123 : val2 = 93; break;
		case 124 : val1 = val1 - 107 + arg1 - val2; break;
		default : if (arg1 < 0) val1 += 0x1111110 + val2;
			else val1 += val2;
			val1 += val2;
			break;
	}
	return val1 - val2;
}

void level4 (char* arg1){
	int local;
	if (sscanf(arg1,"%d", &local) == 1){
		local = transform(local);
		local = transform(local);
		if (local < 0) return;

		else explode_bomb();

	}
	else {
		explode_bomb();
	}
}


int func5(int arg1, int arg2, int arg3){

}

void level5 (char* arg1){
	int local;
	if (sscanf(arg1,"%d", &local) == 1){
		if (func5(local, 7237, 0) == 7) return;
		else explode_bomb();


	}

	else explode_bomb();
}

int[] read_array(char* arg1, ){}

void level6 (char* arg1){
	unsigned int local1 = (unsigned int)arg1[0];
	unsigned int length = local1 - 65;
	int* base;

	if (length > 5 && length <= 256) {
		read_array(arg1+1, base, length);
		if (base[1] == 31){
			int -xc = 0;
			int -x8 = 0;
			while (length > -x8){
				length--;
				int -x418 = length - 1 - (-x8);
				count *= 4;
				base + (1 * count)
				local2--;
			}
		}	
		else explode_bomb();	
	}
	else explode_bomb();

}
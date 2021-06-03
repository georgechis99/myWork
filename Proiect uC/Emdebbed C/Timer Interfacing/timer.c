#include<reg52.h>

void t0_m1_delay_5ms();

void main(){
	
	TMOD = 0x01; //timer 0, mode 1
	
	while(1){
	P1 = 0xFF;
	t0_m1_delay_5ms();
	P1 = 0x00;
	t0_m1_delay_5ms();
	}
}

void t0_m1_delay_5ms(){
	TH0 = 0xEE;
	TL0 = 0x00; //5ms delay
	TR0 = 1;  //start timer
	
	while(!TF0); 
	
	TR0 = 0;
	TF0 = 0;
}
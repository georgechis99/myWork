#include<reg52.h>

//RS 			P2.0
//RW 			P2.1
//E  			P2.2
//D0-D7  	P1.0-P1.7

// 11 total pins needed for interfacing the LCD

//renaming port pins that we'll use frequently
sbit RS = P2^0;
sbit RW = P2^1;
sbit E = P2^2;

void send_command(unsigned char command_value);
void send_data(unsigned char data_value);
void setup_lcd(void);
void delay_ms(unsigned int time);

void display_number(unsigned int value);
void display_digit(unsigned char value);

void main(){
	
	//2 lines and 5×7 matrix
	send_command(0x38);
	
	//Display ON, cursor blinking
	//send_command(0x0E);
	
	//Display ON, cursor OFF
	send_command(0x0C);
	
	//Clear display screen
	send_command(0x01);
	
	while(1){
	//force cursor on first line
	send_command(0x80);
	send_data('G');  //ascii value is sent
	send_data('e');
	send_data('o');
	send_data('r');
	send_data('g');
	send_data('e');
	
	//force cursor on second line
	send_command(0xC0);
	send_data('C');
	send_data('h');
	send_data('i');
	send_data('s');
		
	//JUST TESTING THIS FUNCTION
	display_number(123);
	}
}

void send_command(unsigned char command_value){
	P1 = command_value;
	RS = 0; //command mode
	RW = 0; //write mode
	
	E = 1;            //HIGH to LOW impulse must be
	delay_ms(10);     //applied in order to latch _data;
	E = 0;            //the signl must be HIGH for at least 450ns, hence the delay
}

void send_data(unsigned char data_value){
	P1 = data_value;
	RS = 1; //_data mode
	RW = 0; 
	
	E = 1;            
	delay_ms(10);     
	E = 0;           
}

void delay_ms(unsigned int time){
	unsigned int i,j;
	
	for(i=0; i<time; i++){  //time ms

		for(j=0; j<113; j++); //1ms 
	}
}

void display_digit(unsigned char value){
	unsigned char ascii = value + 48;
	send_data(ascii);
}

void display_number(unsigned int value){
	
	unsigned char num[10];
	
	int p;
	int k=0;
	while(value>0)
	{
		num[k]=value%10;
		value=value/10;
		k++;
	}
	k--;
	for (p=k;p>=0;p--)
	{
		display_digit(num[p]);
	}
}
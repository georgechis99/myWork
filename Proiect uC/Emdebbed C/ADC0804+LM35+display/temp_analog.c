#include<reg52.h>

#define lcd P2

//lcd
sbit rs = P0^5;
sbit rw = P0^6;
sbit en = P0^7;

//adc
sbit rd = P0^0;
sbit wr = P0^1;
sbit intr = P0^2;

//humidity sensor
sbit DHT11 = P3^7;
int int_rh; //integral humidity (RH) value
int dec_rh; //decimal humidity (RH) value
int int_temp; //integral temperature value
int dec_temp; //decimal temperature value
int checksum; //checksum = int_rh + dec_rh + int_temp + dec_temp (used to further validate the received _data)

//leds
sbit temp_err = P0^4;
sbit hum_err = P3^0;

//button
sbit button = P0^3;

void delay_5ms();
void delay_20ms();
void delay_50ms();
void delay_1s();
void delay(unsigned int count);

void send_command(unsigned int command_value);
void send_data(unsigned int data_value);
void lcd_initialization();
void lcd_setup();

void check_button_pressed();

void display(unsigned char *s);
void display_temperature();
void display_humidity();

unsigned char adc();
unsigned char temperature, digital_value, humidity;

void request();
void response();
unsigned int receive_data();

void read_temperature();
void read_humidity();

int i;

//MAIN FUNCTION////////////////////////////////////////////////////////////////

void main(){
	button = 0xFF;		//input button
	lcd_initialization();
	lcd_setup();
	hum_err = 0;
	
	while(1){
		check_button_pressed();
		read_temperature();
		read_humidity();
		
		DHT11 = 1; //reseting the sensor
	}
}

//HELPER FUNCTIONS/////////////////////////////////////////////////////////////

		//LCD SETUP////////////////////////////////////////////////////////////////

		void lcd_initialization(){
			send_command(0x38); //2 lines and 5×7 matrix
			send_command(0x0C); //Display ON, cursor OFF
			send_command(0x01); //Clear display screen
			send_command(0x80); //Force cursor to beginning of first line
			display("Initializing...");
			delay(5000);  //5 sec
			send_command(0x01); //clear display
		}
		
		void lcd_setup(){
			send_command(01);
			send_command(0x80); //Force cursor to beginning (1st line)
			display("Temp : ");
			
			send_command(0xC0); //Force cursor to beginning (2nd line)
			display("Hum : ");
		}
		
		//CHECK BUTTON PRESSED/////////////////////////////////////////////////////
		void check_button_pressed(){
			if(button == 0){
				send_command(0x01);
				display("Wait for 5");
				for(i=4; i>=0; i--){
					delay(1000);
					send_command(0x89);
					send_data(i + 48);
				}
				send_command(0x01);
				lcd_setup();
			} 
		}

		//TEMPERARURE READ FROM ANALOG LM35////////////////////////////////////////
		void read_temperature(){
			temperature = adc();   //read and convert temperature from sensor
			if(temperature > 32){
				temp_err = 1;
				send_command(0x8D);
				display("Hi");
			} else if(temperature < 27){
				temp_err = 1;
				send_command(0x8D);
				display("Lo");
			} else {
				temp_err = 0;
				send_command(0x8D);
				display("   ");
			}
			display_temperature();
		}
		
		//HUMIDITY READ FROM DHT11/////////////////////////////////////////////////
		void read_humidity(){
			request();
			response();
		
			int_rh = receive_data();	//store first 8 bits in int_rh
			dec_rh = receive_data();	//store next 8 bits in dec_rh
			int_temp = receive_data();	//store next 8 bits in int_temp
			dec_temp = receive_data();	//store next 8 bits in dec_temp
			checksum = receive_data();  //store last 8 bits in checksum
			
			humidity = int_rh;
			
			if(humidity > 85){
				hum_err = 1;
				send_command(0xCA);
				display("Hi");
			}else if(humidity < 70){
				hum_err = 1;
				send_command(0xCA);
				display("Lo");
			} else {
				hum_err = 0;
				send_command(0xCA);
				display("   ");
			}

			if ((int_rh + dec_rh + int_temp + dec_temp) != checksum){
				hum_err = 1;
				send_command(0xCA);
				display("Err");
			}
			display_humidity();
			delay_50ms();
	}
		
		//TEMPERATURE DISPLAY//////////////////////////////////////////////////////

		void display_temperature(){
				unsigned char hundreds = (temperature/100)+48;
				unsigned char tens = ((temperature/10)%10)+48;
				unsigned char units = (temperature%10)+48;
			
				send_command(0x87);    //send cursor to 7th character on LCD (1st line)
			
				if(hundreds <= 48){
					if(tens <= 48){
						send_data(' ');   //Example: 003 -> __3
						send_data(' ');
						send_data(units);
						send_data(0x60); // character for degrees
						send_data('C');
					}else{
						send_data(' ');  //Example: 023 -> _23
						send_data(tens);
						send_data(units);
						send_data(0x60); 
						send_data('C');
					}
				}else{
						send_data(hundreds);  //Example: 123 -> 123
						send_data(tens);
						send_data(units);
						send_data(0x60); 
						send_data('C');
				}
		}
		
		//HUMIDITY DISPLAY/////////////////////////////////////////////////////////
		void display_humidity(){
				unsigned char tens = ((humidity/10)%10)+48;
				unsigned char units = (humidity%10)+48;
			
				send_command(0xC6);    //send cursor to 6th character on LCD (2nd line)
			
				if(tens <= 48){
					send_data(' ');
					send_data(units);
					send_data('%');
				}else{
					send_data(tens);
					send_data(units);
					send_data('%');
				}
		}
	

//DELAYS///////////////////////////////////////////////////////////////////////
		
		
		//delay _using timers (used to decide if the HIGH pulse from the DHT11)
	   
void delay_30us(){ //delay _using timers (used to decide if the HIGH pulse from the DHT11 represents a logic '0' of a logic '1'
	TMOD = 0x01;	   //        logic '1' is HIGH for LONGER than 30us
	TH0 = 0xFF;		   //        logic '0' is HIGH for LESS than 30us                       
	TL0 = 0xF1;		
	TR0 = 1;		
	while(!TF0);
	TR0 = 0;		
	TF0 = 0;		
}
		
		
void delay_5ms(){  //delay _using timers (used for sending _data to LCD)
	TMOD = 0x01;
	TH0 = 0xEE;
	TL0 = 0x00;
	TR0 = 1;
	while(!TF0);
	TR0 = 0;
	TF0 = 0;
}

void delay_20ms(){  //delay _using timers (used for request sent to DHT11 -> 18 ms actually)
	TMOD = 0x01;
	TH0 = 0xB7;
	TL0 = 0xFF;
	TR0 = 1;
	while(!TF0);
	TR0 = 0;
	TF0 = 0;
}

void delay_50ms(){
	TMOD = 0x01;
	TH0 = 0x4B;
	TL0 = 0xFE;
	TR0 = 1;
	while(!TF0);
	TR0 = 0;
	TF0 = 0;
}

void delay_1s(){
	for(i=0;i<20;i++){
		delay_50ms();
	}
}

void delay(unsigned int count)  //soft delay method
{
int i,j;
for(i=0;i<count;i++)
for(j=0;j<100;j++);
}

//LCD INTERFACING//////////////////////////////////////////////////////////////

void send_command(unsigned int command_value){
	P2 = command_value;  //_data pins of the LCD are connected to port P2
	rs = 0; //command mode
	rw = 0; //write mode
	en = 1;        //HIGH to LOW pulse
	delay_5ms();   //needed for the LCD to display _data
	en = 0;
}

void send_data(unsigned int data_value){
	P2 = data_value;
	rs = 1; //_data mode
	rw = 0;
	en = 1;
	delay_5ms();
	en = 0;
}

void display(unsigned char *s){  //function used to display strings of characters (_using pointers)
	while(*s){
		send_data(*s++);
	}
}

//ADC INTERFACING//////////////////////////////////////////////////////////////

unsigned char adc(){  //ANALOG TO DIGITAL CONVERSION
	wr = 0;  //write ready
	rd = 1;  //read ready
	wr = 1;  //start conversion
	while(intr==1);  //wait for end of conversion confirmation
	rd = 0;   //send converted _data to the digital output pins
	digital_value = P1;  //ADC's digital output pins are connected to port P1 (digital_value is a global variable)
	
	return digital_value;
}

//DHT11 INTERFACING////////////////////////////////////////////////////////////

//request sent by our microcontroller to "wake-up" the DHT11 sensor
void request(){
	DHT11 = 0;		
	delay_20ms();	//the datasheet says 18ms -> 20ms just to make sure
	DHT11 = 1;		
}
//response sent back to the microcontroller from DHT11 (this signals that the sensor is ready to send _data)
void response(){
	DHT11 = 1;
	while(DHT11==1);
	while(DHT11==0); //~54us
	while(DHT11==1); //~80us
}

unsigned int receive_data(){
	unsigned int i, c = 0;	
	DHT11 = 1;
	for (i=0; i<8; i++) //for each of the 5 blocks of 8 bits
	{ 
		while(DHT11==0); //initial state
		delay_30us();  //check if '0' or '1'
		
		if(DHT11 == 1){	 //still HIGH results in logic '1'
			c = (c<<1)|(0x01);  //shift left and set LSB on 1 
		}
		else{            //LOW results in logic '0'
			c = (c<<1);         //shift left and LSB will become 0 now
		}
		while(DHT11==1);  //wait if DHT11 is still high
	}
	return c;
}
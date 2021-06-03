org 0000h
sjmp main
	
//lcd
sbit rs = P0^5
sbit rw = P0^6
sbit en = P0^7

//adc
sbit read = P0^0
sbit write = P0^1
sbit intr = P0^2

//humidity sensor
sbit DHT11 = P3^7

//leds
sbit temp_err = P0^4
sbit hum_err = P3^0

//button
sbit button = P0^3
	
temperature equ 20h ;address in RAM where we will store the temperature
	
irh equ 21h ;integral humidity
drh equ 22h ;decimal humidity
itmp equ 23h ;integral temperature
dtmp equ 24h ;decimal temperature
checksum equ 25h ;checksum of the four 8-bit packs (irh + drh+ itemp + dtemp)

humidity equ 26h ;address in RAM where we will store the humidity

org 30h
	main:
		ACALL lcd_initialization
		acall lcd_setup
		
		infinite_loop:
		acall check_button_pressed
		acall read_temperature
		acall read_humidity
		
		setb DHT11
		
		sjmp infinite_loop
	

delay_30us:
	MOV TMOD, #01h   
	MOV TH0, #0FFh		                        
	MOV TL0, #0F1h		
	SETB TR0		
	here1:
		JNB TF0, here1
	CLR TR0
	CLR TF0
	RET	


delay_5ms:
	MOV TMOD, #01h   
	MOV TH0, #0ECh		                        
	MOV TL0, #80h		
	SETB TR0		
	here2:
		JNB TF0, here2
	CLR TR0
	CLR TF0
	RET	
	
delay_20ms:
	MOV TMOD, #01h   
	MOV TH0, #0B2h		                        
	MOV TL0, #0FFh		
	SETB TR0		
	here3:
		JNB TF0, here3
	CLR TR0
	CLR TF0
	RET	
	
;rutina pentru a trimite COMENZI la LCD
; ATENTIE! registrul A (acumulator) trebuie incarcat inainte de a 
; apela subrutina cu valoarea hexa a comenzii dorite
send_command:
	MOV P2, a
	CLR rs ;command mode
	CLR rw ;write mode
	SETB en   ;high to low pulse in order to send data
	ACALL delay_5ms
	CLR en
	RET

;rutina pentru a trimite DATE la LCD
; ATENTIE! registrul A (acumulator) trebuie incarcat inainte de a 
; apela subrutina cu valoarea hexa a datelor dorite
send_data:
	MOV P2, a
	SETB rs ;data mode
	CLR rw
	SETB en
	ACALL delay_5ms
	CLR en
	RET

lcd_initialization:
	MOV a, #38h
	ACALL send_command
	MOV a, #0Ch
	ACALL send_command
	MOV a, #01h
	ACALL send_command
	MOV a, #80h
	ACALL send_command
	
	MOV a, #'H'
	ACALL send_data
	MOV a, #'e'
	ACALL send_data
	MOV a, #'l'
	ACALL send_data
	MOV a, #'l'
	ACALL send_data
	MOV a, #'o'
	ACALL send_data
	MOV a, #'!'
	ACALL send_data
	
	MOV r0, #0FAh 
	loop1:
		ACALL delay_20ms
		DJNZ r0, loop1
	
	MOV a, #01h
	ACALL send_command
	RET

lcd_setup:
	MOV a, #01h
	ACALL send_command
	MOV a, #80h
	ACALL send_command
	
	mov a, #'T'
	acall send_data
	mov a, #'e'
	acall send_data
	mov a, #'m'
	acall send_data
	mov a, #'p'
	acall send_data
	mov a, #' '
	acall send_data
	mov a, #':'
	acall send_data
	mov a, #' '
	acall send_data
	
	MOV a, #0C0h
	ACALL send_command
	
	mov a, #'H'
	acall send_data
	mov a, #'u'
	acall send_data
	mov a, #'m'
	acall send_data
	mov a, #' '
	acall send_data
	mov a, #':'
	acall send_data
	mov a, #' '
	acall send_data
	ret

adc:
	clr write
	setb read
	setb write
	wait:
		jb intr, wait
	clr read
	mov P1, #0FFh
	mov a, P1
	mov temperature, a
	ret
	
read_temperature:
	acall adc
	acall display_temperature
	ret
	
display_temperature:
	MOV a, #87h
	ACALL send_command
	
	mov a, temperature
	mov b, #10
	div ab
	mov r0, b
	mov b, #10
	div ab
	mov r1, b
	mov b, #10
	div ab
	mov r2, b
	
	mov a, r2
	add a, #48
	acall send_data
	
	mov a, r1
	add a, #48
	acall send_data
	
	mov a, r0
	add a, #48
	acall send_data
	
	mov a, #60h
	acall send_data
	
	mov a, #'C'
	acall send_data

	ret

request:
	clr DHT11
	acall delay_20ms
	setb DHT11
	ret

response:
	setb DHT11
	wait2:
		jb DHT11, wait2
	wait3:
		jnb DHT11, wait3
	wait4:
		jb DHT11, wait4
	ret

;received 8 bits will be sored in a (accumulator)
receive_data:
	mov a, 00000000b
	setb DHT11
	
	mov r0, #08
	loop2:
		wait5:
		jnb DHT11, wait5
		
		acall delay_30us
		
		jb DHT11, logic_one
		jnb DHT11, logic_zero
		
		logic_one:
			clr c
			rlc a
			
			orl a, #00000001b
			jmp done
		
		logic_zero:
			clr c
			rlc a
			
			jmp done
		
		done:
			wait6:
				jb DHT11, wait6
			djnz r0, loop2
	ret
	
read_humidity:
	acall request
	acall response
	
	acall receive_data
	mov irh, a
	
	acall receive_data
	mov drh, a
	
	acall receive_data
	mov itmp, a
	
	acall receive_data
	mov dtmp, a
	
	acall receive_data
	mov checksum, a
	
	mov humidity, irh
	
	acall display_humidity
	
	acall delay_20ms
	acall delay_20ms
	
	ret

display_humidity:
	mov a, #0C6h
	acall send_command
	
	mov a, humidity
	mov b, #10
	div ab
	mov r0, b
	mov b, #10
	div ab
	mov r1, b
	
	mov a, r1
	add a, #48
	acall send_data
	
	mov a, r0
	add a, #48
	acall send_data
	
	mov a, #'%'
	acall send_data
	
	ret

check_button_pressed:
	jb button, done1
	
	mov a, #01h
	acall send_command
	
	mov a, #'W'
	acall send_data
	
	mov a, #'a'
	acall send_data
	
	mov a, #'i'
	acall send_data
	
	mov a, #'t'
	acall send_data
	
	mov a, #' '
	acall send_data
	
	mov a, #'f'
	acall send_data
	
	mov a, #'o'
	acall send_data
	
	mov a, #'r'
	acall send_data
	
	mov a, #' '
	acall send_data
	
	mov a, #'5'
	acall send_data
	
	mov r0, #04
	loop_display:
		mov r1, #50
		loop_delay:
			acall delay_20ms
			djnz r1, loop_delay
		
		mov a, #89h
		acall send_command
		
		mov a, r0
		add a, #48
		
		acall send_data
		
		djnz r0, loop_display
	
	mov a, #01h
	acall send_command
	
	acall lcd_setup
	
	done1:
		nop
	ret


	
end
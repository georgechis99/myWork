org 0000h

rs equ P2.0
rw equ P2.1
e equ P2.2
	
mov P1, #38h	;2 lines 5x7 matrix
acall send_command

mov P1, #0Ch	;display ON, cursor OFF	
acall send_command

mov P1, #01h	;clear display
acall send_command

here:	 mov P1, #80h	;force cursor on first line
		acall send_command

		mov P1, #'G'
		acall send_data
		
		mov P1, #'e'
		acall send_data
		
		mov P1, #'o'
		acall send_data
		
		mov P1, #'r'
		acall send_data
		
		mov P1, #'g'
		acall send_data
		
		mov P1, #'e'
		acall send_data
		
		mov P1, #0C0h	;force cursor on second line
		acall send_command
		
		mov P1, #'C'
		acall send_data
		
		mov P1, #'h'
		acall send_data
		
		mov P1, #'i'
		acall send_data
		
		mov P1, #'s'
		acall send_data
		
		sjmp here


send_command:	clr rs
				clr rw
				setb e
				acall delay
				clr e
				ret

send_data:		setb rs
				clr rw
				setb e
				acall delay
				clr e
				ret

delay:	mov r0, #10
here2:	mov r1, #255
here1:	djnz r1, here1
		djnz r0, here2
		ret
		
end
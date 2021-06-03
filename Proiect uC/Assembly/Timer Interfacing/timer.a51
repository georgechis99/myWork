org 0000h
	
mov tmod, #01h

here:	mov p1, #0ffh
		acall t0_m1_delay_5ms
		mov p1, #00h
		acall t0_m1_delay_5ms
		sjmp here

t0_m1_delay_5ms: mov th0, #0eeh
				mov tl0, #00h
				setb tr0
			here1:jnb tf0, here1
				clr tr0
				clr tf0
				ret
				
end
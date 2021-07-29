# Justintime writeup

1. open binary *justintime* with favourite debugger - we'll use *radare2/cutter* in this writeup
2.  locate the correct function that encrypts
3.  one fake function is there that throws you off before calling the correct one
4.  in *r2ghidra* decompiler we can see main function



![[Pasted image 20210504181032.png]]
5. we put breakpoint in *fcn.000011c5* before *ret* statement

![[Pasted image 20210504181247.png]]
and then we start the debugger, we need to hit breakpoint two times and we get flag on stack

![[Pasted image 20210504181614.png]]

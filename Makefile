all: libarblib.so

libarblib.so: native/arb_wrap.c native/complex_plot.c
	gcc -O3 -fPIC -shared native/complex_plot.c native/arb_wrap.c -I/usr/lib/jvm/jdk-17/include -I/usr/lib/jvm/jdk-17/include/linux -olibarblib.so -lflint-arb 

clean:
	rm -f libarblib.so *.o

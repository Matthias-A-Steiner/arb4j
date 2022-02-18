all: libarblib.so

libarblib.so: arb_wrap.c complex_plot.c
	gcc -O3 -fPIC -shared complex_plot.c arb_wrap.c -I/usr/lib/jvm/jdk-16.0.1/include -I/usr/lib/jvm/jdk-16.0.1/include/linux -olibarblib.so -lflint-arb 

clean:
	rm -f libarblib.so *.o

all: libarblib.so

SOURCES:=native/complex_plot.c native/arb_wrap.c native/calculus.c

libarblib.so: $(SOURCES)
	gcc -g -O3 -fPIC -shared $(SOURCES) -I/usr/lib/jvm/jdk-17/include -I/usr/lib/jvm/jdk-17/include/linux -olibarblib.so -lflint-arb 

clean:
	rm -f libarblib.so *.o

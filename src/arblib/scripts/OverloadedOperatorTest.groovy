package arblib.scripts

System.loadLibrary 'arblib'

println 'hello'
c = Complex.claim().set(3, 4)
d = Complex.claim().set(1, 1)
e = c + d
f = d / c
g = d % c

println c.toString() + '+'+ d.toString() + '=' + e.toString()
println d.toString() + '/'+ c.toString() + '=' + f.toString()



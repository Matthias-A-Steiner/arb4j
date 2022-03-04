package arblib.functions;

import static arblib.Constants.*;
import static java.lang.Math.*;

import arblib.*;
import junit.framework.*;

public class YFunctionTests extends
                            TestCase
{
  static
  {
    System.loadLibrary("arblib");
  }

  public static void testY0Newton()
  {
    try ( Real h = Real.claim().assign(0.1); Real a = Real.claim().assign(0.75); Real heading = Real.claim();
          Complex s = Complex.claim();
          Complex y0 = Complex.claim().set(ZFunction.roots.getOrCreate(1), Constants.ZERO.getImag()))
    {
      // YFunction.Y0Newton(y0, y0, 0, s)
    }
  }

  public static void testYangLimit()
  {
    int       prec  = 256;

    try ( Real h = Real.claim().assign(0.1); Real a = Real.claim().assign(0.75); Real heading = Real.claim();
          Complex s = Complex.claim();
          Complex y0 = Complex.claim().set(ZFunction.roots.getOrCreate(1), Constants.ZERO.getImag()))
    {
      YFunction yFunc = new YFunction(a);
      yFunc.realYangLimit(y0, s, h, heading, prec);
      System.out.println("distance=" + h);
      System.out.println("heading=" + heading);
      // assertEquals(0.74754757546711682717, vector.get(0).doubleValue(),
      // Math.pow(10, -17));
      // assertEquals(0.7475482453531754, vector.get(1).doubleValue(), Math.pow(10,
      // -17));

      ;
    }
  }

  public static void testYang()
  {

    try ( Real h = Real.claim().assign(0.1); Real a = Real.claim().assign(0.75); Real heading = Real.claim();
          Complex s = Complex.claim();
          Complex y0 = Complex.claim().set(ZFunction.roots.getOrCreate(1), Constants.ZERO.getImag()))
    {
      YFunction yFunc  = new YFunction();
      Real      vector = RealFunction.iteratedComposition((inDir, order, prec, outDir) ->
                       {
                         yFunc.realYang(y0, s, h, inDir, outDir, prec);
                       }, a, Real.newArray(10), 256);
      assertEquals(0.74754757546711682717, vector.get(0).doubleValue(), Math.pow(10, -17));
      assertEquals(0.7475482453531754, vector.get(1).doubleValue(), Math.pow(10, -17));

      System.out.println("Vector=\n" + vector);
    }
  }

  public static void testY()
  {
    double  epsilon = pow(10, -13);

    Complex res     = Complex.newArray(2);
    Complex Z       = Complex.newArray(2);
    Complex z       = Complex.newArray(1);
    z.get(0).getReal().assign(6);
    z.get(0).getImag().assign(0.4);

    // derivative.init();
    YFunction.Y(z, Z, ONE, 2, res);
    assertEquals(0.53512004345440968336, res.getReal().doubleValue(), epsilon);
    assertEquals(0.051746847309064654, res.getImag().doubleValue(), epsilon);

    System.out.format("Y(%s)=%s\n", z.get(0), res.get(0));
    Complex derivative = res.get(1);
    System.out.format("Y'(%s)=%s\n", z.get(0), derivative);

    assertEquals(0.13096935321569484169292462282811023431607253796833, derivative.getReal().doubleValue(), epsilon);
    assertEquals(-0.00099324386077740430329200769016167465202492727113029,
                 derivative.getImag().doubleValue(),
                 epsilon);
    res.clear();
  }

  public static void testY0()
  {
    double  epsilon = pow(10, -13);

    Complex res     = Complex.newArray(2);
    Complex Z       = Complex.newArray(2);
    Complex z       = new Complex().set(6, 0.4);

    /**
     * Test Y(14.13...+z)=...
     */
    YFunction.Y0(1, z, Z, 2, res, ONE);
    assertEquals(0.6998247984020233900158143, res.getReal().doubleValue(), epsilon);
    assertEquals(-0.2703731229854871081373895, res.getImag().doubleValue(), epsilon);

  }

  public static void testNFoldCompositionY0()
  {
    double  epsilon = pow(10, -13);

    Complex res     = Complex.claim2();
    Complex Z       = Complex.claim2();
    Complex z0      = Complex.claim().set(7.3, 6.4);
    int     prec    = 256;

    ComplexFunction.nFoldComposition(7, (z, w) -> YFunction.Y0Newton(z, Z, ONE, prec, res), z0, res);
    System.out.println("Y0Composed4Times=" + res);

  }
}

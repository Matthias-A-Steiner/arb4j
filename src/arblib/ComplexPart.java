package arblib;

public abstract class ComplexPart implements
                                  AutoCloseable
{

  protected ComplexFunction func;
  protected Complex         in  = new Complex();
  protected Complex         out = Complex.newVector(2);
  protected int             deriv;

  public ComplexPart(ComplexFunction func)
  {
    this.func = func;
  }

  public ComplexPart(ComplexFunction func2, int deriv)
  {
    this.deriv = deriv;
  }

  @Override
  public void close() throws Exception
  {
    in.close();
    out.close();
  }

}
package arblib;

public abstract class ComplexPart implements
                                  AutoCloseable
{

  protected ComplexFunction func;
  protected Complex         in  = new Complex();
  protected Complex         out = new Complex();

  public ComplexPart(ComplexFunction func)
  {
    this.func = func;
  }

  @Override
  public void close() throws Exception
  {
    in.delete();
    out.delete();
  }

}
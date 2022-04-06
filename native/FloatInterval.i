%typemap(javaimports) arf_interval_struct %{
%}

%typemap(javafinalize) arf_interval_struct ""
%typemap(javainterfaces) arf_interval_struct "AutoCloseable"

%typemap(javacode) arf_interval_struct %{

  static
  {
    System.loadLibrary("arblib");
  }

  public static enum RootStatus
  {
   NoRoot,
   RootLocated,
   RootUnknown
  }
  
  public RootStatus flags[];
  public int         length;
  public int         allocated;

  public void addRoot(FloatInterval root, RootStatus status)
  {
    if (length >= allocated)
    {
      int newAllocation = (allocated == 0) ? 1 : 2 * allocated;

//        *blocks = flint_realloc(*blocks, sizeof(arf_interval_struct) * new_alloc);   
//        *flags = flint_realloc(*flags, sizeof(int) * new_alloc);   
//        *alloc = new_alloc;   
//    }   
//    arf_interval_init((*blocks) + *length);   
//    arf_interval_set((*blocks) + *length, block);   
//    (*flags)[*length] = status; 
//    (*length)++; 
    }

    throw new UnsupportedOperationException("TODO: implement this then change the cPtr");
  }


  public void
         split(FloatInterval blocks, int asign, int bsign, int depth, int[] evalCount, int[] foundCount, int prec)
  {
    throw new UnsupportedOperationException("TODO");
  }
  
  public static final int BYTES = 64;

  @Override
  public String toString()
  {
    return String.format("FloatInterval[A=%s, B=%s]", getA(), getB());
  }
    
  @Override
  public void close()
  { 
      delete();
  }
  
  public FloatInterval(double left, double right)
  {
    this();
    init();
    getA().assign(left);
    getB().assign(right);
  }
  
  public FloatInterval init()
  {
   getA().init();
   getB().init();
   return this;
  }
  
  public void set(FloatInterval interval)
  {
    setA(interval.getA());
    setB(interval.getB());
  }
  
  public Real getReal(Real res, int prec)
  {
    arblib.arf_interval_get_arb(res, this, prec);
    return res;
  }
  
  public RootStatus determineRootStatus(RealFunction func, int asign, int bsign, int prec)
  {
    RootStatus result = RootStatus.RootUnknown;

    try ( Real t = Real.claim2(); Real x = getReal(Real.claim(), prec))
    {
      func.evaluate(x, 1, prec, t);
      if (t.isPositive() || t.isNegative())
      {
        result = RootStatus.NoRoot;
      }
      else
      {
        if ((asign < 0 && bsign > 0) || (asign > 0 && bsign < 0))
        {
          func.evaluate(x, 2, prec, t);
          Real firstDerivative = t.get(1);
          if (firstDerivative.isFinite() && !firstDerivative.containsZero())
          {
            result = RootStatus.RootLocated;
          }
        }
      }
    }

    return result;
  }
  
%};
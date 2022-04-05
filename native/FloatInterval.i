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
  
  public RootStatus determineStatus(int asign, int bsign, long prec)
  {
    try ( Real t = Real.claim2() ; Real x = Real.claim() )
    {
      
    }
    /**
     * <code>
        int result;
    
        arf_interval_get_arb(x, block, prec);
        func(t, x, param, 1, prec);
    
        result = BLOCK_UNKNOWN;
    
        if (arb_is_positive(t) || arb_is_negative(t))
        {
            result = BLOCK_NO_ZERO;
        }
        else
        {
            if ((asign < 0 && bsign > 0) || (asign > 0 && bsign < 0))
            {
                func(t, x, param, 2, prec);
    
                if (arb_is_finite(t + 1) && !arb_contains_zero(t + 1))
                {
                    result = BLOCK_ISOLATED_ZERO;
                }
            }
        }
    
        arb_clear(t + 0);
        arb_clear(t + 1);
        arb_clear(x);
    
        return result;
    }  
      </code>
     */
    throw new UnsupportedOperationException("TODO");

  }  
%};
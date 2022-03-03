package arblib.functions;

import static arblib.arblib.arb_init;
import static arblib.arblib.arf_init;

import arblib.Constants;
import arblib.Float;
import arblib.FloatInterval;
import arblib.Magnitude;
import arblib.Real;
import arblib.SWIGTYPE_p_void;
import arblib.arblib;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j.
 * 
 * Arb is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public interface RealFunction
{
  static final int FLINT_BITS = 64;

  public void evaluate(Real res, int order, int prec, Real z);

  public static enum BlockStatus
  {
   NoZero,
   IsolatedZero,
   Indefinite
  };

  public static class Block
  {
    FloatInterval interval = new FloatInterval();
    int           flags;
    int           length;
    int           alloc;
  }

  public static class BlockSet
  {
    Block[] blocks;

    public BlockSet(int size)
    {
      blocks = new Block[size];
    }

    public void addBlock(Block block)
    {
      Block zeroBlock = blocks[0];

      int   length    = zeroBlock.length;
      if (length >= zeroBlock.alloc)
      {
        int newAllocation = (blocks[0].alloc == 0) ? 1 : 2 * blocks[0].alloc;
        reallocate(newAllocation);
      }
      blocks[length].interval.init().set(block.interval);

//      (*flags)[*length] = status; 
//      (*length)++; 
    }

    public void reallocate(int newAllocation)
    {
      Block[]         newBlocks                 = new Block[newAllocation];
      SWIGTYPE_p_void firstBlockIntervalPointer = new SWIGTYPE_p_void(FloatInterval.getCPtr(blocks[0].interval),
                                                                      false);
      SWIGTYPE_p_void heap                      = arblib.flint_realloc(firstBlockIntervalPointer,
                                                                       FloatInterval.BYTES * newAllocation);
      FloatInterval   inner                     = new FloatInterval(SWIGTYPE_p_void.getCPtr(heap),
                                                                    false);
      blocks[0]          = new Block();
      blocks[0].interval = inner;
      blocks[0].alloc    = newAllocation;

    }

  }

  public default int calculatePartition(FloatInterval L, FloatInterval R, FloatInterval block, int prec)
  {
    try ( Real t = Real.claim(); Real m = Real.claim(); Float u = Float.claim())
    {
      int msign = 0;

      arb_init(t);
      arb_init(m);
      arf_init(u);

      /* Compute the midpoint (TODO: try other points) */
      // arf_add(u, block.getA(), block.getB(), Constants.ARF_PREC_EXACT,
      // Constants.ARF_RND_DOWN);
      // arblib.arf_mul_2exp_si(u, u, -1);

      /* Evaluate and get sign at midpoint */
      // arblib.arb_set_arf(m, u);
      evaluate(m, 1, prec, t);
      // func(t, m, param, 1, prec);
      // msign = arblib.arb_sgn_nonzero(t);

      /* L, R = block, split at midpoint */
      arblib.arf_set(L.getA(), block.getA());
      arblib.arf_set(R.getB(), block.getB());
      arblib.arf_set(L.getB(), u);
      arblib.arf_set(R.getA(), u);

      return msign;
    }
  }

  public static void isolateRootsRecursive(BlockSet blockSet, FloatInterval block, int evalCount[], int[] foundCount)
  {
//    BlockStatus status;
//
//    if (foundCount[0] <= 0 || evalCount[0] <= 0)
//    {
//      status = BlockStatus.Indefinite;
//      // ADD_BLOCK
//    }
//    else
//    {
//      // *eval_count -= 1;
//      status = check_block(func, param, block, asign, bsign, prec);
//
//      if (status != BlockStatus.NoZero)
//      {
//        if (status == BlockStatus.IsolatedZero || depth <= 0)
//        {
//          if (status == BlockStatus.IsolatedZero)
//          {
////                    if (arb_calc_verbose)
////                    {
////                        flint_printf("found isolated root in: ");
////                        arf_interval_printd(block, 15);
////                        flint_printf("\n");
////                    }
//
//            // *found_count -= 1;
//          }
//
//          // ADD_BLOCK
//        }
//        else
//        {
//          FloatInterval L, R;
//          int           msign;
//
//          // arblib.arf_interval_init(L);
//          // arf_interval_init(R);
//
//          msign = arb_calc_partition(L, R, func, param, block, prec);
//
//          if (msign == 0 && arb_calc_verbose)
//          {
//            flint_printf("possible zero at midpoint: ");
//            arf_interval_printd(block, 15);
//            flint_printf("\n");
//          }
//
//          isolate_roots_recursive(blocks,
//                                  flags,
//                                  length,
//                                  alloc,
//                                  func,
//                                  param,
//                                  L,
//                                  asign,
//                                  msign,
//                                  depth - 1,
//                                  eval_count,
//                                  found_count,
//                                  prec);
//
//          isolate_roots_recursive(blocks,
//                                  flags,
//                                  length,
//                                  alloc,
//                                  func,
//                                  param,
//                                  R,
//                                  msign,
//                                  bsign,
//                                  depth - 1,
//                                  eval_count,
//                                  found_count,
//                                  prec);
//
//          arf_interval_clear(L);
//          arf_interval_clear(R);
//        }
//      }
//    }
  }

  /**
   * static void isolate_roots_recursive(arf_interval_ptr * blocks, int ** flags,
   * slong * length, slong * alloc, arb_calc_func_t func, void * param, const
   * arf_interval_t block, int asign, int bsign, slong depth, slong * eval_count,
   * slong * found_count, slong prec) { int status;
   * 
   * if (*found_count <= 0 || *eval_count <= 0) { status = BLOCK_UNKNOWN;
   * ADD_BLOCK } else { eval_count -= 1; status = check_block(func, param, block,
   * asign, bsign, prec);
   * 
   * if (status != BLOCK_NO_ZERO) { if (status == BLOCK_ISOLATED_ZERO || depth <=
   * 0) { if (status == BLOCK_ISOLATED_ZERO) { if (arb_calc_verbose) {
   * flint_printf("found isolated root in: "); arf_interval_printd(block, 15);
   * flint_printf("\n"); }
   * 
   * found_count -= 1; }
   * 
   * ADD_BLOCK } else { arf_interval_t L, R; int msign;
   * 
   * arf_interval_init(L); arf_interval_init(R);
   * 
   * msign = arb_calc_partition(L, R, func, param, block, prec);
   * 
   * if (msign == 0 && arb_calc_verbose) { flint_printf("possible zero at
   * midpoint: "); arf_interval_printd(block, 15); flint_printf("\n"); }
   * 
   * isolate_roots_recursive(blocks, flags, length, alloc, func, param, L, asign,
   * msign, depth - 1, eval_count, found_count, prec);
   * 
   * isolate_roots_recursive(blocks, flags, length, alloc, func, param, R, msign,
   * bsign, depth - 1, eval_count, found_count, prec);
   * 
   * arf_interval_clear(L); arf_interval_clear(R); } } } }
   * 
   * @param convergenceFactor
   * @param convergenceRegion
   * @param prec
   */
  public default void calculateNewtonConvergenceFactor(Float convergenceFactor, Real convergenceRegion, int prec)
  {
    try ( Real t = Real.newArray(3))
    {
      evaluate(t, 3, prec, convergenceRegion);
      t.get(2).div(t, prec, t.get(1));
      arblib.arb_mul_2exp_si(t, t, -1);
      arblib.arb_get_abs_ubound_arf(convergenceFactor, t, prec);
    }
  }

  public default Real refineRootNewton(Real result,
                                       Real start,
                                       Real convergenceRegion,
                                       Float convergenceFactor,
                                       int evalExtraPrecision,
                                       int prec)
  {

    int precs[] = new int[FLINT_BITS];
    int i, iters, wp, padding, start_prec;

    start_prec = arblib.arb_rel_accuracy_bits(start);

    padding    = arblib.arf_abs_bound_lt_2exp_si(convergenceFactor);
    padding    = Math.min(padding, prec) + 5;
    padding    = Math.max(0, padding);

    precs[0]   = prec + padding;
    iters      = 1;
    while ((iters < FLINT_BITS) && (precs[iters - 1] + padding > 2 * start_prec))
    {
      precs[iters] = (precs[iters - 1] / 2) + padding;
      iters++;

      if (iters == FLINT_BITS)
      {
        throw new RuntimeException("imprecise input");
      }
    }

    arblib.arb_set(result, start);

    for (i = iters - 1; i >= 0; i--)
    {
      wp = precs[i] + evalExtraPrecision;

      if ((result = calculateNewtonStep(result, result, convergenceRegion, convergenceFactor, wp)) == null)
      {
        return result;
      }
    }

    return result;
  }

  /**
   * Performs a single step with an interval version of Newton’s method. The input
   * consists of the function specified by func and param, a ball x=[m-r,m+r]
   * known to contain a single root of f, a ball I (conv_region) containing with
   * an associated bound (conv_factor) for C the convergence constant and a
   * working precision prec.
   * 
   * The Newton update consists of setting x'=[m'-r',m'+r'] where m'=m-f(m)/f'(m)
   * and r'=C*r^2. The expression is evaluated using ball arithmetic at a working
   * precision of prec bits, and the rounding error during this evaluation is
   * accounted for in the output. We now check x' in I and r'<r. If both
   * conditions are satisfied, we set xnew to and x and return xnew. If either
   * condition fails, we set xnew to x and return null, indicating that this step
   * diverges
   * 
   * @param xnew
   * @param x
   * @param convergenceRegion
   * @param convergenceFactor
   * @param prec
   * @return
   */
  public default Real
         calculateNewtonStep(Real xnew, Real x, Real convergenceRegion, Float convergenceFactor, int prec)
  {
    try ( Magnitude v = convergenceFactor.getMagnitude(Magnitude.claim());
          Magnitude err = x.getRad().pow(2, Magnitude.claim()); Real t = Real.claim(); Real u = Real.claim2();)
    {
      err.mul(v, err);
      t.getMid().assign(x.getMid());
      t.getRad().zero();
      evaluate(u, 2, prec, t);

      Real      dt        = u.div(u.get(1), prec, u);
      Real      z         = t.sub(dt, prec, u);
      Magnitude urad      = u.getRad();
      boolean   converged = u.contains(convergenceRegion) && urad.add(urad, err).compareTo(x.getRad()) < 0;
      if (converged)
      {
        return xnew.swap(u);
      }
      else
      {
        xnew.set(x);
        return null;
      }
    }
  }

  /**
   * TODO: add option for monotonic convergence
   * 
   * @param func
   * @param z0
   * @param convergenceTester
   * @param iters             on entry, the max number of iterations to allow, on
   *                          return, the actual number of iterations
   * @param res
   * @param bits
   * @return
   */
  public static Real iteratedCompositionLimit(RealFunction func,
                                              Real z0,
                                              RealConvergenceTester convergenceTester,
                                              int iters[],
                                              Real res,
                                              int bits)
  {
    // TODO: fix loss of precision
    try ( Real z = Real.claim().set(z0);)
    {
      int i;
      for (i = 0; i < iters[0]; i++)
      {
        assert z.isFinite() : String.format("z is not finite,i=%s", i);
        func.evaluate(z, 1, bits, res);
        bits = res.relAccuracyBits() * 2;

        assert res.isFinite() : String.format("res is not finite: i=%s and z=%s", i, z);
        if (convergenceTester.converged(i, z, res))
        {
          iters[0] = i;
          return res;
        }
        res.setRad(Constants.zeroMag);
        z.set(res);
      }
      iters[0] = i;
      return res;
    }
  }

  /**
   * Iterated function composition
   * 
   * @param func
   * @param z0   initial point
   * @param res  an array , how many iterations of the function composition to
   *             contain
   * @param prec TODO
   * @return res, filled with iterates of func starting from z0 as the initial
   *         point
   */
  static public Real iteratedComposition(RealFunction func, Real z0, Real res, int prec)
  {
    int  n = res.dim;
    Real z = z0;
    for (int i = 0; i < n; i++)
    {
      func.evaluate(z, 1, prec, z = res.get(i));
    }
    return res;
  }
}
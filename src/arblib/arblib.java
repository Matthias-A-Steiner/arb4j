/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

public class arblib {
  public static int isolateRootsOfRealFunction(SWIGTYPE_p_arf_interval_ptr found, SWIGTYPE_p_p_int flags, java.lang.Object realFunction, SWIGTYPE_p_arf_interval_ptr interval, int maxdepth, int maxeval, int maxfound, int prec) {
    return arblibJNI.isolateRootsOfRealFunction(SWIGTYPE_p_arf_interval_ptr.getCPtr(found), SWIGTYPE_p_p_int.getCPtr(flags), realFunction, SWIGTYPE_p_arf_interval_ptr.getCPtr(interval), maxdepth, maxeval, maxfound, prec);
  }

  public static void arb_mat_init(RealMatrix mat, int r, int c) {
    arblibJNI.arb_mat_init(RealMatrix.getCPtr(mat), mat, r, c);
  }

  public static void acb_mat_init(ComplexMatrix mat, int r, int c) {
    arblibJNI.acb_mat_init(ComplexMatrix.getCPtr(mat), mat, r, c);
  }

  public static void acb_dot(Complex res, Complex initial, int subtract, Complex x, int xstep, Complex y, int ystep, int len, int prec) {
    arblibJNI.acb_dot(Complex.getCPtr(res), res, Complex.getCPtr(initial), initial, subtract, Complex.getCPtr(x), x, xstep, Complex.getCPtr(y), y, ystep, len, prec);
  }

  public static void acb_poly_interpolate_newton(ComplexPolynomial poly, Complex xs, Complex ys, int n, int prec) {
    arblibJNI.acb_poly_interpolate_newton(ComplexPolynomial.getCPtr(poly), poly, Complex.getCPtr(xs), xs, Complex.getCPtr(ys), ys, n, prec);
  }

  public static void arf_zero(Float x) {
    arblibJNI.arf_zero(Float.getCPtr(x), x);
  }

  public static void acb_cosh(Complex y, Complex x, int prec) {
    arblibJNI.acb_cosh(Complex.getCPtr(y), y, Complex.getCPtr(x), x, prec);
  }

  public static void arf_neg(Float y, Float x) {
    arblibJNI.arf_neg(Float.getCPtr(y), y, Float.getCPtr(x), x);
  }

  public static int arf_mul_rnd_any(Float z, Float x, Float y, int prec, int rnd) {
    return arblibJNI.arf_mul_rnd_any(Float.getCPtr(z), z, Float.getCPtr(x), x, Float.getCPtr(y), y, prec, rnd);
  }

  public static int arf_mul_rnd_down(Float z, Float x, Float y, int prec) {
    return arblibJNI.arf_mul_rnd_down(Float.getCPtr(z), z, Float.getCPtr(x), x, Float.getCPtr(y), y, prec);
  }

  public static SWIGTYPE_p_void flint_malloc(long size) {
    long cPtr = arblibJNI.flint_malloc(size);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public static SWIGTYPE_p_void flint_realloc(SWIGTYPE_p_void ptr, long size) {
    long cPtr = arblibJNI.flint_realloc(SWIGTYPE_p_void.getCPtr(ptr), size);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public static SWIGTYPE_p_void flint_calloc(long num, long size) {
    long cPtr = arblibJNI.flint_calloc(num, size);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public static void color_function(double[] R, double[] G, double[] B, Complex z, int mode) {
    arblibJNI.color_function(R, G, B, Complex.getCPtr(z), z, mode);
  }

  public static void acb_set_d(Complex z, double c) {
    arblibJNI.acb_set_d(Complex.getCPtr(z), z, c);
  }

  public static void acb_dirichlet_zeta_jet(Complex res, Complex s, int deflate, int len, int prec) {
    arblibJNI.acb_dirichlet_zeta_jet(Complex.getCPtr(res), res, Complex.getCPtr(s), s, deflate, len, prec);
  }

  public static void acb_dirichlet_hardy_z(Complex res, Complex t, DirichletGroup G, DirichletCharacter chi, int len, int prec) {
    arblibJNI.acb_dirichlet_hardy_z(Complex.getCPtr(res), res, Complex.getCPtr(t), t, DirichletGroup.getCPtr(G), G, DirichletCharacter.getCPtr(chi), chi, len, prec);
  }

  public static void acb_print(Complex x) {
    arblibJNI.acb_print(Complex.getCPtr(x), x);
  }

  public static String arb_get_str(Real x, int n, long flags) {
    return arblibJNI.arb_get_str(Real.getCPtr(x), x, n, flags);
  }

  public static double arf_get_d(Float x, int rnd) {
    return arblibJNI.arf_get_d(Float.getCPtr(x), x, rnd);
  }

  public static void arf_set_d(Float x, double v) {
    arblibJNI.arf_set_d(Float.getCPtr(x), x, v);
  }

  public static int arf_sub(Float z, Float x, Float y, int prec, int rnd) {
    return arblibJNI.arf_sub(Float.getCPtr(z), z, Float.getCPtr(x), x, Float.getCPtr(y), y, prec, rnd);
  }

  public static void acb_conj(Complex z, Complex x) {
    arblibJNI.acb_conj(Complex.getCPtr(z), z, Complex.getCPtr(x), x);
  }

  public static void arb_floor(Real y, Real x, int prec) {
    arblibJNI.arb_floor(Real.getCPtr(y), y, Real.getCPtr(x), x, prec);
  }

  public static void arb_ceil(Real y, Real x, int prec) {
    arblibJNI.arb_ceil(Real.getCPtr(y), y, Real.getCPtr(x), x, prec);
  }

  public static void arb_div(Real z, Real x, Real y, int prec) {
    arblibJNI.arb_div(Real.getCPtr(z), z, Real.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void acb_div_si(Complex z, Complex x, int y, int prec) {
    arblibJNI.acb_div_si(Complex.getCPtr(z), z, Complex.getCPtr(x), x, y, prec);
  }

  public static void arb_sin(Real s, Real x, int prec) {
    arblibJNI.arb_sin(Real.getCPtr(s), s, Real.getCPtr(x), x, prec);
  }

  public static void arb_cos(Real s, Real x, int prec) {
    arblibJNI.arb_cos(Real.getCPtr(s), s, Real.getCPtr(x), x, prec);
  }

  public static void arb_mul(Real z, Real x, Real y, int prec) {
    arblibJNI.arb_mul(Real.getCPtr(z), z, Real.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static int arf_abs_bound_lt_2exp_si(Float x) {
    return arblibJNI.arf_abs_bound_lt_2exp_si(Float.getCPtr(x), x);
  }

  public static void arb_mul_2exp_si(Real y, Real x, int e) {
    arblibJNI.arb_mul_2exp_si(Real.getCPtr(y), y, Real.getCPtr(x), x, e);
  }

  public static void arb_get_abs_ubound_arf(Float u, Real x, int prec) {
    arblibJNI.arb_get_abs_ubound_arf(Float.getCPtr(u), u, Real.getCPtr(x), x, prec);
  }

  public static void arb_mul_ui(Real z, Real x, long y, int prec) {
    arblibJNI.arb_mul_ui(Real.getCPtr(z), z, Real.getCPtr(x), x, y, prec);
  }

  public static void arb_mul_si(Real z, Real x, long y, int prec) {
    arblibJNI.arb_mul_si(Real.getCPtr(z), z, Real.getCPtr(x), x, y, prec);
  }

  public static void acb_mul_si(Complex z, Complex x, int y, int prec) {
    arblibJNI.acb_mul_si(Complex.getCPtr(z), z, Complex.getCPtr(x), x, y, prec);
  }

  public static int arf_mul_ui(Float z, Float x, long y, int prec, int rnd) {
    return arblibJNI.arf_mul_ui(Float.getCPtr(z), z, Float.getCPtr(x), x, y, prec, rnd);
  }

  public static int arf_div_ui(Float z, Float x, long y, int prec, int rnd) {
    return arblibJNI.arf_div_ui(Float.getCPtr(z), z, Float.getCPtr(x), x, y, prec, rnd);
  }

  public static int mag_cmp(Magnitude x, Magnitude y) {
    return arblibJNI.mag_cmp(Magnitude.getCPtr(x), x, Magnitude.getCPtr(y), y);
  }

  public static void arb_swap(Real x, Real y) {
    arblibJNI.arb_swap(Real.getCPtr(x), x, Real.getCPtr(y), y);
  }

  public static void mag_init(Magnitude x) {
    arblibJNI.mag_init(Magnitude.getCPtr(x), x);
  }

  public static void mag_add(Magnitude z, Magnitude x, Magnitude y) {
    arblibJNI.mag_add(Magnitude.getCPtr(z), z, Magnitude.getCPtr(x), x, Magnitude.getCPtr(y), y);
  }

  public static void arf_set(Float y, Float x) {
    arblibJNI.arf_set(Float.getCPtr(y), y, Float.getCPtr(x), x);
  }

  public static void mag_pow_ui(Magnitude res, Magnitude x, long e) {
    arblibJNI.mag_pow_ui(Magnitude.getCPtr(res), res, Magnitude.getCPtr(x), x, e);
  }

  public static void mag_mul(Magnitude z, Magnitude x, Magnitude y) {
    arblibJNI.mag_mul(Magnitude.getCPtr(z), z, Magnitude.getCPtr(x), x, Magnitude.getCPtr(y), y);
  }

  public static void arf_get_mag(Magnitude y, Float x) {
    arblibJNI.arf_get_mag(Magnitude.getCPtr(y), y, Float.getCPtr(x), x);
  }

  public static int arf_add(Float z, Float x, Float y, int prec, int rnd) {
    return arblibJNI.arf_add(Float.getCPtr(z), z, Float.getCPtr(x), x, Float.getCPtr(y), y, prec, rnd);
  }

  public static int acb_rel_accuracy_bits(Complex x) {
    return arblibJNI.acb_rel_accuracy_bits(Complex.getCPtr(x), x);
  }

  public static int arb_bits(Real x) {
    return arblibJNI.arb_bits(Real.getCPtr(x), x);
  }

  public static void acb_pow_ui(Complex y, Complex b, long e, int prec) {
    arblibJNI.acb_pow_ui(Complex.getCPtr(y), y, Complex.getCPtr(b), b, e, prec);
  }

  public static void acb_add_ui(Complex z, Complex x, long c, int prec) {
    arblibJNI.acb_add_ui(Complex.getCPtr(z), z, Complex.getCPtr(x), x, c, prec);
  }

  public static void acb_log(Complex r, Complex z, int prec) {
    arblibJNI.acb_log(Complex.getCPtr(r), r, Complex.getCPtr(z), z, prec);
  }

  public static void acb_tanh(Complex y, Complex x, int prec) {
    arblibJNI.acb_tanh(Complex.getCPtr(y), y, Complex.getCPtr(x), x, prec);
  }

  public static void arb_set_d(Real x, double y) {
    arblibJNI.arb_set_d(Real.getCPtr(x), x, y);
  }

  public static void acb_dirichlet_hurwitz(Complex res, Complex s, Complex a, int prec) {
    arblibJNI.acb_dirichlet_hurwitz(Complex.getCPtr(res), res, Complex.getCPtr(s), s, Complex.getCPtr(a), a, prec);
  }

  public static void acb_dirichlet_hardy_theta(Complex res, Complex t, DirichletGroup G, DirichletCharacter chi, int len, int prec) {
    arblibJNI.acb_dirichlet_hardy_theta(Complex.getCPtr(res), res, Complex.getCPtr(t), t, DirichletGroup.getCPtr(G), G, DirichletCharacter.getCPtr(chi), chi, len, prec);
  }

  public static void acb_mul(Complex z, Complex x, Complex y, int prec) {
    arblibJNI.acb_mul(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Complex.getCPtr(y), y, prec);
  }

  public static void acb_cos(Complex r, Complex z, int prec) {
    arblibJNI.acb_cos(Complex.getCPtr(r), r, Complex.getCPtr(z), z, prec);
  }

  public static void acb_sin(Complex r, Complex z, int prec) {
    arblibJNI.acb_sin(Complex.getCPtr(r), r, Complex.getCPtr(z), z, prec);
  }

  public static void acb_exp(Complex y, Complex z, int prec) {
    arblibJNI.acb_exp(Complex.getCPtr(y), y, Complex.getCPtr(z), z, prec);
  }

  public static void arb_add(Real z, Real x, Real y, int prec) {
    arblibJNI.arb_add(Real.getCPtr(z), z, Real.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void arb_sub(Real z, Real x, Real y, int prec) {
    arblibJNI.arb_sub(Real.getCPtr(z), z, Real.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void acb_arg(Real r, Complex z, int prec) {
    arblibJNI.acb_arg(Real.getCPtr(r), r, Complex.getCPtr(z), z, prec);
  }

  public static void acb_modular_eisenstein(Complex r, Complex tau, int len, int prec) {
    arblibJNI.acb_modular_eisenstein(Complex.getCPtr(r), r, Complex.getCPtr(tau), tau, len, prec);
  }

  public static void acb_pow_arb(Complex z, Complex x, Real y, int prec) {
    arblibJNI.acb_pow_arb(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void acb_pow(Complex z, Complex x, Complex y, int prec) {
    arblibJNI.acb_pow(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Complex.getCPtr(y), y, prec);
  }

  public static void nthHardyZero(Real res, int n, int prec) {
    arblibJNI.nthHardyZero(Real.getCPtr(res), res, n, prec);
  }

  public static int arb_contains(Real x, Real y) {
    return arblibJNI.arb_contains(Real.getCPtr(x), x, Real.getCPtr(y), y);
  }

  public static int arb_set_str(Real res, String inp, int prec) {
    return arblibJNI.arb_set_str(Real.getCPtr(res), res, inp, prec);
  }

  public static int arb_overlaps(Real x, Real y) {
    return arblibJNI.arb_overlaps(Real.getCPtr(x), x, Real.getCPtr(y), y);
  }

  public static Complex _acb_vec_init(int n) {
    long cPtr = arblibJNI._acb_vec_init(n);
    return (cPtr == 0) ? null : new Complex(cPtr, false);
  }

  public static Real _arb_vec_init(int n) {
    long cPtr = arblibJNI._arb_vec_init(n);
    return (cPtr == 0) ? null : new Real(cPtr, false);
  }

  public static void _acb_vec_clear(Complex v, int n) {
    arblibJNI._acb_vec_clear(Complex.getCPtr(v), v, n);
  }

  public static void acb_mul_ui(Complex z, Complex x, long y, int prec) {
    arblibJNI.acb_mul_ui(Complex.getCPtr(z), z, Complex.getCPtr(x), x, y, prec);
  }

  public static void acb_set(Complex z, Complex x) {
    arblibJNI.acb_set(Complex.getCPtr(z), z, Complex.getCPtr(x), x);
  }

  public static void acb_clear(Complex x) {
    arblibJNI.acb_clear(Complex.getCPtr(x), x);
  }

  public static void acb_add(Complex z, Complex x, Complex y, int prec) {
    arblibJNI.acb_add(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Complex.getCPtr(y), y, prec);
  }

  public static void acb_sub_ui(Complex z, Complex x, long y, int prec) {
    arblibJNI.acb_sub_ui(Complex.getCPtr(z), z, Complex.getCPtr(x), x, y, prec);
  }

  public static void acb_div(Complex z, Complex x, Complex y, int prec) {
    arblibJNI.acb_div(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Complex.getCPtr(y), y, prec);
  }

  public static void acb_sub(Complex z, Complex x, Complex y, int prec) {
    arblibJNI.acb_sub(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Complex.getCPtr(y), y, prec);
  }

  public static void acb_mul_fmpz(Complex z, Complex x, SWIGTYPE_p_fmpz y, int prec) {
    arblibJNI.acb_mul_fmpz(Complex.getCPtr(z), z, Complex.getCPtr(x), x, SWIGTYPE_p_fmpz.getCPtr(y), prec);
  }

  public static void _arb_vec_clear(Real v, int n) {
    arblibJNI._arb_vec_clear(Real.getCPtr(v), v, n);
  }

  public static void acb_mul_onei(Complex z, Complex x) {
    arblibJNI.acb_mul_onei(Complex.getCPtr(z), z, Complex.getCPtr(x), x);
  }

  public static void acb_div_onei(Complex z, Complex x) {
    arblibJNI.acb_div_onei(Complex.getCPtr(z), z, Complex.getCPtr(x), x);
  }

  public static void acb_neg(Complex z, Complex x) {
    arblibJNI.acb_neg(Complex.getCPtr(z), z, Complex.getCPtr(x), x);
  }

  public static void arb_pow_ui(Real y, Real b, long e, int prec) {
    arblibJNI.arb_pow_ui(Real.getCPtr(y), y, Real.getCPtr(b), b, e, prec);
  }

  public static void acb_abs(Real r, Complex z, int prec) {
    arblibJNI.acb_abs(Real.getCPtr(r), r, Complex.getCPtr(z), z, prec);
  }

  public static void arb_sqrt(Real z, Real x, int prec) {
    arblibJNI.arb_sqrt(Real.getCPtr(z), z, Real.getCPtr(x), x, prec);
  }

  public static void acb_sqr(Complex z, Complex x, int prec) {
    arblibJNI.acb_sqr(Complex.getCPtr(z), z, Complex.getCPtr(x), x, prec);
  }

  public static int acb_contains(Complex x, Complex y) {
    return arblibJNI.acb_contains(Complex.getCPtr(x), x, Complex.getCPtr(y), y);
  }

  public static int acb_overlaps(Complex x, Complex y) {
    return arblibJNI.acb_overlaps(Complex.getCPtr(x), x, Complex.getCPtr(y), y);
  }

  public static int acb_contains_zero(Complex x) {
    return arblibJNI.acb_contains_zero(Complex.getCPtr(x), x);
  }

  public static int acb_allocated_bytes(Complex x) {
    return arblibJNI.acb_allocated_bytes(Complex.getCPtr(x), x);
  }

  public static void acb_mul_arb(Complex z, Complex x, Real y, int prec) {
    arblibJNI.acb_mul_arb(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void acb_div_arb(Complex z, Complex x, Real y, int prec) {
    arblibJNI.acb_div_arb(Complex.getCPtr(z), z, Complex.getCPtr(x), x, Real.getCPtr(y), y, prec);
  }

  public static void arb_set(Real z, Real x) {
    arblibJNI.arb_set(Real.getCPtr(z), z, Real.getCPtr(x), x);
  }

  public static void acb_elliptic_k(Complex res, Complex m, int prec) {
    arblibJNI.acb_elliptic_k(Complex.getCPtr(res), res, Complex.getCPtr(m), m, prec);
  }

  public static void mp_set_memory_functions(SWIGTYPE_p_f_size_t__p_void arg0, SWIGTYPE_p_f_p_void_size_t_size_t__p_void arg1, SWIGTYPE_p_f_p_void_size_t__void arg2) {
    arblibJNI.mp_set_memory_functions(SWIGTYPE_p_f_size_t__p_void.getCPtr(arg0), SWIGTYPE_p_f_p_void_size_t_size_t__p_void.getCPtr(arg1), SWIGTYPE_p_f_p_void_size_t__void.getCPtr(arg2));
  }

  public static void acb_get_mid(Complex m, Complex x) {
    arblibJNI.acb_get_mid(Complex.getCPtr(m), m, Complex.getCPtr(x), x);
  }

  public static void arb_const_pi(Real z, int prec) {
    arblibJNI.arb_const_pi(Real.getCPtr(z), z, prec);
  }

  public static int arb_is_finite(Real z) {
    return arblibJNI.arb_is_finite(Real.getCPtr(z), z);
  }

  public static int acb_is_finite(Complex z) {
    return arblibJNI.acb_is_finite(Complex.getCPtr(z), z);
  }

  public static void arb_abs(Real y, Real x) {
    arblibJNI.arb_abs(Real.getCPtr(y), y, Real.getCPtr(x), x);
  }

  public static void arb_zero(Real x) {
    arblibJNI.arb_zero(Real.getCPtr(x), x);
  }

  public static void arb_one(Real f) {
    arblibJNI.arb_one(Real.getCPtr(f), f);
  }

  public static void arb_pos_inf(Real x) {
    arblibJNI.arb_pos_inf(Real.getCPtr(x), x);
  }

  public static void arb_neg_inf(Real x) {
    arblibJNI.arb_neg_inf(Real.getCPtr(x), x);
  }

  public static void arb_zero_pm_inf(Real x) {
    arblibJNI.arb_zero_pm_inf(Real.getCPtr(x), x);
  }

  public static void arb_indeterminate(Real x) {
    arblibJNI.arb_indeterminate(Real.getCPtr(x), x);
  }

  public static void arb_zero_pm_one(Real x) {
    arblibJNI.arb_zero_pm_one(Real.getCPtr(x), x);
  }

  public static int arb_rel_accuracy_bits(Real x) {
    return arblibJNI.arb_rel_accuracy_bits(Real.getCPtr(x), x);
  }

  public static void arb_unit_interval(Real x) {
    arblibJNI.arb_unit_interval(Real.getCPtr(x), x);
  }

  public static void mag_zero(Magnitude res) {
    arblibJNI.mag_zero(Magnitude.getCPtr(res), res);
  }

  public static void arb_div_si(Real z, Real x, int y, int prec) {
    arblibJNI.arb_div_si(Real.getCPtr(z), z, Real.getCPtr(x), x, y, prec);
  }

  public static void arb_trim(Real y, Real x) {
    arblibJNI.arb_trim(Real.getCPtr(y), y, Real.getCPtr(x), x);
  }

  public static void arb_set_interval_mag(Real x, Magnitude a, Magnitude b, int prec) {
    arblibJNI.arb_set_interval_mag(Real.getCPtr(x), x, Magnitude.getCPtr(a), a, Magnitude.getCPtr(b), b, prec);
  }

  public static void acb_modular_theta_series(ComplexPolynomial theta1, ComplexPolynomial theta2, ComplexPolynomial theta3, ComplexPolynomial theta4, ComplexPolynomial z, Complex tau, int len, int prec) {
    arblibJNI.acb_modular_theta_series(ComplexPolynomial.getCPtr(theta1), theta1, ComplexPolynomial.getCPtr(theta2), theta2, ComplexPolynomial.getCPtr(theta3), theta3, ComplexPolynomial.getCPtr(theta4), theta4, ComplexPolynomial.getCPtr(z), z, Complex.getCPtr(tau), tau, len, prec);
  }

  public static void acb_poly_init(ComplexPolynomial poly) {
    arblibJNI.acb_poly_init(ComplexPolynomial.getCPtr(poly), poly);
  }

  public static void arb_init(Real x) {
    arblibJNI.arb_init(Real.getCPtr(x), x);
  }

  public static void arf_init(Float x) {
    arblibJNI.arf_init(Float.getCPtr(x), x);
  }

  public static void acb_init(Complex x) {
    arblibJNI.acb_init(Complex.getCPtr(x), x);
  }

  public static void arb_tanh(Real y, Real x, int prec) {
    arblibJNI.arb_tanh(Real.getCPtr(y), y, Real.getCPtr(x), x, prec);
  }

  public static void arb_asinh(Real z, Real x, int prec) {
    arblibJNI.arb_asinh(Real.getCPtr(z), z, Real.getCPtr(x), x, prec);
  }

  public static void arb_acosh(Real z, Real x, int prec) {
    arblibJNI.arb_acosh(Real.getCPtr(z), z, Real.getCPtr(x), x, prec);
  }

  public static void arb_lambertw(Real res, Real x, int flags, int prec) {
    arblibJNI.arb_lambertw(Real.getCPtr(res), res, Real.getCPtr(x), x, flags, prec);
  }

  public static void arb_gamma(Real z, Real x, int prec) {
    arblibJNI.arb_gamma(Real.getCPtr(z), z, Real.getCPtr(x), x, prec);
  }

  public static void arb_lgamma(Real z, Real x, int prec) {
    arblibJNI.arb_lgamma(Real.getCPtr(z), z, Real.getCPtr(x), x, prec);
  }

  public static void arb_sqr(Real y, Real x, int prec) {
    arblibJNI.arb_sqr(Real.getCPtr(y), y, Real.getCPtr(x), x, prec);
  }

  public static void acb_sqrt(Complex y, Complex x, int prec) {
    arblibJNI.acb_sqrt(Complex.getCPtr(y), y, Complex.getCPtr(x), x, prec);
  }

}

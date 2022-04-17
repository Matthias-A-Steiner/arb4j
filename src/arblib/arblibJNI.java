/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

public class arblibJNI {
  public final static native void arb_neg(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native void arf_interval_swap(long jarg1, FloatInterval jarg1_, long jarg2, FloatInterval jarg2_);
  public final static native void arb_set_arf(long jarg1, Real jarg1_, long jarg2, Float jarg2_);
  public final static native int arb_is_positive(long jarg1, Real jarg1_);
  public final static native int arb_is_negative(long jarg1, Real jarg1_);
  public final static native void arf_mul_2exp_si(long jarg1, Float jarg1_, long jarg2, Float jarg2_, int jarg3);
  public final static native void arf_interval_set(long jarg1, FloatInterval jarg1_, long jarg2, FloatInterval jarg2_);
  public final static native void arf_interval_get_arb(long jarg1, Real jarg1_, long jarg2, FloatInterval jarg2_, int jarg3);
  public final static native int arb_sgn_nonzero(long jarg1, Real jarg1_);
  public final static native void acb_poly_derivative(long jarg1, ComplexPolynomial jarg1_, long jarg2, ComplexPolynomial jarg2_, int jarg3);
  public final static native void acb_poly_integral(long jarg1, ComplexPolynomial jarg1_, long jarg2, ComplexPolynomial jarg2_, int jarg3);
  public final static native void acb_poly_product_roots(long jarg1, ComplexPolynomial jarg1_, long jarg2, Complex jarg2_, int jarg3, int jarg4);
  public final static native void acb_poly_evaluate(long jarg1, Complex jarg1_, long jarg2, ComplexPolynomial jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_poly_evaluate2(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, ComplexPolynomial jarg3_, long jarg4, Complex jarg4_, int jarg5);
  public final static native void flint_free(long jarg1);
  public final static native void arb_mat_init(long jarg1, RealMatrix jarg1_, int jarg2, int jarg3);
  public final static native void acb_mat_init(long jarg1, ComplexMatrix jarg1_, int jarg2, int jarg3);
  public final static native void acb_dot(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3, long jarg4, Complex jarg4_, int jarg5, long jarg6, Complex jarg6_, int jarg7, int jarg8, int jarg9);
  public final static native void acb_poly_interpolate_newton(long jarg1, ComplexPolynomial jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4, int jarg5);
  public final static native void arf_zero(long jarg1, Float jarg1_);
  public final static native void acb_cosh(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void arf_neg(long jarg1, Float jarg1_, long jarg2, Float jarg2_);
  public final static native int arf_mul_si(long jarg1, Float jarg1_, long jarg2, Float jarg2_, int jarg3, int jarg4, int jarg5);
  public final static native int arf_mul_rnd_any(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, Float jarg3_, int jarg4, int jarg5);
  public final static native int arf_mul_rnd_down(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, Float jarg3_, int jarg4);
  public final static native long flint_malloc(long jarg1);
  public final static native long flint_realloc(long jarg1, long jarg2);
  public final static native long flint_calloc(long jarg1, long jarg2);
  public final static native void color_function(double[] jarg1, double[] jarg2, double[] jarg3, long jarg4, Complex jarg4_, int jarg5);
  public final static native void acb_set_d(long jarg1, Complex jarg1_, double jarg2);
  public final static native void acb_dirichlet_zeta_jet(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3, int jarg4, int jarg5);
  public final static native void acb_dirichlet_hardy_z(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, DirichletGroup jarg3_, long jarg4, DirichletCharacter jarg4_, int jarg5, int jarg6);
  public final static native void acb_print(long jarg1, Complex jarg1_);
  public final static native String arb_get_str(long jarg1, Real jarg1_, int jarg2, long jarg3);
  public final static native double arf_get_d(long jarg1, Float jarg1_, int jarg2);
  public final static native void arf_set_d(long jarg1, Float jarg1_, double jarg2);
  public final static native int arf_sub(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, Float jarg3_, int jarg4, int jarg5);
  public final static native void acb_conj(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void arb_floor(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_ceil(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_div(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void acb_div_si(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3, int jarg4);
  public final static native void arb_sin(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_cos(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_mul(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native int arf_abs_bound_lt_2exp_si(long jarg1, Float jarg1_);
  public final static native void arb_mul_2exp_si(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_get_abs_ubound_arf(long jarg1, Float jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_mul_ui(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, int jarg4);
  public final static native void arb_mul_si(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, int jarg4);
  public final static native void acb_mul_si(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3, int jarg4);
  public final static native int arf_mul_ui(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, int jarg4, int jarg5);
  public final static native int arf_div_ui(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, int jarg4, int jarg5);
  public final static native int mag_cmp(long jarg1, Magnitude jarg1_, long jarg2, Magnitude jarg2_);
  public final static native void arb_swap(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native void mag_init(long jarg1, Magnitude jarg1_);
  public final static native void mag_add(long jarg1, Magnitude jarg1_, long jarg2, Magnitude jarg2_, long jarg3, Magnitude jarg3_);
  public final static native void arf_set(long jarg1, Float jarg1_, long jarg2, Float jarg2_);
  public final static native void mag_pow_ui(long jarg1, Magnitude jarg1_, long jarg2, Magnitude jarg2_, long jarg3);
  public final static native void mag_mul(long jarg1, Magnitude jarg1_, long jarg2, Magnitude jarg2_, long jarg3, Magnitude jarg3_);
  public final static native void arf_get_mag(long jarg1, Magnitude jarg1_, long jarg2, Float jarg2_);
  public final static native int arf_add(long jarg1, Float jarg1_, long jarg2, Float jarg2_, long jarg3, Float jarg3_, int jarg4, int jarg5);
  public final static native int acb_rel_accuracy_bits(long jarg1, Complex jarg1_);
  public final static native int arb_bits(long jarg1, Real jarg1_);
  public final static native void acb_pow_ui(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, int jarg4);
  public final static native void acb_add_ui(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, int jarg4);
  public final static native void acb_log(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void acb_tanh(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void arb_set_d(long jarg1, Real jarg1_, double jarg2);
  public final static native void acb_dirichlet_hurwitz(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_dirichlet_hardy_theta(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, DirichletGroup jarg3_, long jarg4, DirichletCharacter jarg4_, int jarg5, int jarg6);
  public final static native void acb_mul(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_cos(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void acb_sin(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void acb_exp(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void arb_add(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void arb_sub(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void acb_arg(long jarg1, Real jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void acb_modular_eisenstein(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3, int jarg4);
  public final static native void acb_pow_arb(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void acb_pow(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void nthHardyZero(long jarg1, Real jarg1_, int jarg2, int jarg3);
  public final static native int arb_contains(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native int arb_contains_zero(long jarg1, Real jarg1_);
  public final static native int arb_set_str(long jarg1, Real jarg1_, String jarg2, int jarg3);
  public final static native int arb_overlaps(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native long _acb_vec_init(int jarg1);
  public final static native long _arb_vec_init(int jarg1);
  public final static native void _acb_vec_clear(long jarg1, Complex jarg1_, int jarg2);
  public final static native void acb_mul_ui(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, int jarg4);
  public final static native void acb_set(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void acb_clear(long jarg1, Complex jarg1_);
  public final static native void acb_add(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_sub_ui(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, int jarg4);
  public final static native void acb_div(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_sub(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Complex jarg3_, int jarg4);
  public final static native void acb_mul_fmpz(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, int jarg4);
  public final static native void _arb_vec_clear(long jarg1, Real jarg1_, int jarg2);
  public final static native void acb_mul_onei(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void acb_div_onei(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void acb_neg(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void arb_pow_ui(long jarg1, Real jarg1_, long jarg2, Real jarg2_, long jarg3, int jarg4);
  public final static native void acb_abs(long jarg1, Real jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void arb_sqrt(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void acb_sqr(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native int acb_contains(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native int acb_overlaps(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native int acb_contains_zero(long jarg1, Complex jarg1_);
  public final static native int acb_allocated_bytes(long jarg1, Complex jarg1_);
  public final static native void acb_mul_arb(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void acb_div_arb(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, long jarg3, Real jarg3_, int jarg4);
  public final static native void arb_set(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native void acb_elliptic_k(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void mp_set_memory_functions(long jarg1, long jarg2, long jarg3);
  public final static native void acb_get_mid(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_);
  public final static native void arb_const_pi(long jarg1, Real jarg1_, int jarg2);
  public final static native int arb_is_finite(long jarg1, Real jarg1_);
  public final static native int acb_is_finite(long jarg1, Complex jarg1_);
  public final static native void arb_abs(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native void arb_zero(long jarg1, Real jarg1_);
  public final static native void arb_one(long jarg1, Real jarg1_);
  public final static native void arb_pos_inf(long jarg1, Real jarg1_);
  public final static native void arb_neg_inf(long jarg1, Real jarg1_);
  public final static native void arb_zero_pm_inf(long jarg1, Real jarg1_);
  public final static native void arb_indeterminate(long jarg1, Real jarg1_);
  public final static native void arb_zero_pm_one(long jarg1, Real jarg1_);
  public final static native int arb_rel_accuracy_bits(long jarg1, Real jarg1_);
  public final static native void arb_unit_interval(long jarg1, Real jarg1_);
  public final static native void mag_zero(long jarg1, Magnitude jarg1_);
  public final static native void arb_div_si(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3, int jarg4);
  public final static native void arb_trim(long jarg1, Real jarg1_, long jarg2, Real jarg2_);
  public final static native void arb_set_interval_mag(long jarg1, Real jarg1_, long jarg2, Magnitude jarg2_, long jarg3, Magnitude jarg3_, int jarg4);
  public final static native void acb_modular_theta_series(long jarg1, ComplexPolynomial jarg1_, long jarg2, ComplexPolynomial jarg2_, long jarg3, ComplexPolynomial jarg3_, long jarg4, ComplexPolynomial jarg4_, long jarg5, ComplexPolynomial jarg5_, long jarg6, Complex jarg6_, int jarg7, int jarg8);
  public final static native void acb_poly_init(long jarg1, ComplexPolynomial jarg1_);
  public final static native void arb_init(long jarg1, Real jarg1_);
  public final static native void arf_init(long jarg1, Float jarg1_);
  public final static native void acb_init(long jarg1, Complex jarg1_);
  public final static native void arb_tanh(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_asinh(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_acosh(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_lambertw(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3, int jarg4);
  public final static native void arb_gamma(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_lgamma(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void arb_sqr(long jarg1, Real jarg1_, long jarg2, Real jarg2_, int jarg3);
  public final static native void acb_sqrt(long jarg1, Complex jarg1_, long jarg2, Complex jarg2_, int jarg3);
  public final static native void RealMatrix_entries_set(long jarg1, RealMatrix jarg1_, long jarg2, Real jarg2_);
  public final static native long RealMatrix_entries_get(long jarg1, RealMatrix jarg1_);
  public final static native void RealMatrix_r_set(long jarg1, RealMatrix jarg1_, int jarg2);
  public final static native int RealMatrix_r_get(long jarg1, RealMatrix jarg1_);
  public final static native void RealMatrix_c_set(long jarg1, RealMatrix jarg1_, int jarg2);
  public final static native int RealMatrix_c_get(long jarg1, RealMatrix jarg1_);
  public final static native void RealMatrix_rows_set(long jarg1, RealMatrix jarg1_, long jarg2);
  public final static native long RealMatrix_rows_get(long jarg1, RealMatrix jarg1_);
  public final static native long new_RealMatrix();
  public final static native void delete_RealMatrix(long jarg1);
  public final static native void ComplexMatrix_entries_set(long jarg1, ComplexMatrix jarg1_, long jarg2, Complex jarg2_);
  public final static native long ComplexMatrix_entries_get(long jarg1, ComplexMatrix jarg1_);
  public final static native void ComplexMatrix_r_set(long jarg1, ComplexMatrix jarg1_, int jarg2);
  public final static native int ComplexMatrix_r_get(long jarg1, ComplexMatrix jarg1_);
  public final static native void ComplexMatrix_c_set(long jarg1, ComplexMatrix jarg1_, int jarg2);
  public final static native int ComplexMatrix_c_get(long jarg1, ComplexMatrix jarg1_);
  public final static native void ComplexMatrix_rows_set(long jarg1, ComplexMatrix jarg1_, long jarg2);
  public final static native long ComplexMatrix_rows_get(long jarg1, ComplexMatrix jarg1_);
  public final static native long new_ComplexMatrix();
  public final static native void delete_ComplexMatrix(long jarg1);
  public final static native void FloatInterval_a_set(long jarg1, FloatInterval jarg1_, long jarg2, Float jarg2_);
  public final static native long FloatInterval_a_get(long jarg1, FloatInterval jarg1_);
  public final static native void FloatInterval_b_set(long jarg1, FloatInterval jarg1_, long jarg2, Float jarg2_);
  public final static native long FloatInterval_b_get(long jarg1, FloatInterval jarg1_);
  public final static native long new_FloatInterval();
  public final static native void delete_FloatInterval(long jarg1);
  public final static native void Magnitude_exp_set(long jarg1, Magnitude jarg1_, long jarg2);
  public final static native long Magnitude_exp_get(long jarg1, Magnitude jarg1_);
  public final static native void Magnitude_man_set(long jarg1, Magnitude jarg1_, long jarg2);
  public final static native long Magnitude_man_get(long jarg1, Magnitude jarg1_);
  public final static native long new_Magnitude();
  public final static native void delete_Magnitude(long jarg1);
  public final static native void Complex_realObj_set(long jarg1, Complex jarg1_, long jarg2, Real jarg2_);
  public final static native long Complex_realObj_get(long jarg1, Complex jarg1_);
  public final static native void Complex_imagObj_set(long jarg1, Complex jarg1_, long jarg2, Real jarg2_);
  public final static native long Complex_imagObj_get(long jarg1, Complex jarg1_);
  public final static native long new_Complex();
  public final static native void delete_Complex(long jarg1);
  public final static native void DirichletGroup_q_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_q_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_q_even_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_q_even_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_mod_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_mod_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_rad_q_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_rad_q_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_phi_q_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_phi_q_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_neven_set(long jarg1, DirichletGroup jarg1_, int jarg2);
  public final static native int DirichletGroup_neven_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_num_set(long jarg1, DirichletGroup jarg1_, int jarg2);
  public final static native int DirichletGroup_num_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_expo_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_expo_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_P_set(long jarg1, DirichletGroup jarg1_, long jarg2, DirichletPrimeGroup jarg2_);
  public final static native long DirichletGroup_P_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_generators_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_generators_get(long jarg1, DirichletGroup jarg1_);
  public final static native void DirichletGroup_PHI_set(long jarg1, DirichletGroup jarg1_, long jarg2);
  public final static native long DirichletGroup_PHI_get(long jarg1, DirichletGroup jarg1_);
  public final static native long new_DirichletGroup();
  public final static native void delete_DirichletGroup(long jarg1);
  public final static native void DirichletCharacter_n_set(long jarg1, DirichletCharacter jarg1_, long jarg2);
  public final static native long DirichletCharacter_n_get(long jarg1, DirichletCharacter jarg1_);
  public final static native void DirichletCharacter_log_set(long jarg1, DirichletCharacter jarg1_, long jarg2);
  public final static native long DirichletCharacter_log_get(long jarg1, DirichletCharacter jarg1_);
  public final static native long new_DirichletCharacter();
  public final static native void delete_DirichletCharacter(long jarg1);
  public final static native void DirichletPrimeGroup_p_set(long jarg1, DirichletPrimeGroup jarg1_, long jarg2);
  public final static native long DirichletPrimeGroup_p_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native void DirichletPrimeGroup_e_set(long jarg1, DirichletPrimeGroup jarg1_, int jarg2);
  public final static native int DirichletPrimeGroup_e_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native void DirichletPrimeGroup_pe_set(long jarg1, DirichletPrimeGroup jarg1_, long jarg2);
  public final static native long DirichletPrimeGroup_pe_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native void DirichletPrimeGroup_phi_set(long jarg1, DirichletPrimeGroup jarg1_, long jarg2);
  public final static native long DirichletPrimeGroup_phi_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native void DirichletPrimeGroup_g_set(long jarg1, DirichletPrimeGroup jarg1_, long jarg2);
  public final static native long DirichletPrimeGroup_g_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native void DirichletPrimeGroup_dlog_set(long jarg1, DirichletPrimeGroup jarg1_, long jarg2);
  public final static native long DirichletPrimeGroup_dlog_get(long jarg1, DirichletPrimeGroup jarg1_);
  public final static native long new_DirichletPrimeGroup();
  public final static native void delete_DirichletPrimeGroup(long jarg1);
  public final static native void Real_mid_set(long jarg1, Real jarg1_, long jarg2, Float jarg2_);
  public final static native long Real_mid_get(long jarg1, Real jarg1_);
  public final static native void Real_rad_set(long jarg1, Real jarg1_, long jarg2, Magnitude jarg2_);
  public final static native long Real_rad_get(long jarg1, Real jarg1_);
  public final static native long new_Real();
  public final static native void delete_Real(long jarg1);
  public final static native void MantissaNoPointer_d_set(long jarg1, MantissaNoPointer jarg1_, long jarg2);
  public final static native long MantissaNoPointer_d_get(long jarg1, MantissaNoPointer jarg1_);
  public final static native long new_MantissaNoPointer();
  public final static native void delete_MantissaNoPointer(long jarg1);
  public final static native void MantissaPointer_alloc_set(long jarg1, MantissaPointer jarg1_, long jarg2);
  public final static native long MantissaPointer_alloc_get(long jarg1, MantissaPointer jarg1_);
  public final static native void MantissaPointer_d_set(long jarg1, MantissaPointer jarg1_, long jarg2);
  public final static native long MantissaPointer_d_get(long jarg1, MantissaPointer jarg1_);
  public final static native long new_MantissaPointer();
  public final static native void delete_MantissaPointer(long jarg1);
  public final static native void Mantissa_noptr_set(long jarg1, Mantissa jarg1_, long jarg2, MantissaNoPointer jarg2_);
  public final static native long Mantissa_noptr_get(long jarg1, Mantissa jarg1_);
  public final static native void Mantissa_ptr_set(long jarg1, Mantissa jarg1_, long jarg2, MantissaPointer jarg2_);
  public final static native long Mantissa_ptr_get(long jarg1, Mantissa jarg1_);
  public final static native long new_Mantissa();
  public final static native void delete_Mantissa(long jarg1);
  public final static native void Float_exp_set(long jarg1, Float jarg1_, long jarg2);
  public final static native long Float_exp_get(long jarg1, Float jarg1_);
  public final static native void Float_size_set(long jarg1, Float jarg1_, long jarg2);
  public final static native long Float_size_get(long jarg1, Float jarg1_);
  public final static native void Float_d_set(long jarg1, Float jarg1_, long jarg2, Mantissa jarg2_);
  public final static native long Float_d_get(long jarg1, Float jarg1_);
  public final static native long new_Float();
  public final static native void delete_Float(long jarg1);
  public final static native void MultiPrecFloat__mpfr_prec_set(long jarg1, MultiPrecFloat jarg1_, long jarg2);
  public final static native long MultiPrecFloat__mpfr_prec_get(long jarg1, MultiPrecFloat jarg1_);
  public final static native void MultiPrecFloat__mpfr_sign_set(long jarg1, MultiPrecFloat jarg1_, long jarg2);
  public final static native long MultiPrecFloat__mpfr_sign_get(long jarg1, MultiPrecFloat jarg1_);
  public final static native void MultiPrecFloat__mpfr_exp_set(long jarg1, MultiPrecFloat jarg1_, long jarg2);
  public final static native long MultiPrecFloat__mpfr_exp_get(long jarg1, MultiPrecFloat jarg1_);
  public final static native void MultiPrecFloat__mpfr_d_set(long jarg1, MultiPrecFloat jarg1_, long jarg2);
  public final static native long MultiPrecFloat__mpfr_d_get(long jarg1, MultiPrecFloat jarg1_);
  public final static native long new_MultiPrecFloat();
  public final static native void delete_MultiPrecFloat(long jarg1);
  public final static native void ComplexPolynomial_coeffs_set(long jarg1, ComplexPolynomial jarg1_, long jarg2, Complex jarg2_);
  public final static native long ComplexPolynomial_coeffs_get(long jarg1, ComplexPolynomial jarg1_);
  public final static native void ComplexPolynomial_length_set(long jarg1, ComplexPolynomial jarg1_, int jarg2);
  public final static native int ComplexPolynomial_length_get(long jarg1, ComplexPolynomial jarg1_);
  public final static native void ComplexPolynomial_alloc_set(long jarg1, ComplexPolynomial jarg1_, int jarg2);
  public final static native int ComplexPolynomial_alloc_get(long jarg1, ComplexPolynomial jarg1_);
  public final static native long new_ComplexPolynomial();
  public final static native void delete_ComplexPolynomial(long jarg1);
}

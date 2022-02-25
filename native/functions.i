void arf_zero(arf_t x);

void acb_cosh(acb_t y, const acb_t x, slong prec);

void arf_neg(arf_t y, const arf_t x);

int arf_mul_rnd_any(arf_ptr z, arf_srcptr x, arf_srcptr y, slong prec, arf_rnd_t rnd);

int arf_mul_rnd_down(arf_ptr z, arf_srcptr x, arf_srcptr y, slong prec);

void * flint_malloc(size_t size);
void * flint_realloc(void * ptr, size_t size);
void * flint_calloc(size_t num, size_t size);

%apply double *OUTPUT { double *R };
%apply double *OUTPUT { double *G };
%apply double *OUTPUT { double *B };

void color_function(double * R, double * G, double * B, const acb_t z, int mode);

 void acb_set_d(acb_t z, double c);

 void acb_dirichlet_zeta_jet(acb_t res, const acb_t s, int deflate, slong len, slong prec);
 
 void acb_dirichlet_hardy_z(acb_ptr res, 
                                  const acb_t t, 
								  const dirichlet_group_t G, 
								  const dirichlet_char_t chi,
								  slong len, 
								  slong prec);
	
 void acb_print(const acb_t x);
	 			  
 char *arb_get_str(const arb_t x, slong n, ulong flags);

 double arf_get_d(const arf_t x, arf_rnd_t rnd);

 void arf_set_d(arf_t x, double v);

 int arf_sub(arf_ptr z, arf_srcptr x, arf_srcptr y, slong prec, arf_rnd_t rnd);

 void acb_conj(acb_t z, const acb_t x);
 
void arb_floor(arb_t y, const arb_t x, slong prec);

void arb_ceil(arb_t y, const arb_t x, slong prec);

void arb_div(arb_t z, const arb_t x, const arb_t y, slong prec);

 void acb_div_si(acb_t z, const acb_t x, slong y, slong prec);
 
 void arb_sin(arb_t s, const arb_t x, slong prec);

 void arb_cos(arb_t s, const arb_t x, slong prec);

void arb_mul(arb_t z, const arb_t x, const arb_t y, slong prec);

slong arf_abs_bound_lt_2exp_si(const arf_t x);

void arb_mul_2exp_si(arb_t y, const arb_t x, slong e);

void arb_get_abs_ubound_arf(arf_t u, const arb_t x, slong prec);

 void arb_mul_ui(arb_t z, const arb_t x, ulong y, slong prec);
 
 void arb_mul_si(arb_t z, const arb_t x, ulong y, slong prec);

void acb_mul_si(acb_t z, const acb_t x, slong y, slong prec);

 
 int arf_mul_ui(arf_ptr z, arf_srcptr x, ulong y, slong prec, arf_rnd_t rnd);

 int arf_div_ui(arf_ptr z, arf_srcptr x, ulong y, slong prec, arf_rnd_t rnd);

 int mag_cmp(const mag_t x, const mag_t y);

 void arb_swap(arb_t x, arb_t y);

 void mag_init(mag_t x);
 
 void mag_add(mag_t z, const mag_t x, const mag_t y);
 
 void arf_set(arf_t y, const arf_t x);

 void mag_pow_ui(mag_t res, const mag_t x, ulong e);
 
 void mag_mul(mag_t z, const mag_t x, const mag_t y);

 void arf_get_mag(mag_t y, const arf_t x);

 int arf_add(arf_ptr z, arf_srcptr x, arf_srcptr y, slong prec, arf_rnd_t rnd);

 slong acb_rel_accuracy_bits(const acb_t x);

 void acb_dirichlet_hardy_z(acb_ptr res, const acb_t t, const dirichlet_group_t G, const dirichlet_char_t chi, slong len, slong prec);

 slong arb_bits(const arb_t x);
 
 void acb_pow_ui(acb_t y, const acb_t b, ulong e, slong prec);

 void acb_add_ui(acb_t z, const acb_t x, ulong c, slong prec);

 void acb_log(acb_t r, const acb_t z, slong prec);

 void acb_tanh(acb_t y, const acb_t x, slong prec);

 void color_function(double * R, double * G, double * B, const acb_t z, int mode);

 void arb_set_d(arb_t x, double y);
        
 void acb_dirichlet_hurwitz(acb_t res, const acb_t s, const acb_t a, slong prec);

 void acb_dirichlet_hardy_theta(acb_ptr res, const acb_t t, const dirichlet_group_t G, const dirichlet_char_t chi, slong len, slong prec);

 void acb_mul(acb_t z, const acb_t x, const acb_t y, slong prec);

void acb_cos(acb_t r, const acb_t z, slong prec);

void acb_sin(acb_t r, const acb_t z, slong prec);

 void acb_exp(acb_t y, const acb_t z, slong prec);
  
 void arb_add(arb_t z, const arb_t x, const arb_t y, slong prec);
  
 void arb_sub(arb_t z, const arb_t x, const arb_t y, slong prec);
  
 void acb_arg(arb_t r, const acb_t z, slong prec);
  
 void acb_modular_eisenstein(acb_ptr r, const acb_t tau, slong len, slong prec);
 
 void acb_pow_arb(acb_t z, const acb_t x, const arb_t y, slong prec);
  
 void acb_pow(acb_t z, const acb_t x, const acb_t y, slong prec);

 void nthHardyZero( arb_t res, int n, slong prec );

 int arb_contains(const arb_t x, const arb_t y);

 int arb_set_str(arb_t res, const char *inp, slong prec);
  
 int arb_overlaps(const arb_t x, const arb_t y);

  acb_ptr _acb_vec_init(slong n);

  arb_ptr _arb_vec_init(slong n);

  void _acb_vec_clear(acb_ptr v, slong n);

 void acb_mul_ui(acb_t z, const acb_t x, ulong y, slong prec);

 void acb_mul(acb_t z, const acb_t x, const acb_t y, slong prec);

 void acb_set(acb_t z, const acb_t x);

  void acb_clear(acb_t x);

 void acb_add(acb_t z, const acb_t x, const acb_t y, slong prec);

 void acb_sub_ui(acb_t z, const acb_t x, ulong y, slong prec);

 void acb_add_ui(acb_t z, const acb_t x, ulong y, slong prec);

 void acb_div(acb_t z, const acb_t x, const acb_t y, slong prec);

 void acb_sub(acb_t z, const acb_t x, const acb_t y, slong prec);

 void acb_mul_fmpz(acb_t z, const acb_t x, const fmpz_t y, slong prec);

 arb_ptr _arb_vec_init(slong n);
 void _arb_vec_clear(arb_ptr v, slong n);
 void acb_mul_onei(acb_t z, const acb_t x);
 void acb_div_onei(acb_t z, const acb_t x);
 void acb_neg(acb_t z, const acb_t x);
 void arb_pow_ui(arb_t y, const arb_t b, ulong e, slong prec);

void acb_abs(arb_t r, const acb_t z, slong prec);
 
void arb_sqrt(arb_t z, const arb_t x, slong prec);
 
void acb_sqr(acb_t z, const acb_t x, slong prec);
  
int acb_contains(const acb_t x, const acb_t y);

int acb_overlaps(const acb_t x, const acb_t y);
   
int acb_contains_zero(const acb_t x); 


 slong acb_allocated_bytes(const acb_t x);
 
 void acb_mul_arb(acb_t z, const acb_t x, const arb_t y, slong prec);
 void acb_div_arb(acb_t z, const acb_t x, const arb_t y, slong prec);
 
 void acb_neg(acb_t z, const acb_t x); 
 void arb_set(arb_t z, const arb_t x);
 
 void acb_elliptic_k(acb_t res, const acb_t m, slong prec);
  
 void mp_set_memory_functions (void *(*) (size_t),
                                      void *(*) (void *, size_t, size_t),
                                      void (*) (void *, size_t));
       
void acb_get_mid(acb_t m, const acb_t x);
                                       
void arb_const_pi(arb_t z, slong prec);

int arb_is_finite(const arb_t z);

int acb_is_finite(const acb_t z);

 void arb_abs(arb_t y, const arb_t x);
  
   void arb_zero(arb_t x);
   
    void arb_one(arb_t f);
    
    void arb_pos_inf(arb_t x);
    
  void arb_neg_inf(arb_t x);
 
 
 void arb_zero_pm_inf(arb_t x);
 
  void arb_indeterminate(arb_t x);
  
   void arb_zero_pm_one(arb_t x);
   
    slong arb_rel_accuracy_bits(const arb_t x);
    
    void arb_unit_interval(arb_t x);
    
    void mag_zero(mag_t res);
    
   void arb_div_si(arb_t z, const arb_t x, slong y, slong prec);
   
  void arb_trim(arb_t y, const arb_t x);
  
   void arb_set_interval_mag(arb_t x, const mag_t a, const mag_t b, slong prec);

void arb_sub(arb_t z, const arb_t x, const arb_t y, slong prec);

void acb_modular_theta_series(acb_poly_t theta1, acb_poly_t theta2, acb_poly_t theta3, acb_poly_t theta4, const acb_poly_t z, const acb_t tau, slong len, slong prec);

 void acb_poly_init(acb_poly_t poly);
 
void arb_init(arb_t x);

void arf_init(arf_t x);

void acb_init(acb_t x);
 
  void arb_tanh(arb_t y, const arb_t x, slong prec);

  void arb_asinh(arb_t z, const arb_t x, slong prec);
 
  void arb_acosh(arb_t z, const arb_t x, slong prec);
  
  void arb_lambertw(arb_t res, const arb_t x, int flags, slong prec);
  
  void arb_gamma(arb_t z, const arb_t x, slong prec);
  
  void arb_lgamma(arb_t z, const arb_t x, slong prec);
  
  void arb_sqr(arb_t y, const arb_t x, slong prec);
  
  void acb_sqrt(acb_t y, const acb_t x, slong prec);
  
 // TODO: add https://arblib.org/acb_modular.html#c.psl2z_t

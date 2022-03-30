%{

#include <flint/fmpz.h>
#include <mpfr.h>
#include <flint/flint.h>
#include <arf.h>
#include <arb.h>
#include <acb.h>
#include <acb_calc.h>
#include <acb_elliptic.h>
#include <acb_poly.h>
#include <acb_dirichlet.h>
#include <dirichlet.h>
#include <acb_modular.h>
#include "calculus.h"

#include "complex_plot.h"
#ifndef size_t
#define size_t long unsigned int
#endif
%}

typedef struct
{
  arf_interval_ptr found;
  int *flags;
  int n;
} root_struct;

typedef struct
{
    arb_ptr entries;
    slong r;
    slong c;
    arb_ptr * rows;
}
arb_mat_struct;

typedef arb_mat_struct arb_mat_t[1];

typedef struct
{
    acb_ptr entries;
    slong r;
    slong c;
    acb_ptr * rows;
}
acb_mat_struct;

typedef acb_mat_struct acb_mat_t[1];

typedef struct
{
    arf_struct a;
    arf_struct b;
}
arf_interval_struct;

typedef arf_interval_struct arf_interval_t[1];
typedef arf_interval_struct * arf_interval_ptr;
typedef const arf_interval_struct * arf_interval_srcptr;



typedef struct
{
    fmpz exp;
    mp_limb_t man;
}
mag_struct;

typedef mag_struct mag_t[1];
typedef mag_struct * mag_ptr;
typedef const mag_struct * mag_srcptr;


typedef struct
{
    arb_struct real;
    arb_struct imag;
}
acb_struct;

typedef acb_struct acb_t[1];
typedef acb_struct * acb_ptr;
typedef const acb_struct * acb_srcptr;

typedef struct
{
    ulong q;                /* modulus */
    ulong q_even;           /* even part of modulus */
    nmod_t mod;             /* modulus with precomputed inverse */
    ulong rad_q;            /* radical = product of odd primes */
    ulong phi_q;            /* phi(q) = group size */
    slong neven;            /* number of even components (in 0,1,2)*/
    slong num;              /* number of prime components (even + odd) */
    ulong expo;             /* exponent = largest order in G */
    dirichlet_prime_group_struct * P;
    ulong * generators;     /* generators lifted mod q */
    ulong * PHI;            /* PHI(k) = expo / phi(k)        */
}
dirichlet_group_struct;

typedef dirichlet_group_struct dirichlet_group_t[1];

typedef struct
{
    ulong n;           /* number */
    ulong * log;       /* s.t. prod generators[k]^log[k] = number */
}
dirichlet_char_struct;

typedef dirichlet_char_struct dirichlet_char_t[1];

 
typedef struct
{
    ulong p;    /* underlying prime */
    int e;      /* exponent */
    nmod_t pe;  /* modulus */
    nmod_t phi;  /* phi(p^e) */
    ulong g;    /* conrey generator */
    dlog_precomp_struct * dlog;  /* precomputed data for discrete log mod p^e */
}
dirichlet_prime_group_struct;

typedef dirichlet_group_struct dirichlet_group_t[1];

typedef struct
{
    arf_struct mid;
    mag_struct rad;
}
arb_struct;

typedef arb_struct arb_t[1];
typedef arb_struct * arb_ptr;
typedef const arb_struct * arb_srcptr;


typedef struct
{
    mp_limb_t d[ARF_NOPTR_LIMBS];
}
mantissa_noptr_struct;

typedef struct
{
    mp_size_t alloc;
    mp_ptr d;
}
mantissa_ptr_struct;

typedef union
{
    mantissa_noptr_struct noptr;
    mantissa_ptr_struct ptr;
}
mantissa_struct;

typedef struct
{
    fmpz exp;
    mp_size_t size;
    mantissa_struct d;
}
arf_struct;

typedef struct {
  mpfr_prec_t  _mpfr_prec;
  mpfr_sign_t  _mpfr_sign;
  mpfr_exp_t   _mpfr_exp;
  mp_limb_t   *_mpfr_d;
} __mpfr_struct;

typedef fmpz fmpz_t[1];



typedef arf_struct arf_t[1];
typedef arf_struct * arf_ptr;
typedef const arf_struct * arf_srcptr;

typedef int (*acb_calc_func_t)(acb_ptr out, const acb_t inp, void * param, slong order, slong prec);

typedef struct
{
    acb_ptr coeffs;
    slong length;
    slong alloc;
}
acb_poly_struct;

typedef acb_poly_struct acb_poly_t[1];
